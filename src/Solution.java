import com.nitesh.array.*;
import com.nitesh.map.*;
import com.nitesh.stack.*;
import com.nitesh.string.*;
import com.nitesh.linkedList.*;
import com.nitesh.queue.*;
import com.nitesh.binaryTree.*;
import com.nitesh.bitwise.*;
import com.nitesh.customDataStruct.*;
import com.nitesh.multi.*;

import java.io.*;
import java.util.List;

public class Solution {

    public static void main(String[] args) {
//        ProducerConsumerExample x = new ProducerConsumerExample();
//        try {
//            x.PCTester();
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//        testLinkedList x = new testLinkedList();
//        x.testfn();
        testBitwise x = new testBitwise();
        x.testfn();

//        int[] inputArr = {1,2,3,2};
//        ListNode head = ListNode.createLinkedList(inputArr);
//        checkPalindrome x = new checkPalindrome();
//        System.out.println(x.checkPalindromeFn(head));
//        testBitwise x = new testBitwise();
//        x.testfn();
//        long startTime, endTime;
//        startTime = System.nanoTime();
//        testArray x = new testArray();
//        x.testfn();
//        endTime = System.nanoTime();
//        System.out.println("Time elapsed during execution: " + (endTime-startTime)/1000 + " microsec");

//        testBTree x = new testBTree();
//        x.testBTreeFn();
//        testCustomDS x = new testCustomDS();
//        x.testCustomDSFn();


//        testString x = new testString();
//        x.testStringFn();
//        longestCommonSubstring x = new longestCommonSubstring();
//        System.out.println(x.longestCommonSubstringFn("blahsforGeeks", "Geeksquiz"));
//          testBTree x = new testBTree();
//          x.testBTreeFn();
//        reverseList x = new reverseList();
//        int[] inputArr = {1,2,3};
//        ListNode head = ListNode.createLinkedList(inputArr);
//        ListNode newHead = x.reverseListFn(head);
//        ListNode.printLinkedList(newHead);

//        mergeTwoLists x = new mergeTwoLists();
//        int[] arr1 = {1,2,4};
//        int[] arr2 = {1,3,4};
//        ListNode l1 = ListNode.createLinkedList(arr1);
//        ListNode l2 = ListNode.createLinkedList(arr2);
//        ListNode newHead = x.mergeTwoListsFn(l1, l2);
//        ListNode.printLinkedList(newHead);

//        reorderList x = new reorderList();
//        int[] arr = {};
//        ListNode l = ListNode.createLinkedList(arr);
//        ListNode newHead = x.reorderListFn(l);
//        ListNode.printLinkedList(newHead);

//        lastStoneWeight x = new lastStoneWeight();
//        int[] arr1 = {1,1,1,1,1,1};
//        System.out.println(x.lastStoneWeightFn(arr1));

//          stringShift x = new stringShift();
//          int[][] shift = {
//                  {1,8},
//                  {1,4},
//                  {1,3},
//                  {1,6},
//                  {0,6},
//                  {1,4},
//                  {0,2},
//                  {0,1}
//          };
//
//        System.out.println(x.stringShiftFn("yisxjwry", shift));

//        compareVersion x = new compareVersion();
//        System.out.println(x.compareVersionFn("", "0.0.0"));
//        simplifyPath x = new simplifyPath();
////        System.out.println(x.simplifyPathFn("/a/.b/./....."));
        // testMap x = new testMap();
//        moveZeroes x = new moveZeroes();
//        int[] a = {0,1,0,3,12,0,0,0,0,0};
//        x.moveZeroesFn(a);
//
//        for(int i=0;i<a.length;i++)
//            System.out.printf("%d ", a[i]);

//        generateParenthesis x = new generateParenthesis();
//        List<String> res = x.generateParenthesisFn(3);
//        for(String s : res)
//            System.out.println(s);
    }

    //--------------Below 3 functions to test canJump class ------------------------
//    public static void main(String[] args) throws IOException {
//        // BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//
//        File file = new File("/Users/niteshsinha/Documents/Personal/side-projects/java-problem-solutions/src/largeInput2.txt");
//        BufferedReader in = new BufferedReader(new FileReader(file));
//        String line;
//        while ((line = in.readLine()) != null) {
//            int[] nums = stringToIntegerArray(line);
//
//            boolean ret = new canJump().canJumpFn(nums);
//
//            String out = booleanToString(ret);
//
//            System.out.print(out);
//        }
//    }
//
//    public static int[] stringToIntegerArray(String input) {
//        input = input.trim();
//        input = input.substring(1, input.length() - 1);
//        if (input.length() == 0) {
//            return new int[0];
//        }
//
//        String[] parts = input.split(",");
//        int[] output = new int[parts.length];
//        for(int index = 0; index < parts.length; index++) {
//            String part = parts[index].trim();
//            output[index] = Integer.parseInt(part);
//        }
//        return output;
//    }
//
//    public static String booleanToString(boolean input) {
//        return input ? "True" : "False";
//    }

    //--------------Above 3 functions to test canJump class ------------------------
}
