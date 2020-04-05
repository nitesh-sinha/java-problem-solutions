//        Given a collection of distinct integers, return all possible permutations.
//
//        Example:
//
//        Input: [1,2,3]
//        Output:
//        [
//        [1,2,3],
//        [1,3,2],
//        [2,1,3],
//        [2,3,1],
//        [3,1,2],
//        [3,2,1]
//        ]

package com.nitesh.array;

import java.util.ArrayList;
import java.util.List;

public class permute {
    public List<List<Integer>> permuteFn(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> tempList = new ArrayList<>();
        getPermutations(res, tempList, nums);
        return res;
    }

    private void getPermutations(List<List<Integer>> res, List<Integer> tempList, int[] nums) {
        if(tempList.size() == nums.length)
            res.add(new ArrayList<>(tempList));
        else {
            for(int i=0; i< nums.length; i++) {
                if(tempList.contains(nums[i]))
                    continue;
                tempList.add(nums[i]);
                getPermutations(res, tempList, nums);
                tempList.remove(tempList.size()-1);
            }
        }
    }
}
