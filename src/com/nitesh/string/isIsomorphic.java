//        Two strings are isomorphic if the characters in s can be replaced to get t.
//
//        All occurrences of a character must be replaced with another character while preserving the order of characters.
//        No two characters may map to the same character but a character may map to itself.
//
//        For example,
//        Given "egg", "add", return true.
//
//        Given "foo", "bar", return false.
//
//        Given "paper", "title", return true.

// Time complexity: O(n) where n=length of string(assuming both strings are of same length)

package com.nitesh.string;

import java.util.HashMap;
import java.util.Map;

public class isIsomorphic {
    public boolean isIsomorphicFn(String s, String t) {
        return checkIsomorphic(s, t) && checkIsomorphic(t, s);
    }

    private boolean checkIsomorphic(String s1, String s2) {
        if(s1.length() != s2.length())
            return false;

        Map<Character, Character> replaceMap = new HashMap<>();
        for(int i = 0; i<s1.length(); i++) {
            if(replaceMap.containsKey(s1.charAt(i))) {
               if(replaceMap.get(s1.charAt(i)) != s2.charAt(i))
                   return false;
            } else {
                replaceMap.put(s1.charAt(i), s2.charAt(i));
            }
        }
        return true;
    }
}
