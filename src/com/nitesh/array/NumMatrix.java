//        Given a 2D matrix, find the sum of the elements inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).
//
//        Example:
//
//        Given matrix = [
//        [3, 0, 1, 4, 2],
//        [5, 6, 3, 2, 1],
//        [1, 2, 0, 1, 5],
//        [4, 1, 0, 1, 7],
//        [1, 0, 3, 0, 5]
//        ]
//
//        sumRegion(2, 1, 4, 3) -> 8
//        sumRegion(1, 1, 2, 2) -> 11
//        sumRegion(1, 2, 2, 4) -> 12
//
//        Note:
//
//        You may assume that the matrix does not change.
//        There are many calls to sumRegion function.
//        You may assume that row1 ≤ row2 and col1 ≤ col2.


package com.nitesh.array;

public class NumMatrix {
    private int[][] sumTillCell;
    public NumMatrix(int[][] matrix) {
        if(matrix==null || matrix.length==0 || matrix[0].length==0)
            return;
        int rows=matrix.length, cols=matrix[0].length;
        // sumTillCell[i][j] is the sum of all elements contained in rectangle with top-left corner at (0,0) at bottom-right corner at (i,j)
        sumTillCell = new int[1+rows][1+cols];

        // Populate sumTillCell matrix
        for(int i=1;i<=rows;i++) {
            for(int j=1;j<=cols;j++) {
                // cell (i,j) for sumTillCell matrix is equivalent to cell (i-1,j-1) for input matrix
                sumTillCell[i][j] = sumTillCell[i][j-1] + sumTillCell[i-1][j] - sumTillCell[i-1][j-1] + matrix[i-1][j-1];
            }
        }
    }

    // Time complexity: O(1) for sumRegion()
    public int sumRegion(int row1, int col1, int row2, int col2) {
        // Assuming row1<=rows and col1<=col2
        // adding the intersection of two areas which were subtracted twice
        return sumTillCell[1+row2][1+col2] - sumTillCell[1+row2][col1] - sumTillCell[row1][1+col2] + sumTillCell[row1][col1];
    }
}

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * int param_1 = obj.sumRegion(row1,col1,row2,col2);
 */