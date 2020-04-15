package com.nitesh.linkedList;

// Definition for singly-linked list.
public class ListNode {
		int val;
		ListNode next;
		ListNode(int x) { val = x; }

		public static ListNode createLinkedList(int[] arr) {
		        if(arr.length == 0)
		                return null;
		        ListNode head = new ListNode(arr[0]);
		        ListNode ptr = head, temp;
		        for(int i=1; i<arr.length; i++) {
		                temp = new ListNode(arr[i]);
		                ptr.next = temp;
		                ptr = ptr.next;
                }
		        ptr.next = null; // last node of linked list
                return head;
        }

		public static void printLinkedList(ListNode head) {
		        if(head == null)
		                return;
                System.out.printf("%d", head.val); // print first node
		        ListNode ptr = head.next;
		        while(ptr != null) {
                    System.out.printf(" ---> %d ", ptr.val);
                    ptr = ptr.next;
                }
                System.out.println();
		}
}
