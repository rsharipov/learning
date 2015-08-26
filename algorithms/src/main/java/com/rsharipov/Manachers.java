package com.rsharipov;

import java.util.Arrays;

public class Manachers {

    String findLongestPalindrome(String input) {
        if (input == null || input.isEmpty()) {
            return "";
        }
        char[] inputArray = input.toCharArray();
        char[] mixedArray = mixInZeroes(inputArray);
        int[] radiuses = new int[mixedArray.length];
        int radius = 0;
        int center = 0;
        for (int i = 0; i < mixedArray.length; ++i) {
            if (center + radius >= i) {
                radiuses[i] = Math.min(center + radius - i, radiuses[center - (i - center)]);
            }
            while (i - radiuses[i] - 1 >= 0 && i + radiuses[i] + 1 < mixedArray.length &&
                mixedArray[i - radiuses[i] - 1] == mixedArray[i + radiuses[i] + 1]) {
                ++radiuses[i];
            }
            if (i + radiuses[i] > center + radius) {
                radius = radiuses[i];
                center = i;
            }
        }
        int maxLength = 0;
        int start = 0;
        for (int i = 0; i < mixedArray.length; ++i) {
            int length = i % 2 == 0 ? radiuses[i] : (radiuses[i] / 2 * 2 + 1);
            if (length > maxLength) {
                maxLength = length;
                start = (i - radiuses[i]) / 2;
            }
        }
        return input.substring(start, start + maxLength);
    }
    
    char[] mixInZeroes(char[] input) {
        char[] result = new char[input.length * 2 + 1];
        for (int i = 0; i < input.length; ++i) {
            result[2 * i] = 0;
            result[2 * i + 1] = input[i];
        }
        result[result.length - 1] = 0;
        return result;
    }
    
}
