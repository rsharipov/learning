package com.rsharipov.codingtasks;

public class DivisionWithoutDivisionOperator {
    
    public double divide(double dividend, double divisor) {
        if (divisor == .0) throw new IllegalArgumentException("Divisor cannot be zero");
        if (dividend == .0) return .0;
        final boolean negativeResult = dividend < 0 && divisor > 0 || dividend > 0 && divisor < 0;
        final double absResult = Math.exp(Math.log(Math.abs(dividend)) - Math.log(Math.abs(divisor)));
        return negativeResult ? -absResult : absResult;
    }
    
}
