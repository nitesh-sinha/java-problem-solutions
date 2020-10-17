//        Recover Binary Search Tree:
//        Two elements of a binary search tree (BST) are swapped by mistake.
//
//        Recover the tree without changing its structure.
//
//        Example 1:
//
//        Input: [1,3,null,null,2]
//
//          1
//         /
//        3
//         \
//          2
//
//        Output: [3,1,null,null,2]
//
//          3
//         /
//        1
//         \
//          2
//
//        Example 2:
//
//        Input: [3,1,4,null,null,2]
//
//          3
//         / \
//        1   4
//           /
//          2
//
//        Output: [2,1,4,null,null,3]
//
//          2
//         / \
//        1   4
//           /
//          3
//
//        Note:
//        A solution using O(n) space is pretty straight forward. Could you devise a constant space solution?

package com.nitesh.binaryTree;

public class recoverTree {
    // there are two out of order nodes(first and second)
    private TreeNode first=null, second=null;
    // we need to hold the prev node value across recursive calls during inorder traversal
    private TreeNode prevNode = new TreeNode(Integer.MIN_VALUE);

    public void recoverTreeFn(TreeNode root) {
        if(root==null)
            return;

        inOrderTraverse(root);

        // Found both nodes to swap. Lets swap them
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }

    private void inOrderTraverse(TreeNode root) {
        if(root==null)
            return;

        inOrderTraverse(root.left);
        // 2 out of order(OOO) cases.
        // Case 1: When both OOO nodes are next to each other(Eg: 1,3,2,4)
        //         Here (curNode < prevNode) will be true only once.
        //         So, update both first and second at this instance.
        // Case 2: When OOO nodes are not next to each other(Eg: 5,3,4,2,6,7)
        //         Here (curNode < prevNode) will be true twice
        //         So, update first and second at first instance. Re-update second on second instance.
        if(first==null && prevNode.val > root.val)
            // first out of order node in inorder traversal
            first = prevNode;

        if(first!=null && prevNode.val > root.val)
            // second out of order node in inorder traversal
            second = root;

        prevNode = root; // update prev for next recursive call
        inOrderTraverse(root.right);
    }
}
