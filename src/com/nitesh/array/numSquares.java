//        Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...)
//        which sum to n.
//
//        Example 1:
//
//        Input: n = 12
//        Output: 3
//        Explanation: 12 = 4 + 4 + 4.
//
//
//        Example 2:
//
//        Input: n = 13
//        Output: 2
//        Explanation: 13 = 4 + 9.

// Time complexity: O(n * sqrt(n)) where n= input number; sqrt(n) =no. of perfect squares less than or equal to n

package com.nitesh.array;

public class numSquares {
    // For more info: http://www.geeksforgeeks.org/minimum-number-of-squares-whose-sum-equals-to-given-number-n/
    // Dynamic programming solution is a lot faster for n>50
    public int numSquaresFn(int n) {
        // Dynamic programming solution
        if(n<=0)
            return 0;

        int[] numPerfectSq = new int[n+1]; // numPerfectSq[i] stores min. no. of perfect squares that add up to i
        numPerfectSq[0] = 0;
        numPerfectSq[1] = 1;

        for(int i=2 ; i<= n; i++) {
            numPerfectSq[i] = Integer.MAX_VALUE;
            for(int j=1; j*j <= i; j++)
                // Adding 1 here to consider the term (j*j) that we subtract from i here
                numPerfectSq[i] = Math.min(numPerfectSq[i], 1 + numPerfectSq[i-j*j]);
        }
        return numPerfectSq[n];
    }
}
