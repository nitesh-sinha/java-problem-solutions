//        Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.
//
//        Example:
//
//        Input:
//
//        1 0 1 0 0
//        1 0 1 1 1
//        1 1 1 1 1
//        1 0 0 1 0
//
//        Output: 4

// Time complexity: O(mn) where m=no. of rows; n=no. of cols

package com.nitesh.array;

public class maximalSquare {
    // Logic: squareSide[i][j] is the length of the side of the square with bottom-right corner at cell (i,j)
    // If current element in original matrix is zero, no square possible with bottom-right corner at current element. Hence 0.
    // Else check if current element in original matrix is 1, get min of left, top and top-left of current element and add to  it.
    // We take min because we're trying to form a square and not a rectangle.
    public int maximalSquareFn(char[][] matrix) {
        int numRows = matrix.length, numCols=0, maxSquareLen = 0;
        if(numRows==0)
            return 0;
        numCols = matrix[0].length;
        int[][] squareSide = new int[1+numRows][1+numCols];

        for(int i=1; i<=numRows; i++) {
            for(int j=1; j<=numCols; j++) {
                if(matrix[i-1][j-1] == '1') {
                    // we take min of
                    squareSide[i][j] = 1 + Math.min(Math.min(squareSide[i-1][j], squareSide[i][j-1]), squareSide[i-1][j-1]);
                    maxSquareLen = Math.max(maxSquareLen, squareSide[i][j]);
                }
            }
        }
        return maxSquareLen*maxSquareLen;
    }
}
