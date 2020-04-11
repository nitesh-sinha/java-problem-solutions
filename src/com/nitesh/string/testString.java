package com.nitesh.string;

public class testString {
    public  void testStringFn() {
//        String str = "abc";
//        for (int i = 0; i < str.length(); i++) {
//            System.out.println(str.charAt(i)-97);
//        }

        StringBuilder sb = new StringBuilder();
        //sb.append("World");
        sb.insert(0, "world");
        sb.insert(0, "hellooooo ");
        sb.append('c');
        System.out.println(sb.toString());
    }
}
