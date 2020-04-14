//         Reverse a singly linked list.
//
//        Example:
//
//        Input: 1->2->3->4->5->NULL
//        Output: 5->4->3->2->1->NULL

package com.nitesh.linkedList;

public class reverseList {
    public ListNode reverseListFn(ListNode head) {
        // 0 or 1 node list
        if(head==null || head.next==null)
            return head;

        ListNode prev=null, cur=head, after;
        while(cur!=null) {
            //System.out.println(cur.val);
            after=cur.next;
            cur.next=prev;
            prev=cur;
            cur=after;
        }
        head=prev;
        return head;
    }
}
