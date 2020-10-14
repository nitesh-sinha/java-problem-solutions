//      Top K Frequent numbers in an array. Solution should have time complexity better than O(NlogN).
//
// Eg: Input: arr1 = [1,2,3,1,1,1,2,5,6,7,9,1,1,2]
//               k = 2
//     Output:
//                1
//                2
//      Explanation: 1 has the maximum frequency(of 6) in arr1, followed by 2 which has the second highest frequency(of 3).
//
// Time complexity of alternate solution: O(k * log(n)) where n=length of nums in maxHeap; k=input k(<N)

package com.nitesh.array;

import java.util.*;

public class topKFrequent {
    public List<Integer> topKFrequentFn(int[] nums, int k) {
        if(nums.length == 0)
            return null;
        Map<Integer, Integer> numFreqMap = new HashMap<>();
        PriorityQueue<numFreq> numFreqPQ = new PriorityQueue<>();
        List<Integer> res = new ArrayList<>();
        int freq;

        // Create the number to its frequency map
        for(int num : nums) {
            freq = numFreqMap.getOrDefault(num, 0);
            numFreqMap.put(num, ++freq);
        }

        // Create a priority queue of number and frequencies such that
        // the queue can arrange it in a max heap fashion
        for(Map.Entry<Integer, Integer> entry : numFreqMap.entrySet())
            numFreqPQ.add(new numFreq(entry.getKey(), entry.getValue()));

        // Pop k numbers from priority queue which will inherently be sorted in
        // decreasing order of their frequencies(because of the compareTo() method
        // of numFreq class)
        for(int i=0; i<k; i++) {
            if(numFreqPQ.size() > 0)
                res.add(numFreqPQ.poll().num);
        }

        return res;
    }

    class numFreq implements Comparable<numFreq> {
        int num;
        int frequency;

        numFreq(int n, int f) {
            num = n;
            frequency = f;
        }

        // Sort this class object based on decreasing order of frequency
        @Override
        public int compareTo(numFreq that) {
            return that.frequency - this.frequency;
        }
    }
}
