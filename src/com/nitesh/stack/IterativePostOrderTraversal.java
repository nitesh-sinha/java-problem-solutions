//        Given a binary tree, return the postorder traversal of its nodes' values.
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
//        Output: [3,2,1]
//

package com.nitesh.stack;

import com.nitesh.binaryTree.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class IterativePostOrderTraversal {
    // Algo is a little tricky because we store left->right in stack
    // and then pop them in right->left order and add them to head of list
    // thereby making it root->left->right
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> nodeStack = new Stack<>();

        if(root==null)
            return res;

        nodeStack.push(root);
        while(!nodeStack.isEmpty()) {
            TreeNode element = nodeStack.pop();
            res.add(0, element.val); // This is the trick

            // Since left is pushed to stack before right
            // and we add the popped item to the head of the list,
            // we get left->right->root order in list
            if(element.left != null)
                nodeStack.push(element.left);
            if(element.right != null)
                nodeStack.push(element.right);
        }
        return res;
    }
}
