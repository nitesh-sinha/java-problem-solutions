//        For an undirected graph with tree characteristics, we can choose any node as the root. The result graph is
//        then a rooted tree. Among all possible rooted trees, those with minimum height are called minimum height trees (MHTs).
//        Given such a graph, write a function to find all the MHTs and return a list of their root labels.
//
//        Format
//        The graph contains n nodes which are labeled from 0 to n - 1. You will be given the number n and a list of
//        undirected edges (each edge is a pair of labels).
//
//        You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the
//        same as [1, 0] and thus will not appear together in edges.
//
//        Example 1 :
//
//        Input: n = 4, edges = [[1, 0], [1, 2], [1, 3]]
//
//          0
//          |
//          1
//         / \
//        2   3
//
//        Output: [1]
//        Example 2 :
//
//        Input: n = 6, edges = [[0, 3], [1, 3], [2, 3], [4, 3], [5, 4]]
//
//          0  1  2
//           \ | /
//             3
//             |
//             4
//             |
//             5
//
//        Output: [3, 4]
//
//        Note:
//
//        According to the definition of tree on Wikipedia: “a tree is an undirected graph in which any two vertices are
//        connected by exactly one path. In other words, any connected graph without simple cycles is a tree.”
//
//        The height of a rooted tree is the number of edges on the longest downward path between the root and a leaf.

// Complexity: Time: O(V+E)
//             Space: O(V+E)

package com.nitesh.graph;

import java.util.*;

public class findMinHeightTrees {
    public List<Integer> findMinHeightTreesFn(int n, int[][] edges) {
        // Idea: Calculate all the longest paths in the graph. It can be just one or more than one path with equal size.
        // Get centre node(s) in all those longest paths.
        // If longest path has odd nodes, then only 1 centre node, else two centre nodes.
        // Add all these centre nodes to the resultant list.

        // Algorithm: Instead of calculating all longest paths directly, we'll start traversing
        // from leaf nodes. Then eliminate every edge connected to leaf nodes and find new leaf nodes
        // as a result of edge deletion. Essentially we're starting our graph traversal from leaf nodes
        // and moving towards centre node(s). Repeat this process until we reach the centre node(s).
        // That's our result.
        // Note: There are a max of 2 MHTs possible in any graph with no loops.

        if(n <= 1)
            return Collections.singletonList(0);

        List<HashSet<Integer>> adjList = new ArrayList<HashSet<Integer>>();
        List<Integer> leaves = new ArrayList<>();
        List<Integer> newLeaves;
        int neighbor;

        // create all hashsets in the list
        for(int i=0; i<n; i++)
            adjList.add(new HashSet<Integer>());


        // create adjacency list from given input edge list
        for(int[] edge : edges) {
            adjList.get(edge[0]).add(edge[1]);
            adjList.get(edge[1]).add(edge[0]);
        }

        // get leaves of the graph
        for(int i=0; i < adjList.size(); i++) {
            if(adjList.get(i).size() == 1)
                leaves.add(i);
        }

        // start iteration of graph from leaf nodes and update leaf nodes list as you go
        while(n > 2) {
            n -= leaves.size();
            newLeaves = new ArrayList<>();
            // repeat these steps since there can be a max of 2 MHTs in a graph
            for(int leaf : leaves) {
                neighbor = adjList.get(leaf).iterator().next();
                adjList.get(neighbor).remove(leaf);
                if(adjList.get(neighbor).size() == 1)
                    newLeaves.add(neighbor);
            }
            leaves = newLeaves;
        }
        return leaves;
    }
}
