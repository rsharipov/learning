package com.rsharipov.codingtasks;

public class VersionComparison {
    public int compareVersion(String version1, String version2) {
        String[] numbers1 = version1.split("\\.");
        String[] numbers2 = version2.split("\\.");
        for (int i = 0; i < numbers1.length && i < numbers2.length; ++i) {
            int comparison = Long.compare(Long.parseLong(numbers1[i]), Long.parseLong(numbers2[i]));
            if (comparison != 0) return comparison;
        }
        if (numbers1.length > numbers2.length && !restIsAllNulls(numbers1, numbers2.length)) {
            return 1;
        }
        if (numbers2.length > numbers1.length && !restIsAllNulls(numbers2, numbers1.length)) {
            return -1;
        }
        return 0;
    }
    
    public boolean restIsAllNulls(String[] numbers, int after) {
        for (int i = after; i < numbers.length; ++i) {
            if (Long.parseLong(numbers[i]) != 0) {
                return false;
            }
        }
        return true;
    }
    
    public static void main(String[] args) {
        System.out.println(new VersionComparison().compareVersion("01", "1"));
    }
}
