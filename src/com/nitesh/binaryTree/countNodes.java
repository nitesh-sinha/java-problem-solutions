//        Given a complete binary tree, count the number of nodes.
//
//        Note:
//
//        Definition of a complete binary tree from Wikipedia:
//        In a complete binary tree, every level, except possibly the last, is completely filled, and all nodes in the
//        last level are as far left as possible. It can have between 1 and 2^h nodes(inclusive) at the last level h.
//
//        Example:
//
//        Input:
//          1
//         / \
//        2   3
//       / \  /
//      4  5 6
//
//        Output: 6

// Time complexity: Since we half the tree in every recursive step, we have O(log(n)) steps.
// Finding a height costs O(log(n)). So overall O((log(n))^2).

package com.nitesh.binaryTree;

public class countNodes {
    public int countNodesFn(TreeNode root) {
        int height;
        if(root==null)
            return 0;

        height = getHeight(root);
        // check if right subtree height is one less than current tree height
        if(getHeight(root.right) == (height-1)) {
            // leaf node in last level extends upto right subtree.
            // So count (root node of current tree + all nodes in left subtree)
            // and recurse to count nodes for right subtree
            return 1 + ((int)Math.pow(2, height) - 1) + countNodesFn(root.right);
        } else {
            // leaf node in last level ends in left subtree.
            // So count (root node of current tree + all nodoes in right subtree)
            // and recurse to count nodes for left subtree
            return 1 + ((int)Math.pow(2, height-1) - 1) + countNodesFn(root.left);
        }
    }

    // Height of  complete tree is number of edges along traversal to leftmost leaf node
    private int getHeight(TreeNode root) {
        if(root == null)
            return -1;
        return 1 + getHeight(root.left);
    }
}
