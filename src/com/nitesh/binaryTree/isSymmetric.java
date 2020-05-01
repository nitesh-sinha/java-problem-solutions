//        Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
//
//        For example, this binary tree [1,2,2,3,4,4,3] is symmetric:
//
//              1
//             / \
//            2   2
//           / \ / \
//          3  4 4  3
//
//
//        But the following [1,2,2,null,3,null,3] is not:
//
//          1
//         / \
//        2   2
//         \   \
//         3    3

// Time complexity: O(n) where n=no. of nodes in tree. Since each node is visited once.


package com.nitesh.binaryTree;

public class isSymmetric {
    public boolean isSymmetricFn(TreeNode root) {
        if(root==null)
            return true;
        return isSymmetricFn(root.left, root.right);
    }

    private boolean isSymmetricFn(TreeNode leftTree, TreeNode rightTree) {
        if(leftTree==null && rightTree==null)
            return true;

        // only one of the two is null
        if((leftTree!=null && rightTree==null) || (leftTree==null && rightTree!=null))
            return false;

        if(leftTree.val != rightTree.val)
            return false;

        return (isSymmetricFn(leftTree.left, rightTree.right) &&
                isSymmetricFn(leftTree.right, rightTree.left));
    }
}
