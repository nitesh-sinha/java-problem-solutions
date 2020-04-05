//        Given a set of distinct integers, nums, return all possible subsets (the power set).
//
//        Note: The solution set must not contain duplicate subsets.
//
//        Example:
//
//        Input: nums = [1,2,3]
//        Output:
//        [
//        [3],
//        [1],
//        [2],
//        [1,2,3],
//        [1,3],
//        [2,3],
//        [1,2],
//        []
//        ]


package com.nitesh.array;

import java.util.ArrayList;
import java.util.List;

public class allPossibleSubsets {
    public List<List<Integer>> allPossibleSubsetsFn(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        List<Integer> tempList = new ArrayList<Integer>();
        getSubsets(res, tempList, nums, 0);
        return res;
    }

    private void getSubsets(List<List<Integer>> res, List<Integer> tempList, int[] nums, int start) {
        res.add(new ArrayList<>(tempList));
        for(int i=start; i<nums.length;i++) {
            tempList.add(nums[i]);
            getSubsets(res, tempList, nums, i+1);
            tempList.remove(tempList.size()-1);
        }
    }
}


// Here the result List contains elements in this order for an input array nums[] = {1,2,3}
//[]
//[1]
//[1,2]
//[1,2,3]
//[1,3]
//[2]
//[2,3]
//[3]