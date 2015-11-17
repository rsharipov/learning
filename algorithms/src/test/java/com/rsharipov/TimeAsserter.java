package com.rsharipov;

import org.junit.Assert;

public class TimeAsserter implements AutoCloseable {
    
    private final int milliSecondsLimit;
    private final long start;
    
    public TimeAsserter(int milliSecondsLimit) {
        this.milliSecondsLimit = milliSecondsLimit;
        this.start = System.nanoTime();
    }

    @Override
    public void close() {
        long end = System.nanoTime();
        long runInMillis = (end - start) / NANOSECONDS_IN_MILLISECOND;
        if (runInMillis > milliSecondsLimit) {
            Assert.fail("Expected running time of at most " + milliSecondsLimit + "ms, but was " +
                runInMillis + "ms");
        }            
    }
    private static final int NANOSECONDS_IN_MILLISECOND = 1000000;
}
