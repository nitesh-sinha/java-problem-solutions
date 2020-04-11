package com.nitesh.string;

import java.util.LinkedList;
import java.util.List;

public class letterCombinations {
    public List<String> letterCombinationsFn(String digits) {
        LinkedList<String> res = new LinkedList<>();
        String[] mapping = {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        if(digits.length()==0)
            return res;
        res.add("");

        for(int i=0;i<digits.length();i++) {
            // Iterate over every digit in input
            int d = Character.getNumericValue(digits.charAt(i));
            while(res.peek().length()==i) {
                // Iterate over all existing elements in the queue
                // Existing elements are all possible combinations of
                // i length strings for the mapping of equivalent i-length digits from input.
                // Eg: If input string="234", queue will be filled in this order
                // 1st iteration: [a,b,c]
                // 2nd iteration: remove a,b,c and add [ad,ae,af,bd,be,bf,cd,ce,cf]
                // 3rd iteration: remove existing 2 length elements and add [adg,adh,adi,aeg,aeh,aei,...cfg,cfh,cfi]
                String t = res.remove();
                for(char x : mapping[d].toCharArray())
                    // Add every character in the mapping of the digit to all exisiting elements in the queue
                    res.add(t+x);
            }
        }
        return res;
    }
}
