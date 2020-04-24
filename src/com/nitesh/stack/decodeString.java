//        Given an encoded string, return its decoded string.
//
//        The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.
//
//        You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.
//
//        Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there won't be input like 3a or 2[4].
//
//        Examples:
//
//        s = "3[a]2[bc]", return "aaabcbc".
//        s = "3[a2[c]]", return "accaccacc".
//        s = "2[abc]3[cd]ef", return "abcabccdcdcdef".

package com.nitesh.stack;

import java.util.Stack;

public class decodeString {
    public String decodeStringFn(String s) {
        StringBuilder res = new StringBuilder();
        Stack<Integer> countStack = new Stack<>();
        Stack<String> strStack = new Stack<>();
        int count=0, repeatCount, index=0;

        if(s.length() == 0)
            return res.toString();

        while(index < s.length()) {
            if(Character.isDigit(s.charAt(index))) {
                while(index < s.length() && Character.isDigit(s.charAt(index))) {
                    count = count*10 + (s.charAt(index) - '0');
                    index++;
                }
                countStack.push(count);
                count = 0; // reset count for next iteration
            } else if(s.charAt(index) == '[') {
                // store the current result string onto stack
                System.out.println("Pushing " + res.toString());
                strStack.push(res.toString());
                res = new StringBuilder(); // reset the result string for next iteration
                index++;
            } else if(s.charAt(index) == ']') {
                StringBuilder tempStr = new StringBuilder();
                System.out.println("Popping " + res.toString());
                tempStr.append(strStack.pop());
                repeatCount = countStack.pop();
                while(repeatCount > 0) {
                    tempStr.append(res.toString());
                    repeatCount--;
                }
                res = tempStr;
                index++;
            } else {
                // character is an alphabet. Continue building the resultant string
                res.append(s.charAt(index));
                index++;
            }
        }
        return res.toString();
    }
}
