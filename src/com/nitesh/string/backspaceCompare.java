//        Given two strings S and T, return if they are equal when both are typed into empty text editors. # means a backspace character.
//
//        Example 1:
//
//        Input: S = "ab#c", T = "ad#c"
//        Output: true
//        Explanation: Both S and T become "ac".
//        Example 2:
//
//        Input: S = "ab##", T = "c#d#"
//        Output: true
//        Explanation: Both S and T become "".
//        Example 3:
//
//        Input: S = "a##c", T = "#a#c"
//        Output: true
//        Explanation: Both S and T become "c".
//        Example 4:
//
//        Input: S = "a#c", T = "b"
//        Output: false
//        Explanation: S becomes "c" while T becomes "b".
//        Note:
//
//        1 <= S.length <= 200
//        1 <= T.length <= 200
//        S and T only contain lowercase letters and '#' characters.

// Time complexity: O(N) where N= sum of chars in S and T
// Space complexity: O(N)

package com.nitesh.string;

public class backspaceCompare {
    public boolean backspaceCompareFn(String S, String T) {
        return (getParsedString(S).equals(getParsedString(T)));
    }

    private String getParsedString(String X) {
        int hashCount = 0;
        String finalX = "";

        for(int i=X.length()-1; i>=0; i--) {
            if(X.charAt(i) != '#' && hashCount==0)
                finalX = X.charAt(i) + finalX;
            else {
                while(i>=0 && X.charAt(i) == '#') {
                    hashCount++;
                    i--;
                }

                while(hashCount>0 && i>=0 && X.charAt(i) != '#') {
                    hashCount--;
                    i--;
                }
                i++;
            }
        }
        return finalX;
    }
}
