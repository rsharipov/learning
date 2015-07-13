package com.rsharipov;

import com.rsharipov.ModularArithmetics.GcdResult;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class ModularArithmeticsTest {

    @Test
    public void testGcdWhenItsNot1() {
        ModularArithmetics instance = new ModularArithmetics();
        GcdResult result = instance.gcd(15, 20);
        assertEquals(5, result.getGcd());
        assertEquals(5, 15 * result.getFirstMultiplier() + 20 * result.getSecondMultiplier());
    }
    
    @Test
    public void testGcdWhenIts1() {
        ModularArithmetics instance = new ModularArithmetics();
        GcdResult result = instance.gcd(27, 20);
        assertEquals(1, result.getGcd());
        assertEquals(1, 27 * result.getFirstMultiplier() + 20 * result.getSecondMultiplier());
    }

    
}
