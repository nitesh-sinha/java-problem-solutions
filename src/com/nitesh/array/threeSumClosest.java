//        Given an array nums of n integers and an integer target, find three integers in nums such that the sum is
//        closest to target. Return the sum of the three integers. You may assume that each input would have exactly
//        one solution.
//
//        Example:
//
//        Given array nums = [-1, 2, 1, -4], and target = 1.
//
//        The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).

// Time complexity: O(n^2)

package com.nitesh.array;

import java.util.Arrays;
public class threeSumClosest {
    public int threeSumClosestFn(int[] nums, int target) {
        int len=nums.length, sum, minDiff=Integer.MAX_VALUE, second, third, closestSum=0;
        Arrays.sort(nums);

        for(int first=0; first<=len-3;first++) {
            second=first+1;
            third=len-1;
            while(second<third) {
                sum=nums[first] + nums[second] + nums[third];
                if(sum==target)
                    return sum;
                // Update closestSum if sum is closer to target
                int diff = Math.abs(sum-target);
                if(diff < minDiff) {
                    minDiff=diff;
                    closestSum=sum;
                }
                if(sum<target)
                    second++;
                else
                    third--;
            }
        }
        return closestSum;
    }
}
