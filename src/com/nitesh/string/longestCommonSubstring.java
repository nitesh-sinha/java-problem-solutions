//        Given two strings X and Y, find the length of the longest common substring.
//        Examples :
//
//        Input : X = "GeeksforGeeks", y = "GeeksQuiz"
//        Output : 5
//        The longest common substring is "Geeks" and is of length 5.
//
//        Input : X = "abcdxyz", y = "xyzabcd"
//        Output : 4
//        The longest common substring is "abcd" and is of length 4.
//
//        Input : X = "zxabcdezy", y = "yzabcdezx"
//        Output : 6
//        The longest common substring is "abcdez" and is of length 6.

package com.nitesh.string;

public class longestCommonSubstring {
    public int longestCommonSubstringFn(String text1, String text2) {
        int len1 = text1.length(), len2 = text2.length(), maxLen = Integer.MIN_VALUE;
        if(len1==0 || len2==0)
            return 0;
        int[][] longestCommSubstr = new int[1+len1][1+len2]; // longestCommSubstr[i][j] stores the longest common substring length for text1[0...i-1], text2[0...j-1],  chars at both ends inclusive.

        for(int i=1; i<=len1; i++) {
            for(int j=1; j<=len2; j++) {
                if(text1.charAt(i-1) == text2.charAt(j-1)) {
                    longestCommSubstr[i][j] = 1 + longestCommSubstr[i-1][j-1];
                    maxLen = Math.max(maxLen, longestCommSubstr[i][j]);
                } else
                    // No match of chars
                    // hence longest substring length for this combination of i and j is 0
                    longestCommSubstr[i][j] = 0;
            }
        }
        return maxLen;
    }
}


//   Solution 1 : Recursive approach
//
//    public int longestCommonSubstringFn(String text1, String text2) {
//        int len1 = text1.length(), len2 = text2.length();
//        if(len1==0 || len2==0)
//            return 0;
//        int maxCommonLength = 0;
//        return longestCommSubStr(text1, text2, len1, len2,  maxCommonLength);
//    }
//
//    private int longestCommSubStr(String text1, String text2, int len1, int len2, int maxCommonLength) {
//        if(len1==0 || len2==0)
//            return maxCommonLength;
//
//        if(text1.charAt(len1-1) == text2.charAt(len2-1))
//            // if last chars of both string equal then increment count
//            // and repeat process for strings without that last matching char
//            return longestCommSubStr(text1, text2, len1-1, len2-1, maxCommonLength+1);
//        else
//            // if not equal, then check equality of chars in all possible substrings
//            // and get the max of all of them
//            return Math.max(maxCommonLength, Math.max(longestCommSubStr(text1, text2, len1, len2-1, 0),
//                    longestCommSubStr(text1, text2, len1-1, len2, 0)));
//    }