//        Given a binary tree, determine if it is a complete binary tree.
//
//        Definition of a complete binary tree from Wikipedia:
//        In a complete binary tree every level, except possibly the last, is completely filled, and all nodes in the
//        last level are as far left as possible. It can have between 1 and 2^h nodes(inclusive) at the last level h.
//
//        Example 1:
//
//        Given the following tree [3,9,20,null,null,15,7]:
//
//            3
//           / \
//          9  20
//            /  \
//           15   7
//        Return false(since 15 and 7 in last level are not as left as possible).
//
//
//        Example 2:
//
//        Given the following tree [3,9,20,null,null,15,7]:
//
//            3
//           / \
//          9  20
//        /  \
//       15   7
//        Return true.(Since all nodes are as far left as possible).

// Time complexity: O(n) - where n=number of nodes of tree
// Space complexity: O(n)

package com.nitesh.binaryTree;

import java.util.LinkedList;
import java.util.Queue;

public class isCompleteTree {
    public boolean isCompleteTreeFn(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        TreeNode node;
        if(root==null)
            return true;

        queue.add(root);

        while(queue.size() > 0) {
            node = queue.remove();
            if(node==null) {
                // pop out remaining items in queue to ensure they are null
                while(queue.size()>0) {
                    if(queue.remove()!=null)
                        // found a non-null after a NULL. Hence, not complete tree
                        return false;
                }
                // all remaining items in queue were null. Hence, complete tree
                return true;
            }
            queue.add(node.left); // this can be NULL too
            queue.add(node.right); // this can be NULL too
        }
        return true;
    }
}
