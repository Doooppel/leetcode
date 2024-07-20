package com.doppel.leetcode;

import java.util.HashMap;
import java.util.Map;

public class TwoSum {

    public static void main(String[] args) {
        int[] nums = {1, 3, 4, 5, 7};
        int[] indexes = twoSum(nums, 12);
        for (int index : indexes) {
            System.out.println("index = " + index);
        }
    }

    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int current = nums[i];
            int result = target - current;
            Integer cachedIndex = map.get(result);

            if (map.containsKey(result) && cachedIndex != i ) {
                return new int[]{cachedIndex, i};
            }
            map.put(current, i);
        }
        return null;
    }
}

