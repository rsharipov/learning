package com.rsharipov;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        HashMap<Integer, List<List<Integer>>> sums = new HashMap<>();
        for (int i = 0; i < nums.length; ++i) {
            if (sums.containsKey(-nums[i])) {
                List<List<Integer>> pairs = sums.get(-nums[i]);
                for (List<Integer> pair : pairs) {
                    List<Integer> triple = new ArrayList<>(pair);
                    triple.add(i);
                    result.add(triple);
                }
            }
            else {
                for (int j = 0; j < i; ++j) {
                    List<List<Integer>> pairs = sums.get(nums[j] + nums[i]);
                    if (pairs == null) {
                        pairs = new ArrayList<>();
                        sums.put(nums[j] + nums[i], pairs);
                    }
                    pairs.add(new ArrayList(Arrays.asList(j, i)));
                }
            }
        }
        return result;
    }
}
