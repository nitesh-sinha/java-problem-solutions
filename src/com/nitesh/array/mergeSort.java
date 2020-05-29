package com.nitesh.array;

public class mergeSort {
    public void mergeSortFn(int[] nums) {
        int len=nums.length;
        if(len <= 1)
            return;
        mergeSortFn(nums, 0, len-1);
    }

    private void mergeSortFn(int[] nums, int low, int high) {
        if(low >= high)
            return;

        int mid = low + (high - low)/2;
        //System.out.println("low = " + low + " mid = " + mid + " high = " + high);
        mergeSortFn(nums, low, mid);
        mergeSortFn(nums, mid+1, high);
        merge(nums, low, mid, high);
    }

    // Function that does the actual heavy-lifting of sorting the elements
    private void merge(int[] nums, int low, int mid, int high) {
        int[] outBuff = new int[nums.length]; // work with this array and copy it to nums at the end
        int lPtr = low, rPtr = mid+1, buffIndex = 0;
        while(lPtr <= mid && rPtr <= high) {
            if(nums[lPtr] <= nums[rPtr])
                outBuff[low + buffIndex] = nums[lPtr++];
            else
                outBuff[low + buffIndex] = nums[rPtr++];
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
        // copy it ot nums so that next call of merge() can use partially updated/sorted elements
        // instead of using the input nums(which is unsorted)
        System.arraycopy(outBuff, low, nums, low, high-low+1);
    }
}
