//        Given a non-empty string s and a dictionary wordDict containing a list of non-empty words,
//        determine if s can be segmented into a space-separated sequence of one or more dictionary words.
//
//        Note:
//
//        The same word in the dictionary may be reused multiple times in the segmentation.
//        You may assume the dictionary does not contain duplicate words.
//        Example 1:
//
//        Input: s = "leetcode", wordDict = ["leet", "code"]
//        Output: true
//        Explanation: Return true because "leetcode" can be segmented as "leet code".
//        Example 2:
//
//        Input: s = "applepenapple", wordDict = ["apple", "pen"]
//        Output: true
//        Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
//        Note that you are allowed to reuse a dictionary word.
//        Example 3:
//
//        Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
//        Output: false

package com.nitesh.string;

import java.util.List;

public class wordBreak {
    public boolean wordBreakFn(String s, List<String> wordDict) {
        int len = s.length();
        // wordPossible[i] denotes whether substring(0,i) can be segmented using input dict
        boolean[] wordPossible = new boolean[len+1];

        wordPossible[0] = true;
        for(int i=1; i<=len; i++) {
            for(int j=0; j<i; j++) {
                if(wordPossible[j] && wordDict.contains(s.substring(j, i))) {
                    // if wordPossible[j] is true => substring(0,j) can be segmented using input dict
                    // Now if substring(j,i) is present in input dict, this implies substring(0,i) can be segmented too
                    wordPossible[i] = true;
                    break;
                }
            }
        }
        return wordPossible[len];
    }
}
