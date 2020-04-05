//        Given a collection of integers that might contain duplicates, nums, return all possible subsets (the power set).
//
//        Note: The solution set must not contain duplicate subsets.
//
//        Example:
//
//        Input: [1,2,2]
//        Output:
//        [
//        [2],
//        [1],
//        [1,2,2],
//        [2,2],
//        [1,2],
//        []
//        ]

package com.nitesh.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class allPossibleSubsetsWithDups {
    public List<List<Integer>> allPossibleSubsetsWithDupsFn(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        List<Integer> tempList = new ArrayList<Integer>();
        Arrays.sort(nums);
        getSubsets(res, tempList, nums, 0);
        return res;
    }

    private void getSubsets(List<List<Integer>> res, List<Integer> tempList, int[] nums, int start) {
        res.add(new ArrayList<>(tempList));
        for(int i=start; i<nums.length;i++) {
            // i>start ensures that [1,2,2] and [2,2]
            // combinations don't get skipped in the final result list
            if(i>start && nums[i]==nums[i-1])
                continue;
            tempList.add(nums[i]);
            getSubsets(res, tempList, nums, i+1);
            tempList.remove(tempList.size()-1);
        }
    }
}
