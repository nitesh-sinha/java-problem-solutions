package com.nitesh.array;

import java.util.Comparator;
import java.util.PriorityQueue;

// Used logic described in https://www.youtube.com/watch?v=1CxyVdA_654&ab_channel=ComScienceSimplified
public class MedianFinder {
    // minHeap to store all numbers in upper half, maxHeap to store all numbers in lower half.
    private PriorityQueue<Integer> minHeap, maxHeap;
    public MedianFinder() {
        minHeap = new PriorityQueue<>();
        maxHeap = new PriorityQueue<>(1, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
    }

    public void addNum(int num) {
        // Blindly add 1st element to minheap
        if(minHeap.size()==0) {
            //System.out.println("Added 1st element to minheap");
            minHeap.add(num);
            return;
        }

        // Add a new number to minHeap if possible
        if(minHeap.size()>0 && num >= minHeap.peek()) {
            minHeap.add(num);
            //System.out.println("Added element to minheap");
        } else  {
            // If not possible to add in minHeap, then add to maxHeap
            maxHeap.add(num);
            //System.out.println("Added element to maxheap");
        }


        // if size of both heaps differ by 2 or more, then rebalance the heaps
        // move one element from heap of larger size to heap of smaller size
        if(Math.abs(minHeap.size() - maxHeap.size()) >=2) {
            PriorityQueue<Integer> largerHeap=maxHeap, smallerHeap=minHeap;
            if(minHeap.size() > maxHeap.size()) {
                largerHeap = minHeap;
                smallerHeap = maxHeap;
            }
            smallerHeap.add(largerHeap.poll()); // move element across heaps
            //System.out.println("Resized the heaps");
        }
    }

    public double findMedian() {
        if(minHeap.size()>0 && minHeap.size() == maxHeap.size()) {
           // System.out.println("both heap sizes are equal");
            return (minHeap.peek() + maxHeap.peek())/2.0;
        } else {
            //System.out.println("both heap sizes are unequal");
            return (findLargerHeap().peek());
        }

    }

    private PriorityQueue<Integer> findLargerHeap() {
        return (minHeap.size() > maxHeap.size()) ? minHeap : maxHeap;
    }
}
