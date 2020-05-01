//        Given a binary tree, return all root-to-leaf paths.
//
//        Note: A leaf is a node with no children.
//
//        Example:
//
//        Input:
//
//           1
//         /   \
//        2     3
//         \
//          5
//
//        Output: ["1->2->5", "1->3"]
//
//        Explanation: All root-to-leaf paths are: 1->2->5, 1->3

// Time complexity: O(n) where n=no. of nodes in tree since each node is visited once.

package com.nitesh.binaryTree;

import java.util.ArrayList;
import java.util.List;

public class binaryTreePaths {
    private List<String> paths = new ArrayList<>();

    public List<String> binaryTreePathsFn(TreeNode root) {
        String path = "";
        getPaths(root, path);
        return paths;
    }

    private void getPaths(TreeNode root, String path) {
        if (root==null)
            return;

        path += String.valueOf(root.val);
        // Check for leafnode
        if(root.left==null && root.right==null) {
            paths.add(path);
            return;
        }
        // For non-leaf nodes
        path += "->";
        getPaths(root.left, path);
        getPaths(root.right, path);
    }
}
