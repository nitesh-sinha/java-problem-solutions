//        Given an array of numbers nums, in which exactly two elements appear only once and all the other elements appear exactly twice.
//        Find the two elements that appear only once.
//
// Example 1: Input: nums = [2,3,5,6,2,3,5,6,1,9]
//            Output: [1,9]
//            Explanation: Since elements 1 and 9 appear only once. Other elements appear twice.




package com.nitesh.bitwise;

public class singleNumbers {
    public int[] singleNumbersFn(int[] nums) {
        int xorNums = 0, firstGroup = 0, secondGroup = 0;
        // XOR all elements in input.
        // Final result will be XOR of the two numbers(say A and B) which appear only once(since all duplicate elements will cancel out in XOR)
        for(int num : nums)
            xorNums = xorNums ^ num;

        // xorNums will have bits set only in those positions where bits in elements A and B differ
        // To identify A from B, create a new number C with just the rightmost set bit in xorNums
        // Then bitwise AND this new number C with all numbers.
        // This will separate the two numbers A and B into two different groups
        // Bitwise XOR both groups amongst themselves to obtain A and B(since all other numbers in both groups will cancel out by XOR'ing)
        xorNums = xorNums & ~(xorNums - 1); // this will reset all bits except rightmost set bit

        for(int num : nums) {
            if((xorNums & num) == 0) // AND the number C with all elements and segregate into two groups
                firstGroup = firstGroup ^ num; // XOR the incoming element with the elements in group
            else
                secondGroup = secondGroup ^ num;
        }
        int[] res = {firstGroup, secondGroup};
        return res;
    }
}
