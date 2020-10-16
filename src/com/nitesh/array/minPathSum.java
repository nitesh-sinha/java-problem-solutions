//        Given a mxn grid filled with non-negative numbers, find a path from top left to bottom right which
//        minimizes the sum of all numbers along its path.
//
//        Note: You can only move either down or right at any point in time.
//
//        Time complexity: O(mn); m=no.of rows, n=no. of cols

package com.nitesh.array;

public class minPathSum {
    public int minPathSum(int[][] grid) {
        // Intuition: Since all numbers are non-negative in the grid array, there is only one way(for shortest distance
        // sum) to navigate the first row - which is starting from the top-leftmost cell along the numbers in the first row.
        // Similarly there is only one way(for shortest distance sum) to navigate the first column - which is starting
        // from the top-leftmost cell along the numbers in the first column. Once these shortest paths have been identified,
        // we can move to identify shortest distance("sum") paths for other cells in the grid. Notice that for each cell,
        // shortest distance to it is the sum of that cell itself + min(cell to the left of it and to the top of it) as
        // we can only travel down and right as per this problem definition.
        int numRows = grid.length, numCols=grid[0].length;

        for(int i=0;i<numRows;i++) {
            for(int j=0;j<numCols;j++) {
                if (i==0 && j==0)
                    // 1st row, 1st col
                    grid[i][j] = grid[i][j];
                else if(i==0 && j!=0)
                    // 1st row
                    grid[i][j] = grid[i][j] + grid[i][j-1];
                else if(i!=0 && j==0)
                    // 1st col
                    grid[i][j] = grid[i][j] + grid[i-1][j];
                else
                    // other row and col(except 1st)
                    grid[i][j] = grid[i][j] + Math.min(grid[i][j-1], grid[i-1][j]);
            }
        }

        return grid[numRows-1][numCols-1];
    }
}
