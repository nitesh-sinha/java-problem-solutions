//        Longest Substring Without Repeating Characters:
//
//        Given a string, find the length of the longest substring without repeating characters.
//
//        Examples:
//
//        Given "abcabcbb", the answer is "abc", with the length of 3.
//
//        Given "bbbbb", the answer is "b", with the length of 1.
//
//        Given "pwwkew", the answer is "wke", with the length of 3. Note that the answer must be a substring, "pwke" is a subsequence and not a substring.

// Time complexity: O(n) where n=length of String s.

package com.nitesh.string;

import java.util.HashMap;
import java.util.Map;

public class lengthOfLongestSubstring {
    public int lengthOfLongestSubstringFn(String s) {
        int substringStart=0, curLen=0, maxLen=0, lastIndex;
        Map<Character, Integer> letterIndex = new HashMap<>();

        for(int i=0; i< s.length(); i++) {
            char c = s.charAt(i);
            if(!letterIndex.containsKey(c)) {
                // character not seen before
                curLen++;
            } else {
                // character was seen before
                lastIndex = letterIndex.get(c);
                if(lastIndex >= substringStart) {
                    // this character is counted in longest substring
                    // So eliminate this character and start counting
                    // fresh from next character(of its last occurence)
                    substringStart = lastIndex+1;
                    curLen = i-substringStart+1;
                } else {
                    // this character is not counted in longest substring
                    // so dont reset curLen
                    // (Eg: this code path is taken when current char is the second "p" in input string pwwkeps")
                    curLen++;
                }
            }
            letterIndex.put(c, i);
            if(curLen>maxLen)
                maxLen=curLen;
        }
        return maxLen;
    }
}
