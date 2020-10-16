//        Write a program to find the node at which the intersection of two singly linked lists begins.
//          Eg: l1 = a1, a2, c1, c2, c3
//              l2 = b1, b2, b3, c1, c2, c3
//              These intersect at c1 node
//
//        Notes:
//
//        If the two linked lists have no intersection at all, return null.
//        The linked lists must retain their original structure after the function returns.
//        You may assume there are no cycles anywhere in the entire linked structure.
//        Your code should preferably run in O(n) time and use only O(1) memory.

// Time complexity: O(n) where n=size of the larger length list

package com.nitesh.linkedList;

public class getIntersectionNode {
    public ListNode getIntersectionNodeFn(ListNode headA, ListNode headB) {
        int numNodesA, numNodesB, diffLength;
        ListNode ptrLong=headA, ptrShort=headB; // start with this assumption

        numNodesA = getNumNodes(headA);
        numNodesB = getNumNodes(headB);

        if (numNodesA<numNodesB) {
            // correct the initial assumption
            ptrLong = headB;
            ptrShort = headA;
        }

        // Move ptrLong ahead by diffLength nodes
        diffLength = Math.abs(numNodesA-numNodesB);
        while(diffLength>0) {
            ptrLong = ptrLong.next;
            diffLength--;
        }

        // Move both pointers at same speed now
        while(ptrLong != null && ptrLong != ptrShort) {
            ptrLong = ptrLong.next;
            ptrShort = ptrShort.next;
        }
        return ptrLong;
    }

    private int getNumNodes(ListNode head) {
        int count=0;
        while(head!=null) {
            head=head.next;
            count++;
        }
        return count;
    }
}
