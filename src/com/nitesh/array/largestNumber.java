//        Given a list of non negative integers, arrange them such that they form the largest number.
//
//        For example, given [3, 30, 34, 5, 9], the largest formed number is 9534330.
//
//        Note: The result may be very large, so you need to return a string instead of an integer.

// Time complexity: O(n* log(n)) where n = length of input array

package com.nitesh.array;

import java.util.Arrays;
import java.util.Comparator;

public class largestNumber {
    public String largestNumberFn(int[] nums) {
        int len = nums.length;
        if(len == 0)
            return null;

        String[] numStrs = new String[len];
        boolean allZeros = true;
        // Convert int[] to String[]
        for(int i=0; i<len; i++) {
            numStrs[i] = String.valueOf(nums[i]);
            // Additional check to see if all nos. in input is zero.
            if(nums[i] != 0)
                allZeros = false;
        }

        // Special case to return "0" instead of repeating zeros like "0000"
        if(allZeros)
            return "0";

        // Custom comparator for sorting string array
        Comparator<String> comp = new Comparator<String>(){
            @Override
            public int compare(String s1, String s2) {
                String s3 = s1 + s2; // Assuming s1=3, s2=91 => s3 = 391
                String s4 = s2 + s1; // Assuming s1=3, s2=91 => s4 = 913
                // reverse the order of comparison since our intention is to get opposite of
                // the lexicographic order of s1 and s2
                return s4.compareTo(s3);
            }
        };
        // Solution overrides comparator used during sorting of strings thereby achieving the goal.
        Arrays.sort(numStrs, comp);

        // Create final resultant string since numbers are already sorted
        StringBuilder res = new StringBuilder();
        for(String s : numStrs)
            res.append(s);

        return res.toString();
    }
}
