//        Given an unsorted array return whether an increasing subsequence of length 3 exists in the array or not.
//
//        Formally the function should:
//
//        Return true if there exists i, j, k such that arr[i] < arr[j] < arr[k] given 0 <= i < j < k<= n-1 else return false.
//
//    Example 1:  Input: nums=[1,0,2,1,3]
//                Output: True
//                Explanation: Since 1,2 and 3 form an increasing triplet.
//
//    Example 2:  Input: nums=[9,8,5,2,1]
//                Output: False
//                Explanation: Numbers are in descending order. So, no increasing triplets can be found
//
//
// Time complexity: O(n) where n = no. of input elements
// Space complexity: O(1)

package com.nitesh.array;

public class increasingTriplet {
    public boolean increasingTripletFn(int[] nums) {
        if(nums.length < 3)
            return false;

        int small=Integer.MAX_VALUE, medium = Integer.MAX_VALUE;
        for(int num : nums) {
            if(num > medium)
                return true; // because medium element is guaranteed to be greater than small

            if(num > small && num < medium)
                medium = num; // current num is between small and medium; so update medium to current num

            if(num < small)
                small = num;
        }
        // Checked all input elements and didn't find any triplet
        return false;
    }
}
