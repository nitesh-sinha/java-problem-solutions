package com.nitesh.binaryTree;

public class TrieNode {
    public TrieNode[] arr;
    public boolean isEnd; // to check for overlapping strings(eg: door vs do)

    public TrieNode() {
        arr = new TrieNode[26]; // since we use this node to denote lowercase English alphabets.
        isEnd = false;
    }
}
