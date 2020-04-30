//        Find the sum of all left leaves in a given binary tree.
//
//        Example:
//
//        3
//        / \
//        9  20
//        /  \
//        15   7
//
//        There are two left leaves in the binary tree, with values 9 and 15 respectively. Return 24.


package com.nitesh.binaryTree;

public class sumOfLeftLeaves {
    public int sumOfLeftLeaves(TreeNode root) {
        if(root==null)
            return 0;
        int doAdd=0; // 1 denotes add its value, 0 means don't add in sum
        return addLeftLeaves(root, doAdd);
    }

    private int addLeftLeaves(TreeNode root, int doAdd) {
        int sum;
        if(root==null)
            return 0;

        if(root.left==null && root.right==null) {
            if(doAdd==1)
                return root.val;
            else
                return 0;
        }

        return addLeftLeaves(root.left, 1) + addLeftLeaves(root.right, 0);
    }
}
