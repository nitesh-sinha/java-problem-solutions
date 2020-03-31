//        Given a sorted integer array without duplicates, return the summary of its ranges.
//
//        Example 1:
//
//        Input:  [0,1,2,4,5,7]
//        Output: ["0->2","4->5","7"]
//        Explanation: 0,1,2 form a continuous range; 4,5 form a continuous range.
//        Example 2:
//
//        Input:  [0,2,3,4,6,8,9]
//        Output: ["0","2->4","6","8->9"]
//        Explanation: 2,3,4 form a continuous range; 8,9 form a continuous range.


package com.nitesh.array;
import java.util.ArrayList;
import java.util.List;

public class summaryRanges {
    public List<String> summaryRangesFn(int[] nums) {
        List<String> res = new ArrayList<String>();
        int i;

        if(nums.length==0)
            return res;

        String entry = String.valueOf(nums[0]);
        for(i=1;i<nums.length;i++) {
            if(nums[i]-nums[i-1] != 1) {
                if(!entry.equals(String.valueOf(nums[i-1])))
                    entry = entry + "->" + nums[i-1];
                res.add(entry);
                entry = String.valueOf(nums[i]);
            }
        }
        if(!entry.equals(String.valueOf(nums[i-1])))
            // add the last range of values
            entry = entry + "->" + nums[i-1];
        res.add(entry);
        return res;
    }
}
