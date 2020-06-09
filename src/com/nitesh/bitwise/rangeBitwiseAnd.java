//        Bitwise AND of number range: Given a range [m, n] where 0 <= m <= n <= 2147483647, return the
//        bitwise AND of all numbers in this range, inclusive.
//
//    Example 1: Input: m = 4, n = 7
//               Output: 4
//               Explanation: 100 AND 101 AND 110 AND 111 = 100 (i.e. 4)
//
//    Example 2: Input: m = 4, n = 8
//               Output: 0
//               Explanation: 0100 AND 0101 AND 0110 AND 0111 AND 1000 = 0000 (i.e. 0)
//
// Time complexity: O(32) or O(64) in worst case scenario for 32/64 bit int size.

package com.nitesh.bitwise;

public class rangeBitwiseAnd {
    public int rangeBitwiseAndFn(int m, int n) {
        if(m == 0 || n == 0)
            return 0;

        int numRightShift = 0;
        // Nullify rightmost bits in m and n until they are equal
        // We nullify rightmost bit since either that bit in m or n is 0.
        // AND'ing the numbers in range [m,n] will mean AND with 0 on rightmost bit.
        // Hence result will be 0.
        while(m != n) {
            m = m >> 1;
            n = n >> 1;
            numRightShift++;
        }

        // New m to be shifted left(add zeros to its right) by the number of times it was shifted right before.
        return m * (int)Math.pow(2, numRightShift);
    }
}
