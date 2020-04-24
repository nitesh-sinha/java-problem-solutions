//      Given a binary tree, return its level order traversal of its node's values(i.e. from left to right of 1st level,
//      then left to right of next level and so on)
//      Example:    Given binary tree [3,9,20,null,null,15,7],
//            3
//           / \
//          9  20
//         /     \
//        15      7
//
//      Return its level order traversal as:
//          3, 9, 20, 15, 7
//

package com.nitesh.binaryTree;

import java.util.ArrayList;
import java.util.List;

public class levelOrderTraversal {
    public List<Integer> levelOrderTraversalFn(TreeNode root) {
        List<Integer> levelOrderList = new ArrayList<>();
        int height;
        height = getHeight(root);
        for(int i=0; i< height; i++)
            getValuesAtLevel(root, i, levelOrderList);
        return levelOrderList;
    }

    private int getHeight(TreeNode root) {
        int leftHeight, rightHeight;
        if(root == null)
            return 0;
        else {
            leftHeight = getHeight(root.left);
            rightHeight = getHeight(root.right);
            return 1 + Math.max(leftHeight, rightHeight);
        }
    }

    private void getValuesAtLevel(TreeNode root, int level, List<Integer> levelOrderList) {
        if(root == null)
            return;
        if(level == 0)
            levelOrderList.add(root.val);
        else {
            getValuesAtLevel(root.left, level-1, levelOrderList);
            getValuesAtLevel(root.right, level-1, levelOrderList);
        }
    }
}
