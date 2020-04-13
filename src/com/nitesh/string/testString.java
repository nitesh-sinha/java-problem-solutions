package com.nitesh.string;

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

        String y = "1.2.03";
        System.out.println("backslash \\ is an escape character in java string literal. So escape it with another backslash");
        String[] res = y.split("\\.");
        System.out.println(res.length);
        for(String s : res)
            System.out.println(Integer.parseInt(s));


    }
}
