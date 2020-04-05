//        Given a collection of numbers that might contain duplicates, return all possible unique permutations.
//
//        Example:
//
//        Input: [1,1,2]
//        Output:
//        [
//        [1,1,2],
//        [1,2,1],
//        [2,1,1]
//        ]

package com.nitesh.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class permuteUnique {
    public List<List<Integer>> permuteUniqueFn(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> tempList = new ArrayList<>();
        boolean[] used = new boolean[nums.length];

        for(int i=0;i<nums.length;i++)
            used[i] = false;

        Arrays.sort(nums);
        getPermutations(res, tempList, used, nums);
        return res;
    }

    private void getPermutations(List<List<Integer>> res, List<Integer> tempList, boolean[] used, int[] nums) {
        if(tempList.size() == nums.length)
            res.add(new ArrayList<>(tempList));
        else {
            for(int i=0; i< nums.length; i++) {
                if(used[i] || i>0 && nums[i]==nums[i-1] && !used[i-1])
                    // used[i-1] is true when we're making a new pattern
                    // i.e. in recursive mode not in backtracking mode yet
                    // so in this case re-use duplicates to create a new pattern
                    continue;
                tempList.add(nums[i]);
                used[i]=true;
                getPermutations(res, tempList, used, nums);
                tempList.remove(tempList.size()-1);
                used[i]=false;
            }
        }
    }
}
