//Problem: Move zeroes to the end of the array
//
//[]nums = {1,0,0,3,0,4,5,0} -> {1,3,4,5,0,0,0,0}
//
//
//
//Time complexity: O(n) where n=length of nums


package com.nitesh.array;

public class moveZeroes {
    public void moveZeroesFn(int[] nums) {
        int holeIndex=0, numIndex=0, temp, numLength = nums.length;
        while (numIndex < numLength) {
            holeIndex = getHoleIndex(nums, holeIndex);
            numIndex = getNumIndex(nums, numIndex);

            // swap hole with number and increment hole index by 1
            if (holeIndex < numIndex && numIndex < numLength) {
                temp = nums[holeIndex];
                nums[holeIndex] = nums[numIndex];
                nums[numIndex] = temp;
                holeIndex++;
            }
            // increment numIndex always by 1
            numIndex++;
        }
    }

    private int getHoleIndex(int[] nums, int holeIndex) {
        while(holeIndex < nums.length && nums[holeIndex] != 0) {
            holeIndex++;
        }
        return holeIndex;
    }

    private int getNumIndex(int[] nums, int numIndex) {
        while(numIndex < nums.length && nums[numIndex] == 0) {
            numIndex++;
        }
        return numIndex;
    }
}
