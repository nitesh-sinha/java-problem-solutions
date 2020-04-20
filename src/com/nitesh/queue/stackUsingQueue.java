//        Implement the following operations of a stack using queues.
//
//        push(x) -- Push element x onto stack.
//        pop() -- Removes the element on top of the stack.
//        top() -- Get the top element.
//        empty() -- Return whether the stack is empty.
//        Example:
//
//        MyStack stack = new MyStack();
//
//        stack.push(1);
//        stack.push(2);
//        stack.top();   // returns 2
//        stack.pop();   // returns 2
//        stack.empty(); // returns false
//        Notes:
//
//        You must use only standard operations of a queue -- which means only push to back, peek/pop from front, size, and is empty operations are valid.
//        Depending on your language, queue may not be supported natively. You may simulate a queue by using a list or
//        deque (double-ended queue), as long as you use only standard operations of a queue.
//        You may assume that all operations are valid (for example, no pop or top operations will be called on an empty stack).

// Time complexity:
// Push: O(1)
// Pop: Worst case: O(n) where n=no. of elements in custom stack
// top: O(1)
// Empty: O(1)


package com.nitesh.queue;

import java.util.LinkedList;
import java.util.Queue;

public class stackUsingQueue {
    // Push element x onto stack.
    private Queue<Integer> queue1 = new LinkedList<>();
    private Queue<Integer> queue2 = new LinkedList<>();
    private int top; // stores last element inserted to MyStack

    public void push(int x) {
        queue1.add(x);
        top = x;
    }

    // Removes the element on top of the stack.
    public int pop() {
        int element;
        Queue<Integer> temp;

        while(queue1.size() > 2)
            queue2.add(queue1.remove());

        // Update top using second last element in queue1
        if(queue1.size() == 2) {
            top = queue1.remove();
            queue2.add(top);
        } else {
            top = Integer.MIN_VALUE; // reset top to null
        }

        // remove last element in queue1
        element = queue1.remove();

        // Swap queue1 and queue2
        temp=queue1;
        queue1=queue2;
        queue2=temp;
        return element;
    }

    // Get the top element.
    public int top() {
        if(!queue1.isEmpty())
            return top;
        return Integer.MIN_VALUE; // null value
    }

    // Return whether the stack is empty.
    public boolean empty() {
        return queue1.isEmpty();
    }
}
