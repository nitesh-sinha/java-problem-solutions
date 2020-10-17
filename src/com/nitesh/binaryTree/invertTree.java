//        Invert a binary tree.
//
//        Example:
//
//        Input:
//
//            4
//          /   \
//         2     7
//        / \   / \
//       1   3 6   9
//
//        Output:
//
//            4
//          /   \
//         7     2
//        / \   / \
//       9   6 3   1

package com.nitesh.binaryTree;

public class invertTree {
    public TreeNode invertTreeFn(TreeNode root) {
        if(root==null)
            return null;

        TreeNode invertedLeftTree = invertTreeFn(root.left);
        TreeNode invertedRightTree = invertTreeFn(root.right);

        root.left = invertedRightTree;
        root.right = invertedLeftTree;
        return root;
    }
}
