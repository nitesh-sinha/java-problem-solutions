//        Given a linked list, return the node where the cycle begins. If there is no cycle, return null.
//
//        Note: Do not modify the linked list.

package com.nitesh.linkedList;

public class detectCycle {
    public ListNode detectCycleFn(ListNode head) {
        // 0 node or 1 node(with cycle)
        if(head==null || head.next==head)
            return head;

        ListNode slow=head, fast=head.next;
        int numCycleNodes=1;
        while(fast!=null && fast.next!=null && slow!=fast) {
            slow=slow.next;
            fast=fast.next.next;
        }
        if(slow!=fast)
            // no cycle
            return null;

        //count no. of nodes in cycle
        slow=fast.next;
        while(slow!=fast) {
            numCycleNodes++;
            slow=slow.next;
        }

        // move fast to numCycleNodes ahead
        slow=head;
        fast=head;
        while(numCycleNodes>0) {
            fast=fast.next;
            numCycleNodes--;
        }

        // move both pointers at same speed until they meet at cycle start node
        while(slow!=fast) {
            slow=slow.next;
            fast=fast.next;
        }
        return slow;
    }
}
