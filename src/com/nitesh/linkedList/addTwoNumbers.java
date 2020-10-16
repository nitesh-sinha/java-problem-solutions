//        You are given two non-empty linked lists representing two non-negative integers. The digits are stored in
//        reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked
//        list.
//
//        You may assume the two numbers do not contain any leading zero, except the number 0 itself.
//
//        Example:
//
//        Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
//        Output: 7 -> 0 -> 8
//        Explanation: 342 + 465 = 807.
//
package com.nitesh.linkedList;

public class addTwoNumbers {
    public ListNode addTwoNumberFn(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode temp, resPtr=dummy;
        int firstNumDigit, secondNumDigit, carry=0, sum;

        while(l1!=null || l2!=null) {
            firstNumDigit = (l1!=null) ? l1.val : 0;
            secondNumDigit = (l2!=null) ? l2.val : 0;
            sum = firstNumDigit + secondNumDigit + carry;
            carry = (sum > 9) ? 1 : 0;
            sum = (sum > 9) ? (sum % 10) : sum;
            resPtr.next=new ListNode(sum);;
            resPtr = resPtr.next;
            if(l1 != null)
                l1 = l1.next;
            if(l2 !=null)
                l2 = l2.next;
        }

        // Handle carry from sum of last two digits
        // Note: By default, next pointer of new node is null.
        if(carry>0)
            resPtr.next = new ListNode(carry);
        return dummy.next;
    }
}
