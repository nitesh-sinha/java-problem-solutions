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
            List<String> parensList = generateParenthesisDPFn(n-1);
            for(String paren : parensList) {
                parenLen = paren.length();
                //char[] parenChrs = new char[parenLen];
                //parenChrs = paren.toCharArray();
                for(int i=0; i< parenLen; i++) {
                    newParen = paren.substring(0, i+1) + "()" + paren.substring(i+1, parenLen);
                    parenSet.add(newParen);
                }
            }
            return new ArrayList<String>(parenSet);
        }
    }
}
