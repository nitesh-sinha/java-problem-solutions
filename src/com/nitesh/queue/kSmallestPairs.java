//       Problem Statement: Find K Pairs with Smallest Sums:
//
//        You are given two integer arrays nums1 and nums2 sorted in ascending order and an integer k.
//
//        Define a pair (u,v) which consists of one element from the first array and one element from the second array.
//
//        Find the k pairs (u1,v1),(u2,v2) ...(uk,vk) with the smallest sums.
//
//        Example 1:
//        Given nums1 = [1,7,11], nums2 = [2,4,6],  k = 3
//
//        Return: [1,2],[1,4],[1,6]
//
//        The first 3 pairs are returned from the sequence:
//        [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
//
//
//        Example 2:
//        Given nums1 = [1,1,2], nums2 = [1,2,3],  k = 2
//
//        Return: [1,1],[1,1]
//
//        The first 2 pairs are returned from the sequence:
//        [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
//
//
//        Example 3:
//        Given nums1 = [1,2], nums2 = [3],  k = 3
//
//        Return: [1,3],[2,3]
//
//        All possible pairs are returned from the sequence:
//        [1,3],[2,3]

package com.nitesh.queue;


import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class kSmallestPairs {
    class Pair implements Comparable<Pair> {
        int n1, n2;
        int index; // index of n2 in nums2 array
        long sum;

        public Pair(int n1, int n2, int index) {
            this.n1 = n1;
            this.n2 = n2;
            this.index=index;
            this.sum = n1+n2;
        }

        @Override
        public int compareTo(Pair that) {
            return (int)(this.sum - that.sum);
        }
    }


    public List<int[]> kSmallestPairsFn(int[] nums1, int[] nums2, int k) {
        int len1=nums1.length, len2=nums2.length;
        List<int[]> res = new ArrayList<>();
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        int nextIndex;

        if(nums1==null || len1==0 || nums2==null || len2==0)
            return res;

        // Add k(or len1 whichever is smaller) pairs by always taking first element from nums2[] and
        // all elements from nums1[]
        for(int i=0; i<k && i<len1; i++) {
            pq.add(new Pair(nums1[i], nums2[0], 0));
        }

        for(int i=1; i<=k && !pq.isEmpty(); i++) {
            Pair p = pq.poll();
            res.add(new int[]{p.n1, p.n2});
            nextIndex = p.index + 1;
            if(nextIndex < len2) {
                // within bounds of nums2[]
                pq.add(new Pair(p.n1, nums2[nextIndex], nextIndex));
            }
        }
        return res;
    }
}
