//        Given n, how many structurally unique BST's (binary search trees) that store values 1 ... n?
//
//        Example:
//
//        Input: 3
//        Output: 5
//        Explanation:
//        Given n = 3, there are a total of 5 unique BST's:
//
//        1         3     3      2      1
//         \       /     /      / \      \
//          3     2     1      1   3      2
//         /     /       \                 \
//        2     1         2                 3

package com.nitesh.array;

public class numTrees {
    public int numTreesFn(int n) {
        // Dynamic programming solution
        /**
         * To build a tree that contains {1,2,3,4,5}. First we pick 1 as root; for the left sub tree,
         * there are no numbers; for the right sub tree, we need to count how many possible trees can be
         * constructed from {2,3,4,5}, apparently it's the same number as {1,2,3,4}. So the total number
         * of trees under "1" picked as root is numTreesWithNodes[0] * numTreesWithNodes[4] = 14. (assume numTreesWithNodes[0]=1).
         * Similarly, root 2 has numTreesWithNodes[1]*numTreesWithNodes[3] = 5 trees.
         * root 3 has numTreesWithNodes[2]*numTreesWithNodes[2] = 4,
         * root 4 has numTreesWithNodes[3]*numTreesWithNodes[1]= 5 and
         * root 5 has numTreesWithNodes[4]*numTreesWithNodes[0] = 14.
         * Finally sum them up and it's done.
         **/
        int[] numTreesWithNodes = new int[n+1]; // numTreesWithNodes[i] is the number of unique BST's possible with i nodes
        numTreesWithNodes[0]=1;
        for(int i=1;i<=n;i++) {
            for(int j=0;j<=i-1;j++) {
                numTreesWithNodes[i] += numTreesWithNodes[j] * numTreesWithNodes[i-j-1];
            }
        }
        return numTreesWithNodes[n];
    }
}
