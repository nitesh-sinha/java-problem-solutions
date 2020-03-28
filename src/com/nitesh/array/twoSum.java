//     Problem: Given an array of integers, return indices of the two numbers such that they add up to a specific target.
//
//     You may assume that each input would have exactly one solution.
//
//
//      Time complexity: O(n) where n is length of nums

package com.nitesh.array;
import java.util.Map;
import java.util.HashMap;

public class twoSum {
    public int[] twoSumFn(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>(); // Stores number and its corresponding index
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }
}
