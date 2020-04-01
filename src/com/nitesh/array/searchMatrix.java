//        Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
//
//        Integers in each row are sorted from left to right.
//        The first integer of each row is greater than the last integer of the previous row.
//        Example 1:
//
//        Input:
//        matrix = [
//        [1,   3,  5,  7],
//        [10, 11, 16, 20],
//        [23, 30, 34, 50]
//        ]
//        target = 3
//        Output: true
//        Example 2:
//
//        Input:
//        matrix = [
//        [1,   3,  5,  7],
//        [10, 11, 16, 20],
//        [23, 30, 34, 50]
//        ]
//        target = 13
//        Output: false

//      NOTE: Solution involves searching the first column using binary search to get the row number to search on.
//      Then use another binary search to search along that row.
//   Time complexity: O(log m) + O(log n)

package com.nitesh.array;
import java.util.Arrays;

public class searchMatrix {
    public boolean searchMatrixFn(int[][] matrix, int target) {
        int index, rowToSearch;
        //empty matrix or one row but no columns.
        if(matrix.length == 0 || matrix[0].length == 0)
            return false;

        int[] firstCol = new int[matrix.length];

        for(int j=0;j<matrix.length;j++)
            firstCol[j] = matrix[j][0];
        index = Arrays.binarySearch(firstCol,target);

        // if number present along first column then don't search further
        if(index>=0)
            return true;

        // index of the search key, if it is contained in the array within the specified range;
        // otherwise, (-(insertion point) - 1). The insertion point is defined as the point at which
        // the key would be inserted into the array
        rowToSearch = -1*(index+1) -1;
        if(rowToSearch<0)
            return false;
        if(Arrays.binarySearch(matrix[rowToSearch],target) < 0)
            return false;
        return true;
    }
}
