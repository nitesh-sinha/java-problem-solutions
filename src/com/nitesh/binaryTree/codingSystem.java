//        Find all possible interpretations of an array of digits
//
//        Consider a coding system for alphabets to integers where 'a' is represented as 1, 'b' as 2, .. 'z' as 26. Given an array of digits
//        (1 to 9) as input, write a function that prints all valid interpretations of input array.
//
//        Examples
//
//        Input: {1, 1}
//        Output: ("aa", "k")
//        [2 interpretations: aa(1, 1), k(11)]
//
//        Input: {1, 2, 1}
//        Output: ("aba", "au", "la")
//        [3 interpretations: aba(1,2,1), au(1,21), la(12,1)]
//
//        Input: {9, 1, 8}
//        Output: {"iah", "ir"}
//        [2 interpretations: iah(9,1,8), ir(9,18)]


package com.nitesh.binaryTree;

import java.util.Arrays;

class codeNode {
    private int[] remDigits;
    private String word;
    codeNode left;
    codeNode right;

    codeNode(int[] r, String w) {
        remDigits = r;
        word = w;
        left = null;
        right = null;
    }

    String getWord() {
        return word;
    }
}

public class codingSystem {
    codeNode root;
    private static final String[] alphabet = {"", "a", "b", "c", "d", "e",
            "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r",
            "s", "t", "u", "v", "w", "x", "v", "z"};

    public void codingSystemFn(int[] arr) {
        if(arr.length == 0)
            return;
        root = createBinaryTree(arr, "", 0);
        printTreeLeaves(root);
    }

    // Converts the input arr into a tree of codeNodes
    // Input: the details needed to create a new codeNode
    // Output: The new codeNode created
    private codeNode createBinaryTree(int[] arr, String curWord, int curNum) {
        if(curNum > 26)
            return null;

        String newWord = curWord + alphabet[curNum];
        codeNode node = new codeNode(arr, newWord);

        if(arr.length > 0) {
            // Recurse to create left child
            int[] remDigits = Arrays.copyOfRange(arr, 1, arr.length);
            node.left = createBinaryTree(remDigits, newWord, arr[0]);

            if(arr.length > 1) {
                // Recurse to create right child
                remDigits = Arrays.copyOfRange(arr, 2, arr.length);
                node.right = createBinaryTree(remDigits, newWord, arr[0] * 10 + arr[1]);
            }
        }

        return node;
    }

    private void printTreeLeaves(codeNode root) {
        if(root == null)
            return;
        if(root.left==null && root.right==null)
            System.out.println(root.getWord());
        printTreeLeaves(root.left);
        printTreeLeaves(root.right);
    }
}
