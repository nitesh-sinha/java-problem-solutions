//        Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.
//
//        Each number in candidates may only be used once in the combination.
//
//        Note:
//
//        All numbers (including target) will be positive integers.
//        The solution set must not contain duplicate combinations.
//        Example 1:
//
//        Input: candidates = [10,1,2,7,6,1,5], target = 8,
//        A solution set is:
//        [
//        [1, 7],
//        [1, 2, 5],
//        [2, 6],
//        [1, 1, 6]
//        ]
//        Example 2:
//
//        Input: candidates = [2,5,2,1,2], target = 5,
//        A solution set is:
//        [
//        [1,2,2],
//        [5]
//        ]


package com.nitesh.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class combinationSum2 {
    public List<List<Integer>> combinationSum2Fn(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        List<Integer> tempList = new ArrayList<Integer>();
        Arrays.sort(candidates);
        getSubsets(res, tempList, candidates, target, 0);
        return res;
    }

    private void getSubsets(List<List<Integer>> res, List<Integer> tempList, int[] candidates, int target, int start) {
        if(target == 0)
            res.add(new ArrayList<>(tempList));
        else if(target>0) {
            for(int i=start; i<candidates.length;i++) {
                if(i>start && candidates[i]==candidates[i-1])
                    continue;
                tempList.add(candidates[i]);
                getSubsets(res, tempList, candidates, target-candidates[i], i+1);
                tempList.remove(tempList.size()-1);
            }
        }
    }
}
