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
