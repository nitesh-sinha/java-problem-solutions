//        Given an undirected graph and a number m, determine if the graph can be coloured with at most m colours such that no two
//        adjacent vertices of the graph are colored with the same color. Here coloring of a graph means the assignment of
//        colors to all vertices.
//
//        First Example input:
//        graph = {0, 1, 1, 1},
//        {1, 0, 1, 0},
//        {1, 1, 0, 1},
//        {1, 0, 1, 0}
//
//        Output:
//        Solution Exists:
//
//        Following are the assigned colors
//        1  2  3  2
//
//        Explanation: By coloring the vertices
//        with following colors, adjacent
//        vertices does not have same colors
//
//
//
//        Second example Input:
//        graph = {1, 1, 1, 1},
//        {1, 1, 1, 1},
//        {1, 1, 1, 1},
//        {1, 1, 1, 1}
//        Output: Solution does not exist.
//        Explanation: No solution exits.

// Worst case time complexity: O(m^V) where V=no. of vertices in graph.
// Space complexity: O(V)

package com.nitesh.graph;

public class mColoringProblem {
    // input graph is in adjacency matrix form
    // m = number of max colors allowed
    public boolean mColoringProblemFn(int[][] graph, int m) {
        int numVertex = graph.length;

        if(numVertex <= 1)
            return true;

        // start all colors as unassigned(i.e. 0)
        int[] color = new int[numVertex];

        return (!canColorGraph(graph, m, color, 0));
    }

    // recursive function to check if graph can be colored using at most m colors
    private boolean canColorGraph(int[][] graph, int m, int[] color, int curVertex) {
        if(curVertex == graph.length)
            // all nodes have been colored with different colors.
            return true;

        for(int i=1; i <= m ; i++) {
            if(isSafe(graph, color, curVertex, i)) {
                color[curVertex] = i;
                // recurse for other vertices of graph
                if(canColorGraph(graph, m, color, curVertex+1))
                    return true;
                // backtrack(since assigning color i to curVertex didn't work)
                color[curVertex] = 0;
            }
        }
        return false;
    }

    // return true only if all neighbors of current vertex are of different color
    private boolean isSafe(int[][] graph, int[] color, int curVertex, int curColor) {
        for(int i = 0; i< graph.length; i++) {
            if(graph[curVertex][i] == 1 && curColor == color[i])
                return false;
        }
        return true;
    }
}
