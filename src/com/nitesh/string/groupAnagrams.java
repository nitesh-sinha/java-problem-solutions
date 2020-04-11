//        Given an array of strings, group anagrams together.
//
//        For example, given: ["eat", "tea", "tan", "ate", "nat", "bat"],
//        Return:
//
//        [
//        ["ate", "eat","tea"],
//        ["nat","tan"],
//        ["bat"]
//        ]

// Time complexity: O(n * klog(k)) where n=no. of strings in input; k=sum of all chars in all input strings

package com.nitesh.string;

import java.util.*;

public class groupAnagrams {
    // Idea is to sort each string and check if it exists as key in HashMap
    // If it exists, then add the current string as value in hashmap
    // Else add current string as value in a new entry in hashmap
    public List<List<String>> groupAnagramsFn(String[] strs) {
        int numStrings=strs.length;
        Map<String, List<String>> sortedStrAnagrams = new HashMap<>(); // Key=sorted string, value=anagrams

        for(String str : strs) {
            char[] eachStr = str.toCharArray();
            Arrays.sort(eachStr);
            String sortedStr = new String(eachStr);
            if(sortedStrAnagrams.containsKey(sortedStr)) {
                List<String> existingList = sortedStrAnagrams.get(sortedStr);
                existingList.add(str);
                sortedStrAnagrams.put(sortedStr, existingList);
            } else {
                List<String> newList = new ArrayList<String>();
                newList.add(str);
                sortedStrAnagrams.put(sortedStr, newList);
            }
        }

        return new ArrayList<>(sortedStrAnagrams.values());
    }
}


// To improve time complexity, instead of sort we can use a string as key into HashMap.
// That string can be composed as follows:
// For 1st input string: key string = "#<count-of-a>#<count-of-b>#<count-of-c>#......#<count-of-z>"
// Eg: If first input is "aab", then key = "#2#1#0#0.....#0"