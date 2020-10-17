//        Given a root node reference of a BST and a key, delete the node with the given key in the BST.
//        Return the root node reference (possibly updated) of the BST.
//
//        Basically, the deletion can be divided into two stages:
//
//        Search for a node to remove.
//        If the node is found, delete the node.
//
//        Example:
//
//        root = [5,3,6,2,4,null,7]
//        key = 3
//
//          5
//         / \
//        3   6
//       / \   \
//      2   4   7
//
//        Given key to delete is 3. So we find the node with value 3 and delete it.
//
//        One valid answer is [5,4,6,2,null,null,7], shown in the following BST.
//
//              5
//             / \
//            4   6
//           /     \
//          2       7
//
//        Another valid answer is [5,2,6,null,4,null,7].
//
//              5
//             / \
//            2   6
//             \   \
//              4   7
//
//
// Time complexity: O(height of tree).

package com.nitesh.binaryTree;

public class deleteNode {
    // This solution implements the first valid answer(described above)
    // So always replaces the key node with its inorder successor if
    // both left and right child for the key node exist.
    public TreeNode deleteNodeFn(TreeNode root, int key) {
        if(root==null)
            return null;

        if(key > root.val)
            // the new right child should be the modified
            // right subtree with key node deleted(if found)
            root.right = deleteNodeFn(root.right, key);
        else if(key < root.val)
            root.left = deleteNodeFn(root.left, key);
        else {
            // found node to be deleted
            // Case 1: No left child or leaf node
            if(root.left == null)
                return root.right;
            //Case 2: No right child
            if(root.right == null)
                return root.left;
            // Case 3: Both left and right child exist
            // Hence find smallest number larger than key node
            // i.e. inorder successor of key node and replace
            // key node with it.
            TreeNode nextMin = findNextMin(root);
            root.val = nextMin.val;
            // Since inorder successor of key is always found in right
            // subtree of key. Hence delete original nextMin node
            // and then the new right child of current node should be updated
            root.right = deleteNodeFn(root.right, nextMin.val);
        }
        return root;
    }

    // Gets the inorder successor of input root node
    private TreeNode findNextMin(TreeNode root) {
        TreeNode nextMin = root.right;
        while(nextMin.left != null)
            nextMin = nextMin.left;
        return nextMin;
    }
}
