//        Given a n x n matrix where each of the rows and columns are sorted in ascending order, find the kth smallest element in the matrix.
//
//        Note that it is the kth smallest element in the sorted order, not the kth distinct element.(i.e. elements can repeat)
//
//        Example:
//
//        matrix = [
//        [ 1,  5,  9],
//        [ 2,  6, 10],
//        [10, 11, 12]
//        ],
//        k = 4,
//
//        return 6.
//
//        Note:
//        You may assume k is always valid, 1 ≤ k ≤ n^2.

package com.nitesh.array;

import java.util.PriorityQueue;

public class kthSmallest {
    // User defined data structure - allows us to compare elements and add next element to priority queue
    class Cell implements Comparable<Cell> {
        int val;
        int row;
        int col;

        private Cell() {};

        private Cell(int val, int row, int col) {
            // Stores row num, col num and value of a matrix cell
            this.val = val;
            this.row = row;
            this.col = col;
        }

        @Override
        public int compareTo(Cell that) {
            // implements functionality for minHeap data struct
            return this.val - that.val;
        }
    }

    public int kthSmallestFn(int[][] matrix, int k) {
        int numRows = matrix.length, count = 0;
        PriorityQueue<Cell> minCellHeap = new PriorityQueue<>();
        Cell c = new Cell();

        // Add first column elements in priority queue
        for(int i = 0; i<numRows; i++)
            minCellHeap.add(new Cell(matrix[i][0], i, 0));

        // For every iteration, remove smallest element "c" from queue.
        // and add element(in same row but next column of "c") to queue
        while(count < k) {
            c = minCellHeap.poll(); // remove lowest valued cell from heap
            if(c.col == numRows-1)
                // No more elements present in this row to add to queue
                continue;
            // add the value in the next col of the current element's row
            minCellHeap.add(new Cell(matrix[c.row][c.col+1], c.row, c.col+1));
            count++;
        }
        return c.val;
    }
}
