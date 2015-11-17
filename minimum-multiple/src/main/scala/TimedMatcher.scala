

import java.util.concurrent.TimeUnit
import org.scalatest.exceptions.TestFailedException

object TimedMatcher {
  def shouldRunIn (timeAmount: Int, units: TimeUnit) (action: => Unit) {
		  val start = System.nanoTime
			action
			val end = System.nanoTime
			val actualNanos = end - start;
			val expectedNanos = units.toNanos(timeAmount)
			if (actualNanos > expectedNanos)
			  throw new TestFailedException(Some("should run in " +
		      (expectedNanos / 1000000000.0) + " seconds, but run in " +
		      (actualNanos / 1000000000.0) + " seconds"), None, 5)
		}
}