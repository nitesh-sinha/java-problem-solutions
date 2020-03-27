package com.nitesh.stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class testStack {
    public void testfn() {
         Deque<Integer> stack = new ArrayDeque<>();
        //Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println("size = " + stack.size());
        System.out.println(stack.peek());
        System.out.println("size = " + stack.size());
        stack.add(100);
        stack.add(101);
        System.out.println("size = " + stack.size());
        System.out.println(stack.pop());
        System.out.println(stack.pop());

    }
}
