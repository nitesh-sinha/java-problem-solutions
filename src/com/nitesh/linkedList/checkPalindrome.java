// Given a linkedlist of characters which form a string, check whether the string is palindrome.
//
//  Example 1: Input: s -> w -> a -> w -> s
//             Output: true
//
//  Example 2: Input : p -> e -> n
//             Output: false
//
// Time complexity: O(n) where n = number of nodes in linked list.
// Space complexity: O(1)

package com.nitesh.linkedList;

public class checkPalindrome {
    public boolean checkPalindromeFn(ListNode head) {
        if(head == null || head.next == null)
            return true;

        ListNode slowPtr = head, fastPtr = head.next, reverse;
        while(fastPtr != null && fastPtr.next != null) {
            slowPtr = slowPtr.next;
            fastPtr = fastPtr.next.next;
        }
        // slowPtr is at the mid of linkedlist
        // Reverse section of linkedlist one beyond slowPtr node
        reverseList rev = new reverseList();
        reverse = rev.reverseListFn(slowPtr.next);
        slowPtr.next = null;

        // Check for palindrome amongst lists pointed by head and reverse
        for(slowPtr=head, fastPtr=reverse; fastPtr!=null; slowPtr=slowPtr.next, fastPtr=fastPtr.next) {
            if(slowPtr.val != fastPtr.val)
                return false;
        }
        // checked all nodes; none found unequal
        return true;
    }
}
