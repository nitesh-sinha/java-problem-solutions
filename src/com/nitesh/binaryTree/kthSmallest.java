//        Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.
//
//        Note:
//        You may assume k is always valid, 1 ≤ k ≤ BST's total elements.
//
//        Example 1:
//
//        Input: root = [3,1,4,null,2], k = 1
//           3
//          / \
//         1   4
//          \
//           2
//        Output: 1
//
//
//        Example 2:
//
//        Input: root = [5,3,6,2,4,null,null,1], k = 3
//           5
//          / \
//         3   6
//        / \
//       2   4
//      /
//     1
//        Output: 3

package com.nitesh.binaryTree;

import java.util.ArrayList;
import java.util.List;

public class kthSmallest {
    public int kthSmallestFn(TreeNode root, int k) {
        List<Integer> nums = new ArrayList<>();

        getInOrder(nums, root, k);
        return nums.get(nums.size()-1);
    }

    private void getInOrder(List<Integer> nums, TreeNode root, int k) {
        if(root==null || nums.size()==k)
            return;

        getInOrder(nums, root.left, k);
        if(nums.size()==k)
            // we reached the kth smallest element
            return;
        nums.add(root.val);
        getInOrder(nums, root.right, k);

    }
}
