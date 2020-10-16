package com.nitesh.linkedList;

public class testLinkedList {
    public void testfn() {
        int[] arr1 = {1,2,3,4,5};
        ListNode head, reorderedList;
        head        = ListNode.createLinkedList(arr1);
        reorderList x = new reorderList();
        reorderedList = x.reorderListFn(head);
        ListNode.printLinkedList(reorderedList);
    }
}
