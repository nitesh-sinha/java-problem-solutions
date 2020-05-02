//        Binary tree right side view: Given a binary tree, imagine yourself standing on the right side of it,
//        return the values of the nodes you can see ordered from top to bottom.
//
//        For example:
//        Given the following binary tree,
//
//          1            <---
//        /   \
//       2     3         <---
//        \     \
//         5     4       <---
//        /
//       9
//
//        You should return [1, 3, 4, 9]
//
// Time complexity: O(n) where n = no. of nodes in the tree

package com.nitesh.binaryTree;

import java.util.ArrayList;
import java.util.List;

public class rightSideView {
    private List<Integer> rightView = new ArrayList<>();
    private int maxHeight=Integer.MIN_VALUE;

    public List<Integer> rightSideViewFn(TreeNode root) {
        int curHeight=0;
        traverseTree(root, curHeight);
        return rightView;
    }

    private void traverseTree(TreeNode root, int curHeight) {
        if(root==null)
            return;

        if(curHeight>maxHeight) {
            rightView.add(root.val);
            maxHeight = curHeight;
        }
        traverseTree(root.right, curHeight+1);
        traverseTree(root.left, curHeight+1);
    }
}
