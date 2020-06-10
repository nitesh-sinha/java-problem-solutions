//        Given a binary tree, return the bottom-up level order traversal of its nodes' values. (ie, from left to right, level by level from leaf to root).
//
//        For example:
//        Given binary tree [3,9,20,null,null,15,7],
//            3
//           / \
//          9  20
//            /  \
//           15   7
//        return its bottom-up level order traversal as:
//        [
//        [15,7],
//        [9,20],
//        [3]
//        ]

package com.nitesh.binaryTree;

import java.util.*;

public class iterativeLevelOrderBottom {
    public List<List<Integer>> iterativeLevelOrderBottomFn(TreeNode root) {
        List<List<Integer>> resList = new ArrayList<>();
        int numNodes = 0;
        if(root == null)
            return resList;

        Stack<List<Integer>> stack = new Stack<>();
        Queue<TreeNode> levelQ = new LinkedList<>();
        levelQ.add(root);
        while(!levelQ.isEmpty()) {
            List<Integer> levelOrderList = new ArrayList<>();
            numNodes = levelQ.size();
            for(int i = 0; i < numNodes; i++) {
                // iterate for all nodes in previous level
                TreeNode node = levelQ.remove();
                levelOrderList.add(node.val);
                if(node.left != null)
                    levelQ.add(node.left);
                if(node.right != null)
                    levelQ.add(node.right);
            }
            stack.push(levelOrderList); // push to stack so order of levels are reversed during pop later
        }

        // Pop from stack
        while(!stack.isEmpty())
            resList.add(stack.pop());

        return resList;
    }
}
