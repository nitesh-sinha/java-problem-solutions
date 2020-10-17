//        You are given a perfect binary tree where all leaves are on the same level, and every parent has two children.
//        The binary tree has the following definition:
//
//        struct Node {
//          int val;
//          Node *left;
//          Node *right;
//          Node *next;
//        }
//        Populate each next pointer to point to its next right node. If there is no next right node, the next pointer
//        should be set to NULL.
//
//        Initially, all next pointers are set to NULL.
//
//        Follow up:
//
//        You may only use constant extra space.
//        Recursive approach is fine, you may assume implicit stack space does not count as extra space for this problem.

//        Eg:
//
//        Given the following perfect binary tree,
//
//           1
//          /  \
//         2    3
//        / \  / \
//       4  5  6  7
//
//
//        After calling your function, the tree should look like:
//
//           1 -> NULL
//         /  \
//        2 -> 3 -> NULL
//       / \  / \
//      4->5->6->7 -> NULL


package com.nitesh.binaryTree;

public class connect {
    public Node connectFn(Node root) {
        if(root==null)
            return null;
        Node leftChild=null, rightChild=null;

        if(root.left!=null)
            leftChild = root.left;
        if(root.right!=null)
            rightChild = root.right;
        // make connections of all leftChild to rightChild
        while(leftChild!=null) {
            leftChild.next = rightChild;
            leftChild = leftChild.right;
            rightChild = rightChild.left;
        }
        // recurse for all left and right subtrees
        connectFn(root.left);
        connectFn(root.right);
        return root;
    }
}



// Another elegant solution(If you consider the list in one level as a linked list while iterating it,
//                          the solution is very obvious).
//public void connect(Node root) {
//    if(root==null)
//        return;
//
//    Node depth=root, level=null;
//    while(depth.left!=null) {
//        // depth ptr to iterate from top to bottom
//        level=depth;
//        while(level!=null) {
//            // level ptr to iterate over all nodes at same level
//            level.left.next=level.right;
//            if(level.next!=null)
//                level.right.next=level.next.left;
//            level=level.next;
//        }
//        depth=depth.left;
//    }
//}