//        Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.
//
//        For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two
//        subtrees of every node never differ by more than 1.
//
//        Example:
//
//        Given the sorted linked list: [-10,-3,0,5,9],
//
//        One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:
//
//           0
//          / \
//        -3   9
//        /   /
//      -10  5
//
// Time complexity: O(n) where n=size of the linked list since each node is visited exactly once.
//

package com.nitesh.binaryTree;

import com.nitesh.linkedList.ListNode;

public class sortedListToBST {
    private ListNode ptr; // global pointer to iterate over the linked list
    public TreeNode sortedListToBSTFn(ListNode head) {
        if(head==null)
            return null;
        ListNode iter = head;
        ptr=head;
        int count=0, lo=0, high;
        while(iter!=null) {
            count++;
            iter = iter.next;
        }
        high = count-1;
        return createBSTinOrder(0, high);
    }

    // Create BST by traversing from start to end of "sorted" list, thereby picking
    // each node from list and creating the BST as we traverse in-order. In-order traversal
    // because that is the inherent nature of adding sorted numbers to BST
    private TreeNode createBSTinOrder(int lo, int high) {
        if(lo > high)
            return null;
        // Here lo, mid and high are not used for indexing into the linked list.
        // Rather they just serve as counters to check when to stop iterating recursively
        int mid = lo + (high - lo)/2;
        // LEFT
        TreeNode left = createBSTinOrder(lo, mid-1);

        // ROOT
        TreeNode bst = new TreeNode(ptr.val);
        bst.left = left;
        ptr = ptr.next; // move ptr to next node in linked list

        // RIGHT
        bst.right = createBSTinOrder(mid+1, high);

        return bst;
    }
}
