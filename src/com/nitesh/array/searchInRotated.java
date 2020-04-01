//        Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
//
//        (i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).
//
//        You are given a target value to search. If found in the array return its index, otherwise return -1.
//
//        You may assume no duplicate exists in the array.
//
//        Your algorithm's runtime complexity must be in the order of O(log n).
//
//        Example 1:
//
//        Input: nums = [4,5,6,7,0,1,2], target = 0
//        Output: 4
//        Example 2:
//
//        Input: nums = [4,5,6,7,0,1,2], target = 3
//        Output: -1


package com.nitesh.array;
import java.util.Arrays;

public class searchInRotated {
    public int searchInRotatedFn(int[] nums, int target) {
        // Find pivot index i.e where nums[pivot] < nums[pivot-1]
        // Check which side of pivot does target lie
        // Perform binary search on that side in an attempt to find target

        if(nums.length==0)
            return -1;

        int low=0, high=nums.length-1, mid, pivot, searchIndex;

        while(nums[low] > nums[high]) {
            mid = low + (high-low)/2;
            if(nums[mid] > nums[high])
                // pivot is on right half
                low = mid+1;
            else
                high = mid; // not (mid-1) since mid can be pivot index too
        }

        // low has the pivot
        pivot=low;
        if(target >= nums[0] && pivot!=0) {
            low = 0;
            high = pivot-1;
        } else {
            low = pivot;
            high = nums.length-1;
        }
        searchIndex = Arrays.binarySearch(nums, low, high+1, target);
        if(searchIndex >= 0)
            return searchIndex;
        return -1;
    }
}
