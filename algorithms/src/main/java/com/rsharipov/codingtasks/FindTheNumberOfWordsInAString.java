package com.rsharipov.codingtasks;

public class FindTheNumberOfWordsInAString {

    /**
     * @param input
     * @return the amount of non-empty char sequences
     * delimited by spaces in the @input string
     */
    public int countWordsInAString(String input) {
        String[] splitParts = input.split("\\s+");
        return countNonEmpty(splitParts);
    }

    private int countNonEmpty(String[] parts) {
        int count = 0;
        for (int i = 0; i < parts.length; ++i) {
            if (!parts[i].isEmpty()) {
                ++count;
            }
        }
        return count;
    }
    
}
