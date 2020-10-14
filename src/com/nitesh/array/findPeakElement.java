//        A peak element is an element that is greater than its neighbors.
//
//        Given an input array nums, where nums[i] ≠ nums[i+1], find a peak element and return its index.
//
//        The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.
//
//        You may imagine that nums[-1] = nums[n] = -∞.
//
//        Example 1:
//
//        Input: nums = [1,2,3,1]
//        Output: 2
//        Explanation: 3 is a peak element and your function should return the index number 2.
//
//
//        Example 2:
//
//        Input: nums = [1,2,1,3,5,6,4]
//        Output: 1 or 5
//        Explanation: Your function can return either index number 1 where the peak element is 2,
//        or index number 5 where the peak element is 6.
//        Note:
//
//        Your solution should be in logarithmic complexity.


package com.nitesh.array;

public class findPeakElement {
    public int findPeakElementFn(int[] nums) {

        int l = 0, r = nums.length - 1;
        // Apply a modified binary search algorithm as
        // described in this lecture(starting at time 27:45):
        // https://www.youtube.com/watch?v=HtSuA80QTyo&list=PLUl4u3cNGP61Oq3tWYp6V_F-5jb5L2iHb&index=2&t=0s
        while (l < r) {
            int mid = (l + r) / 2;
            if (nums[mid] > nums[mid + 1]) // compare last element in left half with first element in right half
                // consider mid also while looking to its left for peak
                // since mid can be the peak element as well
                r = mid;
            else
                // ignore mid while looking to its right for peak
                // since mid can't be peak as it is smaller than
                // (mid+1)'th element already.
                l = mid+1;
        }
        return l;
    }
}
