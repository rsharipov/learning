package com.rsharipov;

public class ModularArithmetics {

    public static class GcdResult {

        public long getFirstMultiplier() {
            return firstMultiplier;
        }

        public long getSecondMultiplier() {
            return secondMultiplier;
        }

        public long getGcd() {
            return gcd;
        }

        public GcdResult(long firstMultiplier, long secondMultiplier, long gcd) {
            this.firstMultiplier = firstMultiplier;
            this.secondMultiplier = secondMultiplier;
            this.gcd = gcd;
        }

        private final long firstMultiplier;
        private final long secondMultiplier;
        private final long gcd;
    }
    
    public GcdResult gcd(long first, long second) {
        if (second == 0) {
            return new GcdResult(1, 0, first);
        }
        GcdResult result = gcd(second, first % second);
        
        return new GcdResult(result.secondMultiplier, result.firstMultiplier - first / second * result.secondMultiplier, result.gcd);
    }
    
    public long normalizeModulo(long number, long modulo) {
        if (number < 0) {
            number += (-number + modulo - 1) / modulo * modulo;
        }
        return number % modulo;
    }
    
    public long reciprocal(long number, long modulo) {
        GcdResult result = gcd(number, modulo);
        if (result.getGcd() != 1) {
            return 0;
        }
        return normalizeModulo(result.getFirstMultiplier(), modulo);
    }
    
}
