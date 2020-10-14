//        Count no. of inversions in an array:
//        How far (or close) the array is from being sorted. If array is already sorted then inversion count is 0. If array is
//        sorted in reverse order then inversion count is the maximum. Formally speaking, two elements a[i] and a[j] form an
//        inversion if a[i] > a[j] for i < j.
//
//     Example 1:
//           Input:  nums = {5,3,2,1}
//           Output: 6 (since there are 6 inversions --> 3<5, 2<3, 2<5, 1<5, 1<3, 1<2)
//
//
// Time complexity: O(n*log n)

package com.nitesh.array;

public class countInversions {
    private int count=0;
    public int countInversionsFn(int[] nums) {
        if(nums.length <= 1)
            return 0;

        countInversionsFn(nums, 0, nums.length-1);
        return count;
    }

    // Uses merge sort algorithm to count inversions
    private void countInversionsFn(int[] nums, int low, int high) {
        if(low >= high)
            return;
        int mid = low + (high - low)/2;
        countInversionsFn(nums, low, mid);
        countInversionsFn(nums, mid+1, high);
        merge(nums, low, mid, high);
    }

    private void merge(int[] nums, int low, int mid, int high) {
        int[] outBuff = new int[nums.length]; // work with this array and copy it to nums at the end
        int lPtr = low, rPtr = mid+1, buffIndex = 0;
        while(lPtr <= mid && rPtr <= high) {
            if(nums[lPtr] <= nums[rPtr])
                outBuff[low + buffIndex] = nums[lPtr++];
            else {
                // merge() function expects subarrays [0,mid] and [mid+1, high] to be sorted/
                // So, when nums[lPtr] > nums[rPtr] then all elements after lPtr in left subarray will also be greater than nums[rPtr].
                // So, include those counts too in inversion.
                count += (mid - lPtr + 1);
                outBuff[low + buffIndex] = nums[rPtr++];
            }
            buffIndex++;
        }

        // copy remaining elements from left subarray into output buffer
        while(lPtr <= mid) {
            outBuff[low + buffIndex] = nums[lPtr++];
            buffIndex++;
        }

        // copy remaining elements from right subarray into output buffer
        while(rPtr <= high) {
            outBuff[low + buffIndex] = nums[rPtr++];
            buffIndex++;
        }
        // copy it to nums so that it gets partially sorted and next call to merge()
        // can use that partially sorted elements instead of using the input nums(which is unsorted)
        System.arraycopy(outBuff, low, nums, low, high-low+1);
    }
}
