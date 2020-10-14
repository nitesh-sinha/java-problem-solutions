//        Given two arrays, write a function to compute their intersection.
//
//        Example 1:
//
//        Input: nums1 = [1,2,2,1], nums2 = [2,2]
//        Output: [2]
//
//
//        Example 2:
//
//        Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
//        Output: [9,4]
//
//        Note:
//
//        Each element in the result must be unique.
//        The result can be in any order.
//
//
// Time complexity: O(n1 * log(n2)) where n1=length of nums1 and n2=length of nums2

package com.nitesh.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class intersection {
    public int[] intersectionFn(int[] nums1, int[] nums2) {
        List<Integer> resList = new ArrayList<>();
        if(nums1.length==0 || nums2.length==0)
            return new int[0];

        Arrays.sort(nums1);
        Arrays.sort(nums2);

        for(int i=0; i < nums1.length; i++) {
            if(i>0 && nums1[i]==nums1[i-1])
                // compare only new nums in nums1 with nums2 elements
                continue;

            int searchIndx = Arrays.binarySearch(nums2, nums1[i]);
            if(searchIndx >= 0)
                resList.add(nums1[i]);
        }

        // Convert result list to array
        int[] res = new int[resList.size()];
        for(int i=0; i<resList.size(); i++)
            res[i] = resList.get(i);

        return res;
    }
}
