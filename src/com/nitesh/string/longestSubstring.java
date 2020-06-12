//        Longest Substring with At Least K Repeating Characters: Find the length of the longest substring T of a
//        given string (consists of lowercase letters only) such that every character in T appears no less than k times.
//
//        Example 1:
//        Input:
//        s = "aaabb", k = 3
//        Output:
//        3
//        The longest substring is "aaa", as 'a' is repeated 3 times.
//
//
//        Example 2:
//        Input:
//        s = "ababbc", k = 2
//        Output:
//        5
//        The longest substring is "ababb", as 'a' is repeated 2 times and 'b' is repeated 3 times.


package com.nitesh.string;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class longestSubstring {
    public int longestSubstringFn(String s, int k) {
        // System.out.println("Now processing " + s + " for k=" + k);
        if(s.length() < k || k <= 0)
            return 0;

        Map<Character, Integer> charCount = new HashMap<>();
        Set<Character> splitChars = new HashSet<>(); // stores those characters which cannot be a part of substring(as their frequency < k)
        int count, maxLen=0, start, cur;

        // Create a map of characters with their corresponding frequencies
        for(int i=0; i<s.length(); i++) {
            count = charCount.getOrDefault(s.charAt(i), 0);
            charCount.put(s.charAt(i), 1+count);
        }

        // Obtain all split characters in input string
        for(Map.Entry<Character, Integer> entry : charCount.entrySet()) {
            if (entry.getValue() < k)
                splitChars.add(entry.getKey());
        }

        // if no split chars found, it means all chars in input string s can be used for longest substring length calculation
        // This also serves as an exit condition from recursive calls later
        if(splitChars.isEmpty())
            return s.length();

        for(start=0, cur=0; cur < s.length(); cur++) {
            if(splitChars.contains(s.charAt(cur))) {
                if(cur > start)
                    // reached a split character. Recursively check for longest substring length in left section of string from [start, cur)
                    // [ -> include this char
                    // ) -> exclude this char
                    maxLen = Math.max(maxLen, longestSubstringFn(s.substring(start, cur), k));
                start = cur + 1; // move start to one beyond the current char and begin to capture new substring to see if a max length one exists(on the right of split char)
            }
        }

        // Due to the condition of loop above(cur < s.length), last section of substring
        // is unprocessed for max length. So, perform that check and update maxLen, if needed.
        // Eg: If original input string s = "aabacbbb", k=2, then the substring "bbb" will remain unprocessed.
        maxLen = Math.max(maxLen, longestSubstringFn(s.substring(start, cur), k));
        // System.out.println("    Max len found for this was " + maxLen);
        return maxLen;
    }
}
