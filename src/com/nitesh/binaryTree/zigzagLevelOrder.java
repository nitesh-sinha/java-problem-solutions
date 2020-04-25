//        Binary Tree Zigzag Level Order Traversal: Given a binary tree, return the zigzag level order traversal of its nodes values.
//        (ie, from left to right, then right to left for the next level and alternate between).
//
//        For example:
//        Given binary tree [3,9,20,null,null,15,7],
//
//        3
//        / \
//        9  20
//        /  \
//        15   7
//
//        return its zigzag level order traversal as:
//
//        [
//        [3],
//        [20,9],
//        [15,7]
//        ]
//

package com.nitesh.binaryTree;

import java.util.ArrayList;
import java.util.List;

public class zigzagLevelOrder {
    // This problem can be approached using a very similar logic
    // presented in levelOrderTraversal.java. But here we chose
    // a slightly different approach. The comment below shows the
    // other logic.
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root==null)
            return res;

        getZigZag(root, res, 0);
        return res;
    }

    private void getZigZag(TreeNode root, List<List<Integer>> res, int level) {
        if(root==null)
            return;
        List<Integer> innerList;
        if(res.size()<=level) {
            // A new level is seen. Create its own level order list
            innerList = new ArrayList<>();
            res.add(innerList);
        }

        innerList = res.get(level);
        // Its a simple pre-order traversal of the tree nodes.
        // As we come across a node, we simply add it in the appropriate
        // list(marked for its level).
        if(level %2 == 0)
            innerList.add(root.val); // Add at the end of the list
        else
            innerList.add(0, root.val); // Add at the front of the list

        getZigZag(root.left, res, level+1);
        getZigZag(root.right, res, level+1);
    }
}



// Another solution(by calculating height of tree)

//    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
//        List<Integer> levelOrderList;
//        List<List<Integer>> zigzagList = new ArrayList<>();
//        int height;
//        height = getHeight(root);
//        for(int i=0; i< height; i++) {
//            levelOrderList = new ArrayList<>();
//            getValuesAtLevel(root, i, i, levelOrderList);
//            zigzagList.add(levelOrderList);
//        }
//
//        return zigzagList;
//    }
//
//    private int getHeight(TreeNode root) {
//        int leftHeight, rightHeight;
//        if(root == null)
//            return 0;
//        else {
//            leftHeight = getHeight(root.left);
//            rightHeight = getHeight(root.right);
//            return 1 + Math.max(leftHeight, rightHeight);
//        }
//    }
//
//    private void getValuesAtLevel(TreeNode root, int origLevel, int curLevel, List<Integer> levelOrderList) {
//        if(root == null)
//            return;
//        if(curLevel == 0) {
//            if(origLevel % 2 == 0)
//                levelOrderList.add(root.val); // add at end of list
//            else
//                levelOrderList.add(0, root.val); // add at beginning of list
//        }   else {
//            getValuesAtLevel(root.left, origLevel, curLevel-1, levelOrderList);
//            getValuesAtLevel(root.right, origLevel, curLevel-1, levelOrderList);
//        }
//    }