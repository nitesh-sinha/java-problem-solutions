//        A robot is located at the top-left corner of an m x n grid.
//        The robot can only move either down or right at any point in time.
//        The robot is trying to reach the bottom-right corner of the grid.(1<=m,n<=100)
//
//        How many possible unique paths are there?
//
//        Note: Simple question although the logic involves the use of BigInteger since we are working on very large integers.
//        Basically the robot has to go down "m-1" steps and go right "n-1" steps to go from top-left grid to bottom-right grid.
//        So the questions asks the number of possible ways in which we can arrange (m-1) Right's and (n-1) Down's.
// Time complexity: O(m+n)


package com.nitesh.array;
import java.math.BigInteger;

public class uniquePaths {
    public int uniquePathsFn(int m, int n) {
        // A classic example of permutation with repetition problem(essentially in how many ways can we arrange
        // m+n-2 items where m-1 items are similar and remaining n-1 items are similar.
        // Result = (m-1+n-1)! / ((m-1)! * (n-1)!)
        BigInteger[] fact = new BigInteger[m+n];
        BigInteger res;

        getFact(m+n-2, fact);
        res = (fact[m+n-2].divide(fact[m-1])).divide(fact[n-1]);
        return res.intValue();
    }

    private void getFact(int val, BigInteger[] fact) {
        fact[0] = BigInteger.ONE;
        for(int i=1;i<=val;i++)
            fact[i] = fact[i-1].multiply(BigInteger.valueOf(i));
    }
}
