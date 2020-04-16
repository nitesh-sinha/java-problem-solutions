//        Remove all elements from a linked list of integers that have value val.
//
//        Example:
//
//        Input:  1->2->6->3->4->5->6, val = 6
//        Output: 1->2->3->4->5

package com.nitesh.linkedList;

public class removeElements {
    public ListNode removeElementsFn(ListNode head, int val) {
        ListNode prev=null, cur=head;
        while(cur!=null) {
            // For val at the beginning of linked list
            if(head.val==val)
                head=head.next;

            if(cur.val==val) {
                if (prev==null) {
                    // head element to be removed
                    cur=cur.next;
                } else {
                    // non-head element to be removed
                    prev.next=cur.next;
                    cur.next=null;
                    cur=prev.next;
                }
            } else {
                // continue iterating
                prev=cur;
                cur=cur.next;
            }
        }
        return head;
    }
}
