//        Given a binary tree, find its minimum depth.
//
//        The minimum depth is the number of nodes along the shortest path from the root node down to the nearest
//        leaf node.
//
//        Note: A leaf is a node with no children.
//
//        Example:
//
//        Given binary tree [3,9,20,null,null,15,7],
//
//          3
//         / \
//        9  20
//          /  \
//         15   7
//        return its minimum depth = 2.
//
// Time complexity: O(n) where n=no. of nodes in the tree since each node is visited exactly once.

package com.nitesh.binaryTree;

public class minDepth {
    public int minDepthFn(TreeNode root) {
        if (root==null)
            return 0;

        int leftDepth = minDepthFn(root.left);
        int rightDepth = minDepthFn(root.right);

        // Check if any one depth is zero
        // because zero depth means no path to leaf node
        if (leftDepth==0)
            return 1+rightDepth;

        if (rightDepth==0)
            return 1+leftDepth;

        // Compare depths only when both of them are non-zero
        if (leftDepth<rightDepth)
            return 1 + leftDepth;
        return 1 + rightDepth;
    }
}
