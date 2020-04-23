//        Given a binary tree, return the inorder traversal of its nodes' values.
//
//        Example:
//
//        Input: [1,null,2,3]
//        1
//         \
//          2
//         /
//        3
//
//        Output: [1,3,2]


package com.nitesh.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import com.nitesh.binaryTree.TreeNode;

public class iterativeInOrderTreeTraversal {
    // Similar to a recursive solution, store all left nodes in a user-stack
    // When left node becomes null, store the current node in result list
    // Repeat the same for curNode.right
    // Basically we are following LEFT->ROOT->RIGHT pattern
    public List<Integer> iterativeInOrderTreeTraversalFn(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode element;

        if(root==null)
            return res;

        pushAllLeftSubtree(root, stack);
        while(!stack.isEmpty()) {
            element = stack.pop();
            res.add(element.val);
            pushAllLeftSubtree(element.right, stack);
        }
        return res;
    }

    private void pushAllLeftSubtree(TreeNode root, Stack<TreeNode> s) {
        if(root==null)
            return;
        while(root!=null) {
            s.push(root);
            root = root.left;
        }
    }
}
