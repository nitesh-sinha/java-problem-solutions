package com.nitesh.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class testString {
    public  void testStringFn() {
//        String str = "abc";
//        for (int i = 0; i < str.length(); i++) {
//            System.out.println(str.charAt(i)-97);
//        }

//        StringBuilder sb = new StringBuilder();
//        //sb.append("World");
//        sb.insert(0, "world");
//        sb.insert(0, "hellooooo ");
//        sb.append('c');
//        System.out.println(sb.toString());

//        String y = "1.2.03";
//        System.out.println("backslash \\ is an escape character in java string literal. So escape it with another backslash");
//        String[] res = y.split("\\.");
//        System.out.println(res.length);
//        for(String s : res)
////            System.out.println(Integer.parseInt(s));
//
//        String codes = "ford1, ford333, ford4, ford4a, ford4b, ford1a, ford2, ford333a";
//        String[] codeArr = codes.split(",");
//        List<String> group = new ArrayList<>();
//        for(String code : codeArr) {
//            code = code.trim();
//            for(int i=0; i<code.length(); i++) {
//                // Skip over all non digit char
//                while(i<code.length() && !Character.isDigit(code.charAt(i)))
//                    i++;
//
//                // reached a digit. Skip over all digits
//                while(i<code.length() && Character.isDigit(code.charAt(i)))
//                    i++;
//
//                if(i == code.length())
//                    // reached end of code i.e. no letter after the digits
//                    group.add(code);
//            }
//        }
//
//        for(int i=0; i<group.size(); i++)
//            System.out.println(group.get(i));


//        String[] x = {"sfo", "atl"};
//        List<String> xList = new ArrayList<>();
//
//        for(int i=0; i< x.length; i++) {
//            xList.add(x[i]);
//        }
//        String[] res = new String[x.length];
//        Arrays.sort(xList.toArray(res), new Comparator<String>() {
//            @Override
//            public int compare(String s1, String s2) {
//                System.out.println("Comparing " + s1 + " and  " + s2);
//                int result = -1;
//                // both s1 and s2 are of length 3
//                if (s1.charAt(0) > s2.charAt(0)) {
//                    System.out.println("1st char greater");
//                    result = 1;
//                } else if (s1.charAt(0) == s2.charAt(0) && s1.charAt(1) > s2.charAt(1)) {
//                    System.out.println("2nd char greater");
//                    result = 1;
//                } else if (s1.charAt(0) == s2.charAt(0) && s1.charAt(1) == s2.charAt(1) && s1.charAt(2) > s2.charAt(2)) {
//                    System.out.println("3rd char greater");
//                    result = 1;
//                }
//                return result;
//            }
//        });
//
//        for(String str : res)
//            System.out.println(str);

        isIsomorphic x = new isIsomorphic();
        System.out.println(x.isIsomorphicFn("egg", "add"));
    }
}
