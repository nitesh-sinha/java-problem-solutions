//        Given a sorted linked list, delete all duplicates such that each element appear only once.
//
//        Example 1:
//
//        Input: 1->1->2
//        Output: 1->2
//        Example 2:
//
//        Input: 1->1->2->3->3
//        Output: 1->2->3

package com.nitesh.linkedList;

public class deleteDuplicates {
    public ListNode deleteDuplicatesFn(ListNode head) {
        if(head==null || head.next==null)
            return head;

        ListNode ptr1 = head, ptr2 = head.next;
        while(ptr2 != null) {
            while(ptr2 != null && ptr2.val == ptr1.val)
                ptr2 = ptr2.next;

            ptr1.next=ptr2;
            ptr1 = ptr1.next;
            if(ptr2!=null)
                ptr2 = ptr2.next;
        }
        return head;
    }
}
