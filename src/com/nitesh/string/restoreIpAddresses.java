//        Given a string containing only digits, restore it by returning all possible valid IP address combinations.
//
//        Example:
//
//        Input: "25525511135"
//        Output: ["255.255.11.135", "255.255.111.35"]

// Time complexity: Worst case: Inner most loop runs 3*3*3=27 times.

package com.nitesh.string;

import java.util.ArrayList;
import java.util.List;

public class restoreIpAddresses {
    public List<String> restoreIpAddressesFn(String s) {
        List<String> res = new ArrayList<>();
        String s1, s2, s3, s4;
        int len=s.length();

        // i,j and k divide the input into 4 substrings - (0,i),(i,j),(j,k),(k,len-1)
        // (0,i) covers first 3 chars or less(if input size < 12)
        // (i,j) covers next 3 characters or less(if input size < 12)
        // (j,k) covers the next 3 chars or less(if input size < 12)
        // (k,len) covers the remaining chars in input
        for(int i=1;i<4 && i<len-2;i++) {
            for(int j=i+1; j<i+4 && j<len-1;j++) {
                for(int k=j+1; k<j+4 && k<len;k++) {
                    s1=s.substring(0,i);
                    s2=s.substring(i,j);
                    s3=s.substring(j,k);
                    s4=s.substring(k,len);
                    if(isValid(s1) && isValid(s2) && isValid(s3) && isValid(s4))
                        res.add(s1+"."+s2+"."+s3+"."+s4);
                }
            }
        }
        return res;
    }

    private boolean isValid(String x) {
        if(x.length()>3 || (x.charAt(0)=='0' && x.length()>1) || Integer.parseInt(x)>255)
            return false;
        return true;
    }

}
