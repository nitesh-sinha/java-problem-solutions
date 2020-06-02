// Hamming weight of a number is the number of set bits in the binary representation of that number. Find the hamming weight of an input number n.
//
//   Example 1:
//              Input: n = 25
//              Output: 3
//              Explanation: 24 = Since there are 3 bits set in binary representation of 25 which is 11001
//
// Time complexity: O(k) where k = number of set bits in input number. Worst case: All bits are set. O(32)

package com.nitesh.bitwise;

public class hammingWeight {
    public int hammingWeightFn(int n) {
        int count;
        for(count = 0; n > 0; count++)
            n = n & (n-1); // this resets the rightmost set bit in n to zero
        return count;
    }
}



//  25 & 24 = 11001 & 11000 = 11000 (= 24), count = 1
//  24 & 23 = 11000 & 10111 = 10000 (= 16), count = 2
//  16 & 15 = 10000 & 01111 = 00000 (= 0),  count = 3