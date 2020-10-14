//        Word Search:  Given a 2D board and a word, find if the word exists in the grid.
//
//        The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those
//        horizontally or vertically neighboring.
//        The same letter cell may not be used more than once.
//
//        For example, given board =
//
//        [
//        ['A','B','C','E'],
//        ['S','F','C','S'],
//        ['A','D','E','E']
//        ]
//
//        word = "ABCCED", -> returns true,
//        word = "SEE", -> returns true,
//        word = "ABCB", -> returns false.

// Time complexity: O(m*n*4*k) where m=no. of rows in board, n=no. of cols in board, k=length of word. 4 b'coz we
// search for that word in all 4 neighboring directions??

package com.nitesh.array;

public class wordSearch {
    private boolean[][] visited;
    public boolean exist(char[][] board, String word) {
        int rows = board.length, cols = board[0].length, index=0;
        visited = new boolean[rows][cols];

        for(int i=0;i<rows;i++) {
            for(int j=0;j<cols;j++)
                if(exist(board, word, i, j, index))
                    return true;
        }
        return false;
    }

    private boolean exist(char[][] board, String word, int row, int col, int index) {
        if(index==word.length())
            return true;

        if(row<0 || row>=board.length || col<0 || col>=board[0].length)
            return false;

        if(visited[row][col])
            return false;

        if(board[row][col] != word.charAt(index))
            return false;

        visited[row][col] = true;
        boolean isPresent;
        isPresent = exist(board, word, row+1, col, index+1) ||
                exist(board, word, row-1, col, index+1) ||
                exist(board, word, row, col+1, index+1) ||
                exist(board, word, row, col-1, index+1);
        visited[row][col] = false;
        return isPresent;
    }
}
