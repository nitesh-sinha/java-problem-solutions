//        Given a nested list of integers, implement an iterator to flatten it.
//
//        Each element is either an integer, or a list -- whose elements may also be integers or other lists.
//
//        Example 1:
//        Given the list [[1,1],2,[1,1]],
//
//        By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,1,2,1,1].
//
//        Example 2:
//        Given the list [1,[4,[6]]],
//
//        By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,4,6]

package com.nitesh.stack;

import java.util.Iterator;
import java.util.List;
import java.util.Stack;


// This is the interface that allows for creating nested lists.
  // You should not implement it, or speculate about its implementation
interface NestedInteger {
      // @return true if this NestedInteger holds a single integer, rather than a nested list.
      public boolean isInteger();

      // @return the single integer that this NestedInteger holds, if it holds a single integer
      // Return null if this NestedInteger holds a nested list
      public Integer getInteger();

      // @return the nested list that this NestedInteger holds, if it holds a nested list
      // Return null if this NestedInteger holds a single integer
      public List<NestedInteger> getList();
}



public class nestedIterator implements Iterator<Integer> {
    private NestedInteger nextElem;
    private Stack<Iterator<NestedInteger>> iteratorStack;
    
    public nestedIterator(List<NestedInteger> nestedList) {
        iteratorStack = new Stack<>();
        iteratorStack.push(nestedList.iterator());
    }

    @Override
    public Integer next() {
        if(nextElem!=null)
            return nextElem.getInteger();
        return null;
    }

    @Override
    public boolean hasNext() {
        while(!iteratorStack.isEmpty()) {
            if(!iteratorStack.peek().hasNext())
                // end of list reached
                iteratorStack.pop();
            else if((nextElem = iteratorStack.peek().next()).isInteger())
                // ensures nextElem is an Integer object
                return true;
            else
                // nextElem is a NestedInteger; so push the iterator of the inner list
                iteratorStack.push(nextElem.getList().iterator());
        }
        return false;
    }
}


/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */