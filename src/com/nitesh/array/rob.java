//        You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed,
//        the only constraint stopping you from robbing each of them is that adjacent houses have security system connected and
//        it will automatically contact the police if two adjacent houses were broken into on the same night.
//
//        Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount
//        of money you can rob tonight without alerting the police.
//
//        Example 1:
//
//        Input: [1,2,3,1]
//        Output: 4
//        Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
//        Total amount you can rob = 1 + 3 = 4.
//        Example 2:
//
//        Input: [2,7,9,3,1]
//        Output: 12
//        Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
//        Total amount you can rob = 2 + 9 + 1 = 12.

// Time complexity: O(n) where n=no. of houses

package com.nitesh.array;

public class rob {
    public int robFn(int[] nums) {
        int len=nums.length;
        if(len==0)
            // zero house
            return 0;

        // more than 0 house
        int[] maxMoney = new int[len]; // maxMoney[i] is the max money that can be robbed if i'th house is the last house in the row of houses
        if(len>1) {
            maxMoney[0] = nums[0];
            maxMoney[1] = Math.max(nums[0], nums[1]);
            for(int i=2;i<len;i++) {
                // since we accumulate sum in maxMoney array as we iterate
                // to get max money select max of previous or (previous-1 + current)
                maxMoney[i] = Math.max(maxMoney[i-2]+nums[i], maxMoney[i-1]);
            }
        }
        return maxMoney[len-1];
    }
}
