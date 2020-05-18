// Given a graph in adjacency list representation, perform its topological sort and print the vertices in that sorted order.
// Sorting should be done using Depth First Search algorithm.
// For more details on the algorithm, check https://www.geeksforgeeks.org/topological-sorting/

package com.nitesh.graph;

import java.util.List;
import java.util.Stack;

// While courseSchedule.java mentions the algo for topological sorting using BFS,
// this class performs topological sorting of a graph using DFS algorithm.
// Note that there can be more than one topologically sorting order for a given graph.
public class topologicalSortDFS {
    public void topologicalSortDFSFn(List<List<Integer>> adjList, int numVertices) {
        boolean[] visited = new boolean[numVertices]; // all set to false i.e. not visited
        Stack<Integer> stack = new Stack<>();

        // Iterate over entire adjacency list and perform DFS at each unvisited vertex
        for(int i=0; i<numVertices; i++) {
            if(!visited[i])
                topoSortDFSUtil(adjList, i, visited, stack);
        }

        // Pop stack values, they'll be topologically sorted
        while(!stack.isEmpty())
            System.out.println(stack.pop());
    }

    private void topoSortDFSUtil(List<List<Integer>> adjList, int i, boolean[] visited, Stack<Integer> stack) {
        visited[i] = true;

        List<Integer> neighbors = adjList.get(i);
        for(Integer neighbor : neighbors) {
            if(!visited[neighbor])
                topoSortDFSUtil(adjList, neighbor, visited, stack);
        }

        // all neighbours of i have now been visited. So add it to stack
        stack.add(i);
    }
}
