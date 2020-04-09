//        Given a non-empty string check if it can be constructed by taking a substring of it and appending multiple copies of the substring together. You may assume the given string consists of lowercase English letters only and its length will not exceed 10000.
//
//
//
//        Example 1:
//
//        Input: "abab"
//        Output: True
//        Explanation: It's the substring "ab" twice.
//        Example 2:
//
//        Input: "aba"
//        Output: False
//        Example 3:
//
//        Input: "abcabcabcabc"
//        Output: True
//        Explanation: It's the substring "abc" four times. (And the substring "abcabc" twice.)

package com.nitesh.string;

public class repeatedSubstringPattern {
    public boolean repeatedSubstringPatternFn(String s) {
        int len=s.length(), numPatternBlocks;
        StringBuilder sb;
        // boolean isRepeating=false;

        for(int i=1; i<=len/2; i++) {
            sb = new StringBuilder();
            if(len%i == 0) {
                // possible repeating pattern
                numPatternBlocks = len/i;
                // Append this substr len/i times and check if you get input string
                for(int count=1; count<=numPatternBlocks; count++)
                    sb.append(s.substring(0,i));

                if(s.equals(sb.toString()))
                    return true;
            }
        }
        return false;
    }
}
