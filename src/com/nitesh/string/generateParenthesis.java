//        Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
//
//        For example, given n = 3, a solution set is:
//
//        [
//        "((()))",
//        "(()())",
//        "(())()",
//        "()(())",
//        "()()()"
//        ]

package com.nitesh.string;

import java.util.ArrayList;
import java.util.List;

public class generateParenthesis {
    public List<String> generateParenthesisFn(int n) {
        List<String> res = new ArrayList();
        generateParenthesis(res, "", 0, 0, n);
        return res;
    }

    public void generateParenthesis(List<String> res, String parens, int open, int close, int n) {
        // Intuition:
        // We can start an open bracket if we still have one (of n) left to place.
        // And we can start a close bracket if it would not exceed the number of open brackets.
        // Because for validity of expression: the net number of close brackets minus open brackets
        // must be less than 0 at any time(i.e. close brackets number should be less than open brackets number
        // at any time except end of expression) and  equal to 0 at the end of the expression.
        if (parens.length() == n * 2) {
            res.add(parens);
            return;
        }

        if (open < n) {
            generateParenthesis(res, parens+"(", open+1, close, n);
        }
        if (close < open) {
            generateParenthesis(res, parens+")", open, close+1, n);
        }
    }
}


//    Complexity Analysis
//
//    Our complexity analysis rests on understanding how many elements there are in generateParenthesis(n).
//    It turns out this is the n-th Catalan number 2nCn * 1/(n+1) which is bounded asymptotically 4^n/(n* sqrt(n))
//
//        Time Complexity : O(4^n/sqrt(n)). Each valid sequence has at most n steps during the backtracking procedure.
//
//        Space Complexity : O(4^n/sqrt(n)).