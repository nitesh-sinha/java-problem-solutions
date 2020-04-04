//        Given an integer array nums, find the contiguous subarray within an array (containing at least one number) which has the largest product.
//
//        Example 1:
//
//        Input: [2,3,-2,4]
//        Output: 6
//        Explanation: [2,3] has the largest product 6.
//        Example 2:
//
//        Input: [-2,0,-1]
//        Output: 0
//        Explanation: The result cannot be 2, because [-2,-1] is not a subarray.

package com.nitesh.array;

public class maxProdSubarray {
    public int maxProdSubarrayFn(int[] nums) {
        if(nums.length==0)
            return 0;
        int minCurIter, maxCurIter, globalMax=nums[0], minPrevIter=nums[0], maxPrevIter=nums[0];

        for(int i=1;i<nums.length;i++) {
            // Take max and min from previus iterations, multiply them with
            // current element and look for the global maximum
            // (Remember -ve * -ve = +ve).
            maxCurIter = Math.max(maxPrevIter*nums[i], Math.max(minPrevIter*nums[i], nums[i]));
            minCurIter = Math.min(maxPrevIter*nums[i], Math.min(minPrevIter*nums[i], nums[i]));
            globalMax = Math.max(maxCurIter, globalMax);
            // Store cur max and min values to use in next iteration
            minPrevIter = minCurIter;
            maxPrevIter = maxCurIter;
        }
        return globalMax;
    }
}
