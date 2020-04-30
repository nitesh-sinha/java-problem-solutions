//        Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
//
//        For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
//
//        Example:
//
//        Given the sorted array: [-10,-3,0,5,9],
//
//        One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:
//
//           0
//          / \
//        -3   9
//        /   /
//      -10  5

// Time complexity: O(n) where n=size of the input array since each element is visited exactly once.

package com.nitesh.binaryTree;

public class sortedArrayToBST {
    public TreeNode sortedArrayToBSTFn(int[] nums) {
        // Very similar to a binary search solution
        return createBST(nums, 0, nums.length-1);
    }

    private TreeNode createBST(int[] nums, int start, int end) {
        if (start>end)
            return null;
        int mid = start + (end-start)/2;
        // ROOT
        TreeNode node = new TreeNode(nums[mid]);
        // LEFT
        node.left = createBST(nums, start, mid-1);
        // RIGHT
        node.right = createBST(nums, mid+1, end);

        return node;
    }
}
