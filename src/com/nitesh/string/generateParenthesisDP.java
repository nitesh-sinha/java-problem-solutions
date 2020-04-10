package com.nitesh.string;

import java.util.*;

public class generateParenthesisDP {
    public List<String> generateParenthesisDPFn(int n) {
        List<String> res = new ArrayList<>();
        if(n==0)
            return Arrays.asList("");
        else if(n==1)
            return Arrays.asList("()");
        else {
            int parenLen;
            Set<String> parenSet = new HashSet<>();
            String newParen;
            // Algorithm:
            // 1. Get balanced parenthesis list for n-1 given n
            // 2. For every such balanced parenthesis, do the following:
            // 2a.      Take first character of first balanced parenthesis expression, add (), then add remaining chars from the first expression.
            // 2b.      Take first 2 chars of first balanced parenthesis expression, add (), then add remaining chars from the first expression.
            // 2c.      Repeat this process until end of first balanced parenthesis expression.
            // 2d.      Repeat this process for all the remaining balanced parens expressions obtained from Step 1.
            // 3.       Store these in a Set since there will be duplicates as you perform steps 2a thru d. So, a Set will eliminate duplicates.
            List<String> parensList = generateParenthesisDPFn(n-1);
            for(String paren : parensList) {
                parenLen = paren.length();
                for(int i=0; i< parenLen; i++) {
                    newParen = paren.substring(0, i+1) + "()" + paren.substring(i+1, parenLen);
                    parenSet.add(newParen);
                }
            }
            return new ArrayList<String>(parenSet);
        }
    }
}
