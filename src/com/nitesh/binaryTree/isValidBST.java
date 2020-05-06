//        Given a binary tree, determine if it is a valid binary search tree (BST).
//
//        Assume a BST is defined as follows:
//
//        The left subtree of a node contains only nodes with keys less than the node's key.
//        The right subtree of a node contains only nodes with keys greater than the node's key.
//        Both the left and right subtrees must also be binary search trees.
//
//
//        Example 1:
//
//          2
//         / \
//        1   3
//
//        Input: [2,1,3]
//        Output: true
//        Example 2:
//
//           5
//          / \
//         1   4
//            / \
//           3   6
//
//        Input: [5,1,4,null,null,3,6]
//        Output: false
//        Explanation: The root node's value is 5 but its right child's value is 4.


package com.nitesh.binaryTree;

public class isValidBST {
    public boolean isValidBSTFn(TreeNode root) {
        if(root==null)
            return true;

        // Use long min and max values so that lower and upper bounds work well
        // when actual node values are Integer.MIN_VALUE and/or Integer.MAX_VALUE
        return isValidBSTFn(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean isValidBSTFn(TreeNode root, long lower, long upper) {
        if(root==null)
            return true;

        if(root.val >= upper || root.val <= lower)
            return false;

        return isValidBSTFn(root.left, lower, root.val) &&
                isValidBSTFn(root.right, root.val, upper);
    }
}
