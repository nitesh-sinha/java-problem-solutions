//    Sum of two integers without using addition or subtraction mathematical operators.
//
//    Example: Input: a = 10, b = 13.
//             Output: 23



// Remember full adder circuit
// XOR gate gives sum(without carry)
// AND gate gives carry
// Explanation of the algorithm below:
//    Iteration 1:
//    ============
//    a=1010 (= 10 Integer)
//    b=1101 (= 13 Integer)
//
//    Iteration 2:
//    ==============
//    sum=1010 ^ 1101 = 0111
//    b=10000
//    a=0111
//
//    Iteration 3:
//    =============
//    sum=00111 ^ 10000 = 10111
//    b=0
//    a=10111 -> result (= 23 Integer)
//

package com.nitesh.bitwise;

public class addTwoNumbers {
    public int addTwoNumbersFn(int a, int b) {
        int sum = 0;
        while(b != 0) {
            sum = a ^ b; // bitwise XOR
            b = (a & b) << 1; // save carry for next iteration
            a = sum; // save sum for next iteration
        }
        return sum;
    }
}
