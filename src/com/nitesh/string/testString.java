package com.nitesh.string;

import java.util.ArrayList;
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
//            System.out.println(Integer.parseInt(s));

        String codes = "ford1, ford333, ford4, ford4a, ford4b, ford1a, ford2, ford333a";
        String[] codeArr = codes.split(",");
        List<String> group = new ArrayList<>();
        for(String code : codeArr) {
            code = code.trim();
            for(int i=0; i<code.length(); i++) {
                // Skip over all non digit char
                while(i<code.length() && !Character.isDigit(code.charAt(i)))
                    i++;

                // reached a digit. Skip over all digits
                while(i<code.length() && Character.isDigit(code.charAt(i)))
                    i++;

                if(i == code.length())
                    // reached end of code i.e. no letter after the digits
                    group.add(code);
            }
        }

        for(int i=0; i<group.size(); i++)
            System.out.println(group.get(i));


    }
}
