package com.rsharipov.codingtasks;

import org.junit.Test;
import static org.junit.Assert.*;

public class DivisionWithoutDivisionOperatorTest {
    
    private static final double DELTA = 1e-10;
    
    @Test(expected = IllegalArgumentException.class)
    public void testDivisionByZeroThrowsException() {
        new DivisionWithoutDivisionOperator().divide(1.0, 0.0);
    }
    
    @Test
    public void testDivisionOfZeroIsZero() {
        new DivisionWithoutDivisionOperator().divide(0.0, 1.0);
    }
    
    @Test
    public void testDivisionOfPositiveNumbersIsPositive() {
        assertEquals(1.5, new DivisionWithoutDivisionOperator().divide(3.0, 2.0), DELTA);
    }
    
    @Test
    public void testDivisionOfNegativeNumbersIsPositive() {
        assertEquals(2.5, new DivisionWithoutDivisionOperator().divide(-5.0, -2.0), DELTA);
    }
    
    @Test
    public void testDivisionOfANegativeAndAPositiveNumbersIsNegative() {
        assertEquals(-2.5, new DivisionWithoutDivisionOperator().divide(-5.0, 2.0), DELTA);
    }
    
}
