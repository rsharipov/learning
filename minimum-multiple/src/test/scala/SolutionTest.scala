import org.scalatest.Matchers
import TimedMatcher._
import org.scalatest.FlatSpec
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.util.concurrent.TimeUnit
import org.scalatest.exceptions.TestFailedException

class SolutionSpec extends FlatSpec with Matchers {


	"Solution" should "return expected values for queries" in {
		val lcmTree = new LCMTree(Array(1, 2, 3, 4, 5, 6, 7))
		def For(left: Int, right: Int): BigInt = lcmTree.query(left, right)
	
		For(0, 0) shouldBe 1
		For(0, 1) shouldBe 2
		For(0, 2) shouldBe 6
		For(0, 3) shouldBe 12
		For(0, 4) shouldBe 60
		For(0, 5) shouldBe 60
		For(0, 6) shouldBe 420

		For(2, 4) shouldBe 60
		For(1, 3) shouldBe 12

		For(4, 6) shouldBe 210
	}

	it should "return expected values for queries after update" in {
		val lcmTree = new LCMTree(Array(1, 2, 3, 4, 5, 6, 7))
		def For(left: Int, right: Int): BigInt = lcmTree.query(left, right)

		lcmTree.update(1, 7)

		For(0, 0) shouldBe 1
		For(0, 1) shouldBe 14
		For(0, 2) shouldBe 42
		For(0, 3) shouldBe 84
		For(0, 4) shouldBe 420
		For(0, 5) shouldBe 420
		For(0, 6) shouldBe 420
		
	}
	
	it should "return expected values for queries after several updates" in {
		val lcmTree = new LCMTree(Array(1, 2, 3, 4, 5, 6, 7))
		def For(left: Int, right: Int): BigInt = lcmTree.query(left, right)

		lcmTree.update(2, 3)
		lcmTree.update(4, 7)

		// 1 2 9 4 35 6 7
		
		For(0, 0) shouldBe 1
		For(0, 1) shouldBe 2
		For(0, 2) shouldBe 18
		For(0, 3) shouldBe 36
		For(0, 4) shouldBe 1260
		For(0, 5) shouldBe 1260
		For(0, 6) shouldBe 1260
		For(2, 3) shouldBe 36
		For(2, 4) shouldBe 1260
		For(2, 5) shouldBe 1260
	}
	
	it should "solve for input correctly" in {
		val input = new ByteArrayInputStream((
				"5\n" +
				"2 5 6 1 9\n" +
				"7\n" +
				"Q 0 4\n" +
				"U 1 2\n" +
				"Q 0 2\n" +
				"Q 3 4\n" +
				"Q 2 4\n" +
				"U 3 8\n" +
				"Q 2 3\n").getBytes)
		val output = new ByteArrayOutputStream()
		Solution.solve(input, output)
		output.toString shouldBe "90\n30\n9\n18\n24\n".replaceAll("\n", System.lineSeparator())
	}
	
	it should "solve for bigger input correctly" in {
		val input = getClass().getResourceAsStream("input.txt");
		val output = new ByteArrayOutputStream()
		Solution.solve(input, output)
		output.toString shouldBe (scala.io.Source.fromInputStream(
	      getClass().getResourceAsStream("output.txt")).getLines().mkString(System.lineSeparator()) + System.lineSeparator())
	}
	
//	it should "run in less than 7 seconds" in {
//	  import TimeUnit._
//		
//		shouldRunIn (7, SECONDS) {
//			val output = new ByteArrayOutputStream()
//			val input = getClass().getResourceAsStream("input_slow.txt");
//			input should not be null
//			Solution.solve(input, output)			
//		}
//	}
	
	"Factorization" should "factorize a number correctly" in {
	  Factorization.factorize(630) shouldEqual List((2, 1), (3, 2), (5, 1), (7, 1))
	}
	
	it should "merge to lcm correctly" in {
	  Factorization(250) lcm Factorization(90) shouldEqual Factorization(2250)
	}
	
	it should "power by modulo correctly" in {
		Factorization.powMod(1, 4, 50) shouldEqual 1
		Factorization.powMod(2, 4, 2) shouldEqual 0
	  Factorization.powMod(2, 5, 17) shouldEqual 15
	  Factorization.powMod(3, 4, 50) shouldEqual 31
	  Factorization.powMod(3, 5, 50) shouldEqual 43
	  Factorization.powMod(2, 40, 1000000009) shouldEqual 511617885
	}
	
	it should "toInt correctly" in {
	  val lcm = Factorization(511617885) lcm Factorization(37)
	  lcm.toInt(1000000009) shouldEqual 929861583
	}
}