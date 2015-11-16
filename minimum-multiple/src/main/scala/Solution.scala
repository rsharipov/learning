import java.util.Scanner
import java.io.BufferedInputStream
import scala.BigInt
import scala.math.BigInt.int2bigInt
import java.io.InputStream
import java.io.OutputStream
import java.io.PrintStream

object Solution {

	abstract class Node {
		val multiple: BigInt
	}
	case class Leaf(multiple: BigInt) extends Node
	case class Tree(left: Node, right: Node, multiple: BigInt) extends Node
	
	abstract class Request
	case class Query(left: Int, right: Int) extends Request
	case class Update(index: Int, value: Int) extends Request
	
	class Processor(_data: Array[Int]) {
		val MODULO = 1000000007
		val data = _data.clone()
		var tree = buildTree(data, 0, data.length - 1)
		
		private def gcd(x: BigInt, y: BigInt) : BigInt = if (y == 0) x else gcd(y, x % y)
		private def lcm(x: BigInt, y: BigInt) : BigInt = x * y / gcd(x, y)
		private def buildTree(data: Array[Int], left: Int, right: Int) : Node = {
			if (left == right) Leaf(data(left))
			else {
				val mid = (left + right) / 2
				val leftTree = buildTree(data, left, mid)
				val rightTree = buildTree(data, mid + 1, right)
				Tree(leftTree, rightTree, lcm(leftTree.multiple, rightTree.multiple))
			}
		}
		private def updateTree(tree: Node, left: Int, right: Int, index: Int, value: Int) : Node = {
			tree match {
				case Leaf(multiple) => Leaf(multiple * value)
				case Tree(leftTree, rightTree, multiple) =>
					val mid = (left + right) / 2
					if (index <= mid) {
						val oldValue = leftTree.multiple
						val newLeftTree = updateTree(leftTree, left, mid, index, value)
						val newValue = newLeftTree.multiple
						val c = newValue / oldValue
						Tree(newLeftTree, rightTree, oldValue * lcm(multiple / oldValue, c))
					}
					else {
						val oldValue = rightTree.multiple
						val newRightTree = updateTree(rightTree, mid + 1, right, index, value)
						val newValue = newRightTree.multiple
						val c = newValue / oldValue
						Tree(leftTree, newRightTree, oldValue * lcm(multiple / oldValue, c))
					}
			}
		}

		def query(tree: Node, left: Int, right: Int, leftReq: Int, rightReq: Int) : BigInt = {
			tree match {
				case Leaf(multiple) => multiple
				case Tree(leftTree, rightTree, multiple) =>
					val mid = (left + right) / 2
					if (rightReq <= mid) {
						query(leftTree, left, mid, leftReq, rightReq)
					}
					else if (mid + 1 <= leftReq) {
						query(rightTree, mid + 1, right, leftReq, rightReq)
					}
					else {
						lcm(query(leftTree, left, mid, leftReq, mid), query(rightTree, mid + 1, right, mid + 1, rightReq))
					}
			}
		}
		
		def query(left: Int, right: Int) : Int = (query(tree, 0, data.length - 1, left, right) % MODULO).toInt
		
		def update(index: Int, value: Int) {
			tree = updateTree(tree, 0, data.length - 1, index, value)
		}
	}
	
	def solve(in: InputStream, out: OutputStream) {
		val scanner = new Scanner(new BufferedInputStream(in));
		val printer = new PrintStream(out)
		val n = scanner.nextInt();
		val a = new Array[Int](n);
		for (i <- 0 until n) {
			a(i) = scanner.nextInt();
		}
		val k = scanner.nextInt();
		val processor = new Processor(a);
		for (i <- 0 until k) {
			val query = scanner.next();
			val first = scanner.next().toInt
			val second = scanner.next().toInt
			if (query == "Q") {
				printer.println(processor.query(first, second))
			}
			else {
				processor.update(first, second)
			}
		}
	}
		
	def main(args: Array[String]) {
		solve(System.in, System.out)
	}
}