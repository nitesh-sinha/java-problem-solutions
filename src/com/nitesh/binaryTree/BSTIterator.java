//        Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root
//        node of a BST.
//
//        Calling next() will return the next smallest number in the BST.
//
//        Example:
//
//            7
//          /   \
//         3    15
//              / \
//             9   20
//
//        BSTIterator iterator = new BSTIterator(root);
//        iterator.next();    // return 3
//        iterator.next();    // return 7
//        iterator.hasNext(); // return true
//        iterator.next();    // return 9
//        iterator.hasNext(); // return true
//        iterator.next();    // return 15
//        iterator.hasNext(); // return true
//        iterator.next();    // return 20
//        iterator.hasNext(); // return false

//        Note:
//
//        next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.
//        You may assume that next() call will always be valid, that is, there will be at least a next smallest number
//        in the BST when next() is called.

package com.nitesh.binaryTree;

import java.util.Stack;

public class BSTIterator {
    private Stack<TreeNode> stack;

    public BSTIterator(TreeNode root) {
        stack = new Stack<>();
        pushAllLeft(root);
    }

    /** @return the next smallest number */
    public int next() {
        TreeNode elem = stack.pop();
        pushAllLeft(elem.right);
        return elem.val;
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return (stack.size()>0);
    }

    private void pushAllLeft(TreeNode root) {
        if(root==null)
            return;
        while(root!=null) {
            stack.push(root);
            root=root.left;
        }
    }
}
