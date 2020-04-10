//        Write a function to find the longest common prefix string amongst an array of strings.
//
//        If there is no common prefix, return an empty string "".
//
//        Example 1:
//
//        Input: ["flower","flow","flight"]
//        Output: "fl"
//        Example 2:
//
//        Input: ["dog","racecar","car"]
//        Output: ""
//        Explanation: There is no common prefix among the input strings.
//        Note:
//
//        All given inputs are in lowercase letters a-z.

// Time complexity: Worst case O(S) where S is sum of all chars in all strings.

package com.nitesh.string;

public class longestCommonPrefix {
    // Algo: Horizontal scanning. Idea is LCP(S1, ....., Sn) = LCP(LCP(....LCP(S1, S2),S3)...Sn)
    public String longestCommonPrefixFn(String[] strs) {
        if(strs.length==0)
            return "";
        String commonPrefix = strs[0];
        for(int i=1; i<strs.length; i++) {
            while(strs[i].indexOf(commonPrefix) != 0) {
                commonPrefix = commonPrefix.substring(0, commonPrefix.length()-1);
                if(commonPrefix.isEmpty())
                    return "";
            }
        }
        return commonPrefix;
    }
}
