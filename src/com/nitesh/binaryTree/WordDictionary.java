//        Design a data structure that supports the following two operations:
//
//        void addWord(word)
//        bool search(word)

//        search(word) can search a literal word or a regular expression string containing only letters a-z or dot(.)
//        A dot(.) means it can represent any one letter.
//
//        Example:
//
//        addWord("bad")
//        addWord("dad")
//        addWord("mad")
//        search("pad") -> false
//        search("bad") -> true
//        search(".ad") -> true
//        search("b..") -> true
//
//        Note:
//        You may assume that all words are consist of lowercase letters a-z.

package com.nitesh.binaryTree;

public class WordDictionary {
    TrieNode root;

    /** Initialize your data structure here. */
    public WordDictionary() {
        root = new TrieNode();
    }

    /** Adds a word into the data structure. */
    public void addWord(String word) {
        int index;
        TrieNode ptr = root;

        for(int i=0; i < word.length(); i++) {
            index = word.charAt(i) - 'a';
            if(ptr.arr[index] == null) {
                // create a new node
                TrieNode temp = new TrieNode();
                ptr.arr[index] = temp;
            }
            ptr = ptr.arr[index];
        }
        ptr.isEnd = true;
    }

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        if(word.length()==0)
            return false;
        return search(word, 0, root);
    }

    private boolean search(String word, int index, TrieNode ptr) {
        // Exit case from recursion
        if(index == word.length())
            return ptr.isEnd;

        if(word.charAt(index) != '.') {
            if(ptr.arr[word.charAt(index) - 'a'] == null) {
                return false;
            }
            // Found current character in word. Recursive search for remaining chars in word
            // by advancing ptr to next node
            return search(word, index+1, ptr.arr[word.charAt(index) - 'a']);
        } else {
            // Iterate over the entire array of this TrieNode and see if any character exist
            for(int i=0; i<26; i++) {
                if(ptr.arr[i] != null) {
                    // A char corresponding to a regex dot(.) is found
                    // Search for remaining chars in the input word(Move ptr forward while calling recursive search method)
                    if(search(word, index+1, ptr.arr[i])) {
                        return true;
                    }
                }
            }
            // no character found in this node
            return false;
        }
    }
}
