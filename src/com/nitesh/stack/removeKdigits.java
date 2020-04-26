//        Given a non-negative integer num represented as a string, remove k digits from the number so that the new number is the smallest possible.
//        Digits can be removed from any position i.e. removal need not always be of consecutive digits.
//
//        Note:
//        The length of num is less than 10002 and will be ≥ k.
//        The given num does not contain any leading zero.
//        Example 1:
//
//        Input: num = "1432219", k = 3
//        Output: "1219"
//        Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.
//        Example 2:
//
//        Input: num = "10200", k = 1
//        Output: "200"
//        Explanation: Remove the leading 1 and the number is 200. Note that the output must not contain leading zeroes.
//        Example 3:
//
//        Input: num = "10", k = 2
//        Output: "0"
//        Explanation: Remove all the digits from the number and it is left with nothing which is 0.
//
//        Input: num = "176349", k = 3
//        Output: "134"
//        Explanation: Remove the digits 7,6 and 9 from the number to get the smallest possible number 134.


package com.nitesh.stack;

import java.util.Stack;

public class removeKdigits {
    public String removeKdigitsFn(String num, int k) {
        Stack<Integer> digitStack = new Stack<>();
        int digit;
        StringBuilder res = new StringBuilder();

        // Idea: Delete digits from num in this order of preference:-
        // Case 1. Delete those digits which are larger than its immediate next digit. During this deletion,
        //         new number formed after deleting a digit should go through step 1 again.
        // Case 2. If k is still positive, then the resultant number has digits either equal or in increasing
        //         order when read from left to right. Now start to delete those digits from the right until k>0.

        if(k >= num.length())
            return "0";

        // Case 1
        for(int i=0; i<num.length(); i++) {
            digit = num.charAt(i) - '0';
            while(k > 0 && !digitStack.isEmpty() && digit < digitStack.peek()) {
                digitStack.pop();
                k--;
            }
            digitStack.push(digit);
        }

        // Case 2 (For cases like repeating or increasing digits. eg: "22222" or "12345")
        while(k > 0) {
            digitStack.pop();
            k--;
        }

        // Create resultant number
        while(!digitStack.isEmpty())
            res.append(digitStack.pop()); // append at the beginning
        res.reverse();

        // Remove leading zeros from result, if any
        // but stop at length 1 since a result containing
        // all 0's should return a 0 instead of ""
        while(res.length()>1 && res.charAt(0) == '0')
            res.deleteCharAt(0);

        return res.toString();
    }
}
