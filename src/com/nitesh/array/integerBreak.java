//        Given a positive integer n, break it into the sum of at least two positive integers and maximize the product of those integers.
//        Return the maximum product you can get.
//
//        Example 1:
//
//        Input: 2
//        Output: 1
//        Explanation: 2 = 1 + 1, 1 × 1 = 1.
//        Example 2:
//
//        Input: 10
//        Output: 36
//        Explanation: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36.
//        Note: You may assume that n is not less than 2 and not larger than 58.

// Time complexity: O(n^2)

package com.nitesh.array;

public class integerBreak {
    // Dynamic programming solution
    public int integerBreakFn(int n) {
        int[] maxProdParts = new int[n+1]; // maxProdParts stores the max product of two or more integers adding up to 'i'
        maxProdParts[0] = 0;
        maxProdParts[1] = 1;
        maxProdParts[2] = 1;

        for(int i = 3; i<= n; i++) {
            maxProdParts[i] = Integer.MIN_VALUE;
            // For every i, go up to i/2(since after j=i/2, the pair j & (i-j) start to repeat)
            for(int j = 1; j <= (i/2); j++) {
                // Inner max is more subtle: Its needed so that we pick max of either the number or the parts which make up that number
                // So, get their max instead. Eg: If number=2 then dp[2]=1. If 2 is one of the +ve integers that makes up N, then we should select 2
                // i.e. (i-j) when considering the parts of N and not dp[i-j] i.e.dp[2]=1

                // Outer max is obvious. It is to select the max of the product of j with its prod parts of remainder i.e. maxProdParts[n-j]
                maxProdParts[i] = Math.max(maxProdParts[i], j * Math.max(i-j, maxProdParts[i-j]));
            }
        }
        return maxProdParts[n];
    }
}
