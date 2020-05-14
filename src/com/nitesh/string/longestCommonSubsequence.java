//        Given two strings text1 and text2, return the length of their longest common subsequence.
//
//        A subsequence of a string is a new string generated from the original string with some characters(can be none)
//        deleted without changing the relative order of the remaining characters. (eg, "ace" is a subsequence of "abcde"
//        while "aec" is not). A common subsequence of two strings is a subsequence that is common to both strings.
//
//        If there is no common subsequence, return 0.
//
//        Example 1:
//
//        Input: text1 = "abcde", text2 = "ace"
//        Output: 3
//        Explanation: The longest common subsequence is "ace" and its length is 3.
//        Example 2:
//
//        Input: text1 = "abc", text2 = "abc"
//        Output: 3
//        Explanation: The longest common subsequence is "abc" and its length is 3.
//        Example 3:
//
//        Input: text1 = "abc", text2 = "def"
//        Output: 0
//        Explanation: There is no such common subsequence, so the result is 0.
//
//
//        Constraints:
//
//        1 <= text1.length <= 1000
//        1 <= text2.length <= 1000
//        The input strings consist of lowercase English characters only.


package com.nitesh.string;

public class longestCommonSubsequence {
    // Solution 2: Dynamic programming(To understand this algo, check solution 1 in comment below)
    public int longestCommonSubsequenceFn(String text1, String text2) {
        int len1 = text1.length(), len2 = text2.length();
        if(len1==0 || len2==0)
            return 0;

        int[][] lcs = new int[1+len1][1+len2]; // lcs[i][j] stores the longest common subsequence of text1[0...i-1], text2[0...j-1], chars at both ends inclusive.

        for(int i=1; i<=len1; i++) {
            for(int j=1; j<=len2; j++) {
                // If first char of both strings match, then lcs is 1+lcs of both strings starting from 2nd char
                // Note this follows directly from algorithm of LCS described below in Solution 1(except that here we're
                // starting match from first char instead of last char) but the result will be identical.
                if(text1.charAt(i-1) == text2.charAt(j-1))
                    lcs[i][j] = 1 + lcs[i-1][j-1];
                else
                    lcs[i][j] = Math.max(lcs[i][j-1], lcs[i-1][j]);
            }
        }

        return lcs[len1][len2];
    }
}


// Solution 1: Recursive approach(Time complexity: O(2^n) where n=sum of chars in text1 and text2)
//             Note that there are multiple sub-problems which are repeating in this solution.
//
//    public int longestCommonSubsequence(String text1, String text2) {
//        int len1 = text1.length(), len2 = text2.length();
//        if(len1==0 || len2==0)
//            return 0;
//
//        return lcs(text1, text2, len1, len2);
//    }
//
//    private int lcs(String text1, String text2, int len1, int len2) {
//        if(len1 == 0 || len2 == 0)
//            return 0;
//
//        if(text1.charAt(len1-1) == text2.charAt(len2-1))
//            // if last chars of strings match, then lcs is 1+lcs(Strings without last char)
//            // eg: text1="abcde" and text2="ace". Since last char 'e' matches, result = 1+lcs("abcd", "ac")
//            return 1 + lcs(text1, text2, len1-1, len2-1);
//        else
//            // if last chars don't match, then get max of the two terms:
//            // First Term: lcs of 1st input string upto len1 and 2nd string upto len2 but excluding last character
//            // Second Term: lcs of 1st string upto len1 but without last char and 2nd input string
//            // eg: text1="efgh" and text2="ace". Since last char 'h' doesnt matche 'e', result =
//            // max(lcs("efgh", "ac"), lcs("efg", "ace"))
//            return Math.max(lcs(text1, text2, len1, len2-1), lcs(text1, text2, len1-1, len2));
//    }