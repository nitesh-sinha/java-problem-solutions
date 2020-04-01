//        Given a sorted array of integers, find the starting and ending position of a given target value.
//
//        If the target is not found in the array, return [-1, -1].
//
//        For example,
//        Given [5, 7, 7, 8, 8, 10] and target value 8,
//        return [3, 4].


//      Time complexity: O(log n)

package com.nitesh.array;

public class searchRange {
    // Two binary searches. Elegant solution
    public int[] searchRangeFn(int[] nums, int target) {
        return helper(nums, target, 0, nums.length - 1);
    }
    private int[] helper(int[] nums, int target, int lo, int hi) {
        if (nums[lo] == target && nums[hi] == target)
            return new int[]{lo, hi};
        if (nums[lo] <= target && nums[hi] >= target) {
            int mid = lo + (hi - lo) / 2;
            int[] left = helper(nums, target, lo, mid);
            int[] right = helper(nums, target, mid + 1, hi);
            if (left[0] == -1) return right; // Not found in left half
            if (right[0] == -1) return left; // Not found in right half
            return new int[]{left[0], right[1]}; // Found in both half(so choose startIndex in left half & endIndex in right half)
        }
        // Not found in the array
        return new int[]{-1, -1};
    }
}
