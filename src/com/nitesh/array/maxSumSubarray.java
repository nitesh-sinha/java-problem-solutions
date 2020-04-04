//        Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.
//
//        Example:
//
//        Input: [-2,1,-3,4,-1,2,1,-5,4],
//        Output: 6
//        Explanation: [4,-1,2,1] has the largest sum = 6.


package com.nitesh.array;

public class maxSumSubarray {
    public int maxSumSubArrayFn(int[] nums) {
        int sumTillNow=0, maxSum=Integer.MIN_VALUE;

        for(int n:nums) {
            sumTillNow += n;
            // if current number > (sum of relevant numbers seen before+current num)
            // then ignore the numbers seen before in the maximum subarray
            sumTillNow = Math.max(sumTillNow, n);
            maxSum = Math.max(maxSum, sumTillNow);
        }
        return maxSum;
    }

}
