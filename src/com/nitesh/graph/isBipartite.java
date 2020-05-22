//        Given an undirected graph, return true if and only if it is bipartite.
//
//        Recall that a graph is bipartite if we can split it's set of nodes into two independent subsets A and B such
//        that every edge in the graph has one node in A and another node in B.
//
//        The graph is given in the following form: graph[i] is a list of indexes j for which the edge between nodes i and j exists.
//        Each node is an integer between 0 and graph.length - 1.  There are no self edges or parallel edges: graph[i] does not contain i,
//        and it doesn't contain any element twice.
//
//        Example 1:
//        Input: [[1,3], [0,2], [1,3], [0,2]]
//        Output: true
//        Explanation:
//        The graph looks like this:
//        0----1
//        |    |
//        |    |
//        3----2
//        We can divide the vertices into two groups: {0, 2} and {1, 3}.
//        Example 2:
//        Input: [[1,2,3], [0,2], [0,1,3], [0,2]]
//        Output: false
//        Explanation:
//        The graph looks like this:
//        0----1
//        | \  |
//        |  \ |
//        3----2
//        We cannot find a way to divide the set of nodes into two independent subsets.
//
//
//        Note:
//
//        graph will have length in range [1, 100].
//        graph[i] will contain integers in range [0, graph.length - 1].
//        graph[i] will not contain i or duplicate values.
//        The graph is undirected: if any element j is in graph[i], then i will be in graph[j].

/**
 Following is a simple algorithm to find out whether a given graph is Birpartite or not using Breadth First Search (BFS).
 1. Assign RED color to the source vertex (putting into set U).
 2. Color all the neighbors with BLUE color (putting into set V).
 3. Color all neighbor’s neighbor with RED color (putting into set U).
 4. This way, assign color to all vertices such that it satisfies all the constraints of m way coloring problem where m = 2.
 5. While assigning colors, if we find a neighbor which is colored with same color as current vertex, then the graph cannot be colored with 2 colours (or graph is not Bipartite)

 **/

// Time complexity: O(V+E)

package com.nitesh.graph;

import java.util.LinkedList;
import java.util.Queue;

public class isBipartite {
    public boolean isBipartiteFn(int[][] graph) {
        int numVertex = graph.length;
        if(numVertex <= 1)
            return true;
        int[] color = new int[numVertex]; // checking for biparitite graph is a 1-way coloring problem in graphs.

        // fill up colors array with -1 to denote no colour
        for(int i=0; i<numVertex; i++)
            color[i] = -1;

        for(int i=0; i< numVertex; i++) {
            if(color[i] == -1 && !isSubGraphBipartite(graph, color, i))
                return false;
        }
        // all vertices visited and neighbors visited.
        // all subgraphs are bipartite
        // Hence overall graph is bipartite.
        return true;
    }

    // All nodes in bipartite graph may not be connected. Some nodes maybe isolated nodes.
    // Isolated nodes can belong to either of the two sets while segregating the vertices to check for bipartite nature.
    // Only condition for bipartite graph is that all the edges in graph must belong to two different sets.
    private boolean isSubGraphBipartite(int[][] graph, int[] color, int srcVertex) {
        // We'll try to assign one of 2 colors, namely 0 and 1
        // to each vertex such that each no two neighbors have
        // the same color.
        // Start with any node(here we choose node 0)
        Queue<Integer> bfsQueue = new LinkedList<>();
        bfsQueue.add(srcVertex);
        color[srcVertex] = 1;

        // Perform BFS traversal starting from srcVertex
        while(!bfsQueue.isEmpty()) {
            int vertex = bfsQueue.poll();
            int[] neighbors = graph[vertex];
            for(int neighbor : neighbors) {
                if(color[neighbor] == -1) {
                    // edge exists but neighbir is not yet colored. So color it with contrast color wrt current vertex
                    color[neighbor] = 1 - color[vertex];
                    bfsQueue.add(neighbor);
                } else if(color[neighbor] == color[vertex])
                    // same color of neighbor and current vertex. Hence not bipartite
                    return false;
            }
        }

        // all vertices starting from srcVertex have been visited and none of the neighbors
        // have same color
        return true;
    }
}
