//        Given an unsorted array of integers, find the length of longest increasing subsequence.
//
//        Example:
//
//        Input: [10,9,2,5,3,7,101,18]
//        Output: 4
//        Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
//
//        Note:
//        There may be more than one LIS combination, it is only necessary for you to return the length.
//        Your algorithm should run in O(n^2) complexity.

// Time complexity: O(n^2)

package com.nitesh.array;

public class lengthOfLIS {
    public int lengthOfLIS(int[] nums) {
        // Dynamic programming solution
        int len=nums.length, max;
        if(len==0)
            return 0;

        int[] lis = new int[len]; // lis[i] is the length of LIS with num[i] as the last number in LIS

        // prepopulate lis with all 1's(Assuming input in decreasing order)
        for(int i=0;i<len;i++)
            lis[i]=1;

        for(int i=1;i<len;i++) {
            for(int j=0;j<i;j++) {
                if (nums[i]>nums[j] && lis[i]<lis[j]+1)
                    // multiple LIS might be possible when considering array ending with nums at i'th postion;
                    // so (lis[i]<lis[j]+1) condition ensures that we select the LIS which has the maximum
                    // number of numbers
                    lis[i]=lis[j]+1;
            }
        }

        // Pick max in the lis array
        max=lis[0];
        for(int i=1;i<len;i++)
            if(lis[i]>max)
                max=lis[i];

        return max;
    }
}


//    Follow up: Could you improve it to O(n log n) time complexity?
