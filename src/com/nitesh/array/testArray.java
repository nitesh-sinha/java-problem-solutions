package com.nitesh.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class testArray {

    public void testfn() {
        LinkedList<Integer> l1 = new LinkedList<>();
        l1.add(1);
        l1.add(2);
        l1.add(3);
        System.out.println(l1.pop());
        System.out.println("num at index 1 is " + l1.get(1));
        l1.remove(1);
        System.out.println("num at index 0 is " + l1.get(0));
        System.out.println("size = " + l1.size());
        l1.clear();
        System.out.println("size = " + l1.size());
        System.out.println(l1.peek());

    }

}
