//        Implement the following operations of a queue using stacks.
//
//        push(x) -- Push element x to the back of queue.
//        pop() -- Removes the element from in front of queue.
//        peek() -- Get the front element.
//        empty() -- Return whether the queue is empty.
//        Example:
//
//        MyQueue queue = new queueUsingStack();
//
//        queue.push(1);
//        queue.push(2);
//        queue.peek();  // returns 1
//        queue.pop();   // returns 1
//        queue.empty(); // returns false
//        Notes:
//
//        You must use only standard operations of a stack -- which means only push to top, peek/pop from top, size,
//        and is empty operations are valid.
//        Depending on your language, stack may not be supported natively. You may simulate a stack by using a list or
//        deque (double-ended queue), as long as you use only standard operations of a stack.
//        You may assume that all operations are valid (for example, no pop or peek operations will be called on an
//        empty queue).

// Time complexity:
// Push: O(1)
// Pop: Worst case: O(n) where n=no. of elements in queue, Amortized complexity: O(1)
// Peek: O(1)
// Empty: O(1)


package com.nitesh.stack;

import java.util.Stack;

public class queueUsingStack {
    private Stack<Integer> inStack = new Stack<Integer>();
    private Stack<Integer> outStack = new Stack<Integer>();
    private int front; // keeps track of first element in queue

    // Push element x to the back of queue.
    public void push(int x) {
        if(inStack.isEmpty())
            front = x;
        inStack.push(x);
    }

    // Removes the element from in front of queue.
    public int pop() {
        //int item;
        if (outStack.isEmpty())  {
            while(!inStack.empty())
                outStack.push(inStack.pop());
        }
        return outStack.pop();
    }

    // Get the front element.
    public int peek() {
        if(!outStack.isEmpty())
            return outStack.peek();
        return front;
    }

    // Return whether the queue is empty.
    public boolean empty() {
        return (outStack.empty() && inStack.empty());

    }
}
