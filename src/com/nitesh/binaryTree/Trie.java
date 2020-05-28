//        Implement a trie with insert, search, and startsWith methods.
//
//        Example:
//
//        Trie trie = new Trie();
//
//        trie.insert("apple");
//        trie.search("apple");   // returns true
//        trie.search("app");     // returns false
//        trie.startsWith("app"); // returns true
//        trie.insert("app");
//        trie.search("app");     // returns true
//        Note:
//
//        You may assume that all inputs are consist of lowercase letters a-z.
//        All inputs are guaranteed to be non-empty strings.
//
// Time complexity: All operations implemented here take O(n) time where n=length fo input word

package com.nitesh.binaryTree;

public class Trie {
    TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Inserts a word into the trie.
    public void insert(String word) {
            TrieNode ptr = root;
            int index;

            if(word.length() == 0)
                return;

            for(int i=0; i < word.length(); i++) {
                index = word.charAt(i) - 'a';
                if(ptr.arr[index] == null) {
                    // this character doesn't exist in trie. Create it!
                    TrieNode temp = new TrieNode();
                    ptr.arr[index] = temp;

                }
                // Move ptr ahead by one node
                ptr = ptr.arr[index];
            }
            ptr.isEnd = true; // One node beyond last character in word
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        TrieNode ptr = searchString(word);
        return (ptr != null && ptr.isEnd);
    }

    // Returns one node beyond last character in word
    private TrieNode searchString(String word) {
        TrieNode ptr = root;
        int index;
        if(word.length()==0)
            return null;
        for(int i=0; i < word.length();i++) {
            index = word.charAt(i) - 'a';
            if(ptr.arr[index]==null)
                return null;
            else {
                ptr = ptr.arr[index];
            }
        }
        return ptr;
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        TrieNode ptr = searchString(prefix);
        return (ptr != null);
    }
}
