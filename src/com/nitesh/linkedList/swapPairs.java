//        Given a linked list, swap every two adjacent nodes and return its head.
//
//        You may not modify the values in the list's nodes, only nodes itself may be changed.
//
//        Example:
//
//        Given 1->2->3->4, you should return the list as 2->1->4->3.
//
// Time complexity: O(n) where n=no.of nodes in linked list.

package com.nitesh.linkedList;

public class swapPairs {
    public ListNode swapPairsFn(ListNode head) {
        // 0 or 1 node
        if(head==null || head.next==null)
            return head;

        ListNode first=head, second=first.next, prevFirst=null;
        head = head.next;
        while(first!=null && second!=null) {
            // fix link from previous pair swap to current pair
            if(prevFirst!=null)
                prevFirst.next=second;

            // swap links of node pairs
            first.next=second.next;
            second.next=first;

            // move pointers to next two pairs of nodes
            prevFirst=first; // always points to the first of the two pairs which were swapped already
            first=first.next;
            if(first!=null)
                second=first.next;
        }
        return head;
    }
}
