import org.scalatest.Matchers
import org.scalatest.FlatSpec
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream

class SolutionSpec extends FlatSpec with Matchers {


	"Solution" should "return expected values for queries" in {
		val solution = new Solution.Processor(Array(1, 2, 3, 4, 5, 6, 7))
		def For(left: Int, right: Int): BigInt = solution.query(left, right)
	
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
		val solution = new Solution.Processor(Array(1, 2, 3, 4, 5, 6, 7))
		def For(left: Int, right: Int): BigInt = solution.query(left, right)

		solution.update(1, 7)

		For(0, 0) shouldBe 1
		For(0, 1) shouldBe 14
		For(0, 2) shouldBe 42
		For(0, 3) shouldBe 84
		For(0, 4) shouldBe 420
		For(0, 5) shouldBe 420
		For(0, 6) shouldBe 420
		
	}
	
	it should "return expected values for queries after several updates" in {
		val solution = new Solution.Processor(Array(1, 2, 3, 4, 5, 6, 7))
		def For(left: Int, right: Int): BigInt = solution.query(left, right)

		solution.update(2, 3)
		solution.update(4, 7)

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
	
	it should "run in less than 7 seconds" in {
		case class seconds(value: Int)
		class TimedRunner(action: => Unit) {
			def in(secs: seconds) {
				val start = System.nanoTime
				action
				val end = System.nanoTime
				(((end - start) / 1000000000).toInt) should be < secs.value
			}
		}
		def shouldRun (action: => Unit) = {
			new TimedRunner(action)
		}
		
		shouldRun {
			val output = new ByteArrayOutputStream()
			Solution.solve(getClass().getResourceAsStream("input.txt"), output)			
		} in seconds(7)
	}

}