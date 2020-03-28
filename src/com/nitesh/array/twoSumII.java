//        Given an array of integers that is already sorted in ascending order, find two numbers such that they add up to a specific target number.
//
//        The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2.
//
//        Note:
//
//        Your returned answers (both index1 and index2) are not zero-based.
//        You may assume that each input would have exactly one solution and you may not use the same element twice.
//        Example:
//
//        Input: numbers = [2,7,11,15], target = 9
//        Output: [1,2]
//        Explanation: The sum of 2 and 7 is 9. Therefore index1 = 1, index2 = 2.


package com.nitesh.array;
import java.util.Arrays;

public class twoSumII {
    public int[] twoSumIIFn(int[] numbers, int target) {
        int searchIndx=-1,i;

        for(i=0; i<numbers.length-1; i++) {
            if(target<numbers[i]) {
                // pairs cannot be found anymore
                return new int[] {0,0};
            }
            // BinarySearch includes fromIndex(2nd arg) but excludes toIndex(3rd arg) from search
            searchIndx = Arrays.binarySearch(numbers, i+1, numbers.length, target-numbers[i]);
            if(searchIndx>=0)
                break;
        }
        return new int[] {i+1, searchIndx+1};
    }

}
