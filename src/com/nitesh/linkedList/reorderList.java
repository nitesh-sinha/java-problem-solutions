//        Given a singly linked list L: L0→L1→…→Ln-1→Ln,
//        reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…
//
//        You may not modify the values in the list's nodes, only nodes itself may be changed.
//
//        Example 1:
//
//        Given 1->2->3->4, reorder it to 1->4->2->3.
//        Example 2:
//
//        Given 1->2->3->4->5, reorder it to 1->5->2->4->3.

package com.nitesh.linkedList;

public class reorderList {
    public ListNode reorderListFn(ListNode head) {
        // 0 or 1 node list
        if(head==null || head.next==null)
            return head;

        ListNode slow=head, fast=head;
        while(fast!=null && fast.next!=null) {
            fast=fast.next.next;
            slow=slow.next;
        }

        // slow is at midpoint now
        fast = reverse(slow.next);
        slow.next = null;
        slow = head;

        // start joining the two lists pointed to by slow and fast
        return merge(slow, fast);

    }

    // Reverses the list given its head
    // Returns the new head of the reversed list
    private ListNode reverse(ListNode head) {
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

    // Merges lists l1 and l2 interleaving alternate nodes
    private ListNode merge(ListNode l1, ListNode l2) {
        // Algo uses a dummy node in front.
        ListNode dummy = new ListNode(0);
        ListNode prev = dummy;
        boolean first=true;

        while(l1!=null && l2!=null) {
            if(first) {
                prev.next = l1;
                l1=l1.next;
                first=false;
            } else {
                prev.next=l2;
                l2=l2.next;
                first=true;
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
