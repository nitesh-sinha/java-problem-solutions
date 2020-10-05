//        Given an integer array with all positive numbers and no duplicates, find the number of possible combinations
//        that add up to a positive integer target.
//
//        Example:
//
//        nums = [1, 2, 3]
//        target = 4
//
//        The possible combination ways are:
//        (1, 1, 1, 1)
//        (1, 1, 2)
//        (1, 2, 1)
//        (1, 3)
//        (2, 1, 1)
//        (2, 2)
//        (3, 1)
//
//        Note that different sequences are counted as different combinations.
//
//        Therefore the output is 7.

// Time complexity: O(nk) where n=length of nums; k=target

package com.nitesh.array;

import java.util.Arrays;

public class combinationSum4 {
    public int combinationSum4Fn(int[] nums, int target) {
        // Dynamic programming solution
        if(target <= 0)
            return 0;
        int remainingTarget;

        Arrays.sort(nums);

        int[] numCombo = new int[target+1]; // numCombo[i] stores the num of possible sequences where each of them add up to i
        numCombo[0] = 1;

        for(int i = 1; i<= target; i++) {
            numCombo[i] = 0;
            for(int j = 0; j<nums.length && nums[j] <= i; j++) {
                remainingTarget = i-nums[j];
                numCombo[i] += numCombo[remainingTarget];
            }
        }
        return numCombo[target];
    }
}
