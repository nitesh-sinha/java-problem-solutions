//        A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
//
//        The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
//
//        Now consider if some obstacles are added to the grids. How many unique paths would there be?
//
//        An obstacle and empty space is marked as 1 and 0 respectively in the grid.
//
//        Note: m and n will be at most 100.
//
//        Example 1:
//
//        Input:
//        [
//        [0,0,0],
//        [0,1,0],
//        [0,0,0]
//        ]
//        Output: 2
//        Explanation:
//        There is one obstacle in the middle of the 3x3 grid above.
//        There are two ways to reach the bottom-right corner:
//        1. Right -> Right -> Down -> Down
//        2. Down -> Down -> Right -> Right
//
//        Time complexity: O(mn) where m=no. of rows; n=no. of cols.

package com.nitesh.array;

public class uniquePathsWithObstacles {
    public int uniquePathsWithObstaclesFn(int[][] obstacleGrid) {
        int rows=obstacleGrid.length, cols=obstacleGrid[0].length;
        if(obstacleGrid[0][0]==1 || obstacleGrid[rows-1][cols-1]==1)
            return 0;

        int[][] waysToReach = new int[rows][cols];

        // Fill 1st row
        for(int i=0;i<cols;i++) {
            if(obstacleGrid[0][i]==1) {
                while(i<cols) {
                    // Further cells in that row can't be reached(since we can only move right or down)
                    waysToReach[0][i] = 0;
                    i++;
                }
            } else
                waysToReach[0][i] = 1;
        }

        // Fill 1st col
        for(int i=0;i<rows;i++) {
            if(obstacleGrid[i][0]==1) {
                while(i<rows) {
                    // Further cells in that column can't be reached(since we can only move right or down)
                    waysToReach[i][0]=0;
                    i++;
                }
            } else
                waysToReach[i][0]=1;
        }

        // Fill rest of rows and cols
        for(int i=1;i<rows;i++) {
            for(int j=1;j<cols;j++) {
                if(obstacleGrid[i][j]==1)
                    waysToReach[i][j] = 0;
                else
                    // We can reach this from one of the two neighboring cells(top or left)
                    waysToReach[i][j] = waysToReach[i-1][j] + waysToReach[i][j-1];
            }
        }
        return waysToReach[rows-1][cols-1];
    }
}
