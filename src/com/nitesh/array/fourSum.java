//        Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c + d = target?
//        Find all unique quadruplets in the array which gives the sum of target.
//
//        Note: The solution set must not contain duplicate quadruplets.

//    Time complexity: O(n^3) where n=no. of elements in input array


package com.nitesh.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class fourSum {
    public List<List<Integer>> fourSumFn(int[] nums, int target) {
        int len=nums.length, k,l,sum;
        List<List<Integer>> fourSumList = new ArrayList<List<Integer>>();
        if (nums == null || nums.length < 4)
            return fourSumList;

        Arrays.sort(nums);

        // Array is now sorted, so add a guard
        if (nums[0] * 4 > target || nums[len-1] * 4 < target)
            return fourSumList;

        for(int i=0;i<len-3;i++) {
            // Additional guards for faster completion
            if (nums[i] + nums[len - 1] * 3 < target) //nums[i] is too small
                continue;
            if (nums[i] * 4 > target) { //nums[i] is too big
                break;
            }

            // Skip duplicates
            if (i>0 && nums[i]==nums[i-1]) {
                continue;
            }
            for(int j=i+1; j<len-2;j++) {
                // Additional guards for faster completion
                if (nums[i] + nums[j] + nums[len - 1] * 2 < target) //nums[j] is too small
                    continue;
                if (nums[i] + nums[j] * 3 > target) { //nums[j] is too big
                    break;
                }
                // Skip duplicates
                if(j>(i+1)&& nums[j]==nums[j-1]) {
                    continue;
                }
                // Find other 2 nos. in O(n) time using linear search
                k=j+1;
                l=len-1;
                while (k<l) {
                    sum=nums[i]+nums[j]+nums[k]+nums[l];
                    if(sum==target) {
                        fourSumList.add(Arrays.asList(nums[i],nums[j],nums[k],nums[l]));

                        // Skip duplicates
                        while(k<l && nums[k]==nums[k+1])
                            k++;
                        while(k<l && nums[l]==nums[l-1])
                            l--;

                        k++; // Skips over the last repeating element
                        l--; // Skips over the last repeating element
                    }

                    if(sum<target)
                        k++;
                    else if(sum>target)
                        l--;
                }
            }
        }
        return fourSumList;
    }
}
