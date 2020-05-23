//      Given a graph and a source vertex in the graph, find shortest paths from source to all vertices in the given graph.
//
//      For visual explanation using a graph, see http://www.geeksforgeeks.org/greedy-algorithms-set-6-dijkstras-shortest-path-algorithm/

package com.nitesh.graph;

import java.util.HashSet;
import java.util.Set;

// Time complexity: O(V^2) but using a priority queue(to replace outer for loop and minDistance function) it can be brought down to O(E*logV)

public class dijkstra {
    // calculates the shortest path from srcVertex to all other vertices in the graph
    // Returns the distance array
    // Note: Input graph is represented as adjacency matrix
    public int[] dijkstraFn(int[][] graph, int srcVertex) {
        int numVertex = graph.length;
        if(numVertex <= 0)
            return new int[0];
        int[] distance = new int[numVertex]; // distance[i] will hold the shortest distance from srcVertex to i'th vertex
        Set<Integer> shortestPathSet = new HashSet<>(); // stores the vertices whose shortest path have been finalized

        // Start with infinite distance for all nodes except source vertex
        for(int i = 0; i<numVertex; i++)
            distance[i] = Integer.MAX_VALUE;
        distance[srcVertex] = 0;

        while(shortestPathSet.size() < numVertex) {
            // Pick the minimum distance vertex from the set of vertices
            // not yet processed. It is always equal to srcVertex in first
            // iteration.
            int curVertex = getMinDistVertex(distance, shortestPathSet);
            shortestPathSet.add(curVertex);

            // get all neighbors of current vertex and update their distances
            for(int i=0; i<numVertex; i++) {
                // Update dist[i] only if is not in shortest path set AND there is an
                // edge from current vertex to i, and total weight of path from src to
                // i through current vertex is smaller than current value of dist[i]
                if(!shortestPathSet.contains(i) && graph[curVertex][i] != 0 && distance[curVertex] != Integer.MAX_VALUE &&
                distance[curVertex] + graph[curVertex][i] < distance[i])
                    distance[i] = distance[curVertex] + graph[curVertex][i];
            }
        }

        return distance;
    }

    // Returns the vertex with the min distance which is not in the shortestPathSet
    private int getMinDistVertex(int[] distance, Set<Integer> shortestPathSet) {
        int minDist = Integer.MAX_VALUE, minIndex = -1;
        for(int i=0; i<distance.length; i++) {
            if(!shortestPathSet.contains(i) && distance[i] < minDist) {
                minDist = distance[i];
                minIndex = i;
            }
        }
        return minIndex;
    }
}
