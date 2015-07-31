package com.rsharipov;

public class PolynomialTimeEstimater {

    @FunctionalInterface
    public interface Calculation {
        long estimateTime(long taskSize);
    }
    private final int maxSize;
    
    public PolynomialTimeEstimater(int maxSize) {
        this.maxSize = maxSize;
    }
    
    public double estimateHigherOrderMemberDegree(Calculation calculation) {
        long firstSize = (long)Math.sqrt(maxSize);
        long secondSize = maxSize;
        long timeFirstSize = calculation.estimateTime(firstSize);
        long timeSecondSize = calculation.estimateTime(secondSize);
        return (Math.log(timeFirstSize) - Math.log(timeSecondSize)) / 
            (Math.log(firstSize) - Math.log(secondSize));
    }
}
