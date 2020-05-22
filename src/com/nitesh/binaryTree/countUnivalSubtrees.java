//        Implement a method to count the number of unival subtrees. A unival subtree is a subtree within a tree with root node and all its
//        children nodes(upto the leaf node) have the same value. Note that all leaf nodes are considered unival subtrees.
//
//
//        Eg:
//           5
//          / \
//         5   5
//            /  \
//           5    5
//
//        The above binary tree has five unival subtrees namely:
//        1. Three leaf nodes
//        2. The right subtree with root at 5(at level 1) and its two child leaf nodes as 5
//        3. Entire binary tree rooted at 5(at level 0)

// Time complexity: O(n) where n = no. of nodes of the input tree

package com.nitesh.binaryTree;

public class countUnivalSubtrees {
    private int count = 0;

    public int countUnivalSubtreesFn(TreeNode root) {
        if(root==null)
            return count;

        countUnival(root);
        return count;
    }

    private boolean countUnival(TreeNode root) {
        if(root == null)
            return true;

        boolean isLeftUnival = countUnival(root.left);
        boolean isRightUnival = countUnival(root.right);

        if(root.left != null && root.val != root.left.val)
            return false;

        if(root.right != null && root.val != root.right.val)
            return false;

        if(isLeftUnival && isRightUnival) {
            count++;
            return true;
        }
        return false;
    }
}
