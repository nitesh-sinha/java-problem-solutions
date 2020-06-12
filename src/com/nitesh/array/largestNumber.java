//        Given a list of non negative integers, arrange them such that they form the largest number.
//
//        For example, given [3, 30, 34, 5, 9], the largest formed number is 9534330.
//
//        Note: The result may be very large, so you need to return a string instead of an integer.

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
        for(int i=0; i<len; i++) {
            numStrs[i] = String.valueOf(nums[i]);
            if(nums[i] != 0)
                allZeros = false;
        }

        // Special case to return "0" instead of repeating zeros like "0000"
        if(allZeros)
            return "0";

        Comparator<String> comp = new Comparator<String>(){
            @Override
            public int compare(String s1, String s2) {
                String s3 = s1 + s2;
                String s4 = s2 + s1;
                return s4.compareTo(s3);
            }
        };

        Arrays.sort(numStrs, comp);

        // Create final resultant string since numbers are already sorted
        StringBuilder res = new StringBuilder();
        for(String s : numStrs)
            res.append(s);

        return res.toString();
    }
}
