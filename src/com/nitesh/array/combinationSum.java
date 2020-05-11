//        Given a set of candidate numbers (candidates) (without duplicates) and a target number (target), find all unique combinations in candidates
//        where the candidate numbers sums to target.
//
//        The same repeated number may be chosen from candidates unlimited number of times.
//
//        Note:
//
//        All numbers (including target) will be positive integers.
//        The solution set must not contain duplicate combinations.
//        Example 1:
//
//        Input: candidates = [2,3,6,7], target = 7,
//        A solution set is:
//        [
//        [7],
//        [2,2,3]
//        ]
//        Example 2:
//
//        Input: candidates = [2,3,5], target = 8,
//        A solution set is:
//        [
//        [2,2,2,2],
//        [2,3,3],
//        [3,5]
//        ]

package com.nitesh.array;

import java.util.ArrayList;
import java.util.List;

public class combinationSum {
    public List<List<Integer>> combinationSumFn(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        List<Integer> tempList = new ArrayList<Integer>();
        getSubsets(res, tempList, candidates, target, 0);
        return res;
    }

    private void getSubsets(List<List<Integer>> res, List<Integer> tempList, int[] candidates, int target, int start) {
        if(target == 0)
            res.add(new ArrayList<>(tempList));
        else if(target>0) {
            for(int i=start; i<candidates.length;i++) {
                tempList.add(candidates[i]);
                getSubsets(res, tempList, candidates, target-candidates[i], i);
                tempList.remove(tempList.size()-1);
            }
        }
    }
}
