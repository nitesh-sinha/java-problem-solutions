//        Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
//
//        push(x) -- Push element x onto stack.
//        pop() -- Removes the element on top of the stack.
//        top() -- Get the top element.
//        getMin() -- Retrieve the minimum element in the stack.
//
//
//        Example 1:
//
//        Input
//        ["MinStack","push","push","push","getMin","pop","top","getMin"]
//        [[],[-2],[0],[-3],[],[],[],[]]
//
//        Output
//        [null,null,null,null,-3,null,0,-2]
//
//        Explanation
//        MinStack minStack = new MinStack();
//        minStack.push(-2);
//        minStack.push(0);
//        minStack.push(-3);
//        minStack.getMin(); // return -3
//        minStack.pop();
//        minStack.top();    // return 0
//        minStack.getMin(); // return -2
//
//
//        Constraints:
//        Methods pop, top and getMin operations will always be called on non-empty stacks.

// Time complexity for all operations: O(1)

package com.nitesh.stack;

import java.util.Stack;

public class minStack {
    private Stack<Integer> s; // stores all stack elements
    private Stack<Integer> minStack; // stores min seen until every push

    /** initialize your data structure here. */
    public minStack() {
        s = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int x) {
        // Push x to s stack
        // Push min(x, top_of_s_stack) to minStack
        s.push(x);
        int elem = x;
        if(!minStack.isEmpty())
            elem = Math.min(minStack.peek(), x);
        minStack.push(elem);
    }

    public void pop() {
        s.pop();
        minStack.pop();
    }

    public int top() {
        return s.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}
