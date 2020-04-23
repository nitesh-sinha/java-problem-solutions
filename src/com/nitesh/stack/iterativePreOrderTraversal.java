package com.nitesh.stack;

import com.nitesh.binaryTree.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class iterativePreOrderTraversal {
    public List<Integer> preorderTraversalFn(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode element;

        if(root==null)
            return res;

        stack.add(root);
        while(!stack.isEmpty()) {
            element = stack.pop();
            res.add(element.val);
            // First push right child, then left child
            // since left needs to get popped before left child
            if(element.right != null)
                stack.add(element.right);
            if(element.left != null)
                stack.add(element.left);
        }
        return res;
    }
}
