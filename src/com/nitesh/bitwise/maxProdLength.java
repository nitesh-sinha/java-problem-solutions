//        Given a string array words, find the maximum value of length(word[i]) * length(word[j])
//        where the two words do not share common letters.
//        You may assume that each word will contain only lower case letters. If no such two words exist, return 0.
//
//   Example 1: Input: words = {"abc", "def", "adfg", "dira"}
//              Output: 9
//              Explanation: "abc" and "def" are the only words which do not contain common letters.
//              Hence product of their lengths = 3*3 = 9

// Time complexity: O(n) + O(k^2) = O(k^2), where n = no. of all chars in all words; k = no. of words

package com.nitesh.bitwise;

public class maxProdLength {
    public int maxProdLengthFn(String[] words) {
        if (words == null || words.length == 0)
            return 0;

        int maxProdLen = Integer.MIN_VALUE, numWords = words.length;
        // letterBitSet[i] stores a number whose binary equivalent has set bits at position per the order of that
        // letter in English lang for word[i].
        // Eg: if word="abc", then binary
        // equivalent of corresponding number is 0000....111
        // NOte that this algo considers duplicate letters in the same word as a single occurrence of that letter.
        int[] bitSetWord = new int[numWords];

        // create bitset array for each word in the array
        for(int i=0; i<numWords; i++) {
            for(int j = 0;j < words[i].length(); j++)
                bitSetWord[i] |= 1 << (words[i].charAt(j) - 'a');
        }

        // Now calculate max product length
        // bitset array will help determine if two words contain same character in O(1) time.
        for(int i=0; i < numWords-1; i++) {
            for(int j=i+1; j< numWords; j++)
                if((bitSetWord[i] & bitSetWord[j]) == 0 && (words[i].length() * words[j].length()) > maxProdLen)
                    // No common letter in both words and product of lengths is greater than max
                    maxProdLen = words[i].length() * words[j].length();
        }
        return maxProdLen;
    }
}
