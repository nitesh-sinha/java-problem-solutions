//        You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed.
//        All houses at this place are arranged in a circle. That means the first house is the neighbor of the last one.
//        Meanwhile, adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.
//
//        Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money
//        you can rob tonight without alerting the police.
//
//        Example 1:
//
//        Input: [2,3,2]
//        Output: 3
//        Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2),
//        because they are adjacent houses.
//        Example 2:
//
//        Input: [1,2,3,1]
//        Output: 4
//        Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
//        Total amount you can rob = 1 + 3 = 4.

package com.nitesh.array;
import java.util.Arrays;

public class robInCircle {
    public int robInCircleFn(int[] nums) {
        int len=nums.length;
        if(len==0)
            return 0;
        if(len==1)
            return nums[0];
        if(len==2)
            return Math.max(nums[0], nums[1]);
        // (nums, from, to) where "from: is inclusive whereas "to" is exclusive while performing copy
        int robExcludeLastHouse = robEach(Arrays.copyOfRange(nums, 0, len-1));
        int robExcludeFirstHouse = robEach(Arrays.copyOfRange(nums, 1, len));
        return Math.max(robExcludeLastHouse, robExcludeFirstHouse);
    }

    private int robEach(int[] nums) {
        int len=nums.length;
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
