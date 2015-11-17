import java.util.Scanner
import java.io.BufferedInputStream
import scala.BigInt
import scala.math.BigInt.int2bigInt
import java.io.InputStream
import java.io.OutputStream
import java.io.PrintStream
import scala.annotation.tailrec
import java.util.ArrayList
import scala.collection.mutable.HashMap

class Factorization private (private val factors: List[(Int, Int)]) {
  private def this(n: Int) = {
    this(Factorization.factorize(n))
  }
  def lcm(other: Factorization): Factorization = {
    new Factorization(merge(factors, other.factors, List(), Math.max))
  }
  def *(other: Int): Factorization = {
    new Factorization(merge(factors, Factorization.factorize(other), List(), (x, y) => x + y))
  }
  override def equals(any: Any): Boolean = {
    if (!(any.isInstanceOf[Factorization])) {
      false
    } else {
      factors == any.asInstanceOf[Factorization].factors
    }
  }
  def toInt(modulo: Int) = {
    factors.foldRight(1L)((value, acc) =>
      (acc * Factorization.powMod(value._1, value._2, modulo) % modulo)).toInt
  }
  @tailrec
  private def merge(left: List[(Int, Int)], right: List[(Int, Int)], accumulator: List[(Int, Int)],
                    degreeMerger: (Int, Int) => (Int)): List[(Int, Int)] = {
    if (left.isEmpty && right.isEmpty) {
      accumulator.reverse
    } else if (left.isEmpty) {
      merge(left, right.tail, right.head :: accumulator, degreeMerger)
    } else if (right.isEmpty) {
      merge(left.tail, right, left.head :: accumulator, degreeMerger)
    } else {
      val leftHead :: leftTail = left
      val rightHead :: rightTail = right
      if (leftHead._1 < rightHead._1) {
        merge(leftTail, right, leftHead :: accumulator, degreeMerger)
      } else if (rightHead._1 < leftHead._1) {
        merge(left, rightTail, rightHead :: accumulator, degreeMerger)
      } else {
        merge(leftTail, rightTail, (leftHead._1, degreeMerger(leftHead._2, rightHead._2)) :: accumulator, degreeMerger)
      }
    }
  }
}

object Factorization {
  def apply(n: Int) = {
    new Factorization(n)
  }
  // Imperative for simplicity's and performance's sake
  def factorize(n: Int): List[(Int, Int)] = {
    val cache = new HashMap[Int, List[(Int, Int)]]()
    val cached = cache.get(n)
    if (cached.isDefined) {
      cached.get
    } else {
      import scala.collection.JavaConversions._
      var r = n
      val factors = new ArrayList[(Int, Int)]()
      var i = 2
      while (i <= r) {
        if (r % i == 0) {
          var count = 0
          while (r % i == 0) {
            r /= i
            count += 1
          }
          factors.add((i, count))
        }
        i += 1
      }
      val list = factors.toList
      cache.put(n, list)
      list
    }
  }
  def powMod(number: Int, degree: Int, modulo: Int): Int = {
    @tailrec
    def powMod(number: Long, degree: Int, modulo: Long, result: Long): Long = {
      if (degree == 0) result
      else if (degree % 2 == 1) {
        powMod(number * number % modulo, degree / 2, modulo, result * number % modulo)
      } else {
        powMod(number * number % modulo, degree / 2, modulo, result)
      }
    }
    powMod(number, degree, modulo, 1).toInt
  }
}

abstract class Node {
  val multiple: Factorization
}
case class Leaf(multiple: Factorization) extends Node
case class Tree(left: Node, right: Node) extends Node {
  val multiple = left.multiple lcm right.multiple
}

abstract class Request
case class Query(left: Int, right: Int) extends Request
case class Update(index: Int, value: Int) extends Request

class LCMTree(_data: Array[Int]) {
  val MODULO = 1000000007
  val data = _data.clone()
  var tree = buildTree(data, 0, data.length - 1)

  private def buildTree(data: Array[Int], left: Int, right: Int): Node = {
    if (left == right) Leaf(Factorization(data(left)))
    else {
      val mid = (left + right) / 2
      val leftTree = buildTree(data, left, mid)
      val rightTree = buildTree(data, mid + 1, right)
      Tree(leftTree, rightTree)
    }
  }
  private def updateTree(tree: Node, left: Int, right: Int, index: Int, value: Int): Node = {
    tree match {
      case Leaf(multiple) => Leaf(multiple * value)
      case Tree(leftTree, rightTree) =>
        val mid = (left + right) / 2
        if (index <= mid) {
          val newLeftTree = updateTree(leftTree, left, mid, index, value)
          Tree(newLeftTree, rightTree)
        } else {
          val newRightTree = updateTree(rightTree, mid + 1, right, index, value)
          Tree(leftTree, newRightTree)
        }
    }
  }

  def query(tree: Node, left: Int, right: Int, leftReq: Int, rightReq: Int): Factorization = {
    tree match {
      case Leaf(multiple) => multiple
      case Tree(leftTree, rightTree) =>
        val mid = (left + right) / 2
        if (left == leftReq && right == rightReq) {
          tree.multiple
        }
        else if (rightReq <= mid) {
          query(leftTree, left, mid, leftReq, rightReq)
        } else if (mid + 1 <= leftReq) {
          query(rightTree, mid + 1, right, leftReq, rightReq)
        } else {
          query(leftTree, left, mid, leftReq, mid) lcm
            query(rightTree, mid + 1, right, mid + 1, rightReq)
        }
    }
  }

  def query(left: Int, right: Int): Int = query(tree, 0, data.length - 1, left, right).toInt(MODULO)

  def update(index: Int, value: Int) {
    tree = updateTree(tree, 0, data.length - 1, index, value)
  }
}

object Solution {

  def solve(in: InputStream, out: OutputStream) {
    val scanner = new Scanner(new BufferedInputStream(in));
    val printer = new PrintStream(out)
    val n = scanner.nextInt();
    val a = new Array[Int](n);
    for (i <- 0 until n) {
      a(i) = scanner.nextInt();
    }
    val k = scanner.nextInt();
    val processor = new LCMTree(a);
    for (i <- 0 until k) {
      val query = scanner.next();
      val first = scanner.next().toInt
      val second = scanner.next().toInt
      if (query == "Q") {
        printer.println(processor.query(first, second))
      } else {
        processor.update(first, second)
      }
    }
  }

  def main(args: Array[String]) {
    solve(System.in, System.out)
  }
}