//        Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists.
//
//        Example:
//
//        Input: 1->2->4, 1->3->4
//        Output: 1->1->2->3->4->4

// Time cimplexity: O(n1+n2) where n1=no. of nodes in l1, n2=no. of nodes in l2.

package com.nitesh.linkedList;

public class mergeTwoLists {
    public ListNode mergeTwoListsFn(ListNode l1, ListNode l2) {
        // Algo uses a dummy node in front.
        ListNode dummy = new ListNode(0);
        ListNode prev = dummy;

        while(l1!=null && l2!=null) {
            if(l1.val<=l2.val) {
                prev.next = l1;
                l1=l1.next;
            } else {
                prev.next=l2;
                l2=l2.next;
            }
            prev=prev.next;
        }

        // Check if any one list is non-empty
        if(l1==null)
            prev.next=l2;
        else
            prev.next=l1;
        return dummy.next;
    }
}
