// Testing to ensure that values in ArrayList are held when returning back from a recursive function call.


package com.nitesh.array;

import java.util.ArrayList;
import java.util.List;

public class testRecursion {
    public List<List<Integer>> testRecursionFn(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        List<Integer> tempList = new ArrayList<Integer>();
        getSubsets(res, tempList, nums, 0, 0);
        return res;
    }

    private void printList(List<Integer> l) {
        for(Integer i : l)
            System.out.println(i);
    }

    private void getSubsets(List<List<Integer>> res, List<Integer> tempList, int[] nums, int start, int recursionNum) {
        System.out.println("Recursion number: " + recursionNum);
        res.add(new ArrayList<>(tempList));
        for(int i=start; i<nums.length;i++) {
            tempList.add(nums[i]);
            printList(tempList);
            System.out.println("----------------");
            getSubsets(res, tempList, nums, i+1, ++recursionNum);
            System.out.println("Backtracking now");
            tempList.remove(tempList.size()-1);
            printList(tempList);
            System.out.println("----------------");
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