//        Given a binary tree, flatten it to a linked list in-place. Essentially nodes are flattened in root->left->right manner.
//
//        For example, given the following tree:
//
//           1
//          / \
//         2   5
//        / \   \
//       3   4   6
//
//        The flattened tree should look like:
//
//        1
//         \
//          2
//           \
//            3
//             \
//              4
//               \
//                5
//                 \
//                  6


package com.nitesh.binaryTree;

public class flatten {
    // object variable instead of function local variable since
    // once it is set within the function, we don't want to reset it in further recursive
    // iterations until we've used up the value set in it.
    TreeNode temp = null;

    public void flattenFn(TreeNode root) {
        if(root == null)
            return;
        flattenFn(root.left);
        flattenFn(root.right);
        // now left and right subtrees are flattened
        if(root.left != null) {
            // move left subtree to right link
            temp = root.right;
            root.right = root.left;
            root.left = null;
            // move pointer recursively to last node of right subtree
            // (so that we can append temp tree to it later)
            flattenFn(root.right);
        }
        if(temp != null) {
            // we're appending prev right(i.e. temp) subtree to
            // last node of cur right subtree
            root.right = temp;
            temp = null; // reset temp to null
        }
    }
}
