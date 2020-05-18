// Problem Statement: There are a total of n courses(labeled 0 to n - 1) that you have to complete to obtain a degree.
//
// Some courses may have prerequisites, for example to take course 0 you have to first take course 1,
// which is expressed as a pair: [0,1]
//
// Given the total number of courses and a list of prerequisite pairs as input, find out if it is possible for you
// to finish all courses and get the degree.
//
//        For example:
//        2, [[1,0]]
//        There are a total of 2 courses to take. To take course 1 you should have finished course 0. So it is possible.
//
//        2, [[1,0],[0,1]]
//        There are a total of 2 courses to take. To take course 1 you should have finished course 0,
//        and to take course 0 you should also have finished course 1. So it is impossible to finish all courses.
//
// Note:
// The input prerequisites is a Graph represented by a list of edges, not adjacency matrices.
// You may assume that there are no duplicate edges in the input prerequisites.
//        1 <= numCourses <= 10^5

package com.nitesh.graph;

import java.util.LinkedList;
import java.util.Queue;

public class courseSchedule {
    // Algo implements Kahn's algorithm(for Topological sorting using BFS)
    public boolean courseScheduleFn(int numCourses, int[][] prerequisites) {
        int[][] adjMatrix = new int[numCourses][numCourses]; // numCourses = num of vertices in the graph
        int[] indegree = new int[numCourses]; // stores the no. of incoming edges for every vertex in the graph
        int src, dst, visitedCount=0;
        Queue<Integer> zeroIndegreeNodes = new LinkedList<>();

        // Create adjacency matrix and indegree array from given edge list input
        for(int i=0;i<prerequisites.length;i++) {
            dst = prerequisites[i][0];
            src = prerequisites[i][1];
            if (adjMatrix[src][dst]==0) // Avoid duplicates in input
                indegree[dst]++;
            adjMatrix[src][dst]=1;
        }

        // Add all vertices with indegree of zero to the queue
        for(int i=0;i<numCourses;i++) {
            if(indegree[i]==0)
                zeroIndegreeNodes.add(i);
        }

        // Iterate over queue until it is empty
        // Keep track of visitedCount
        // Remove edges of the current node and reduce its neighbours indegree by 1
        // Add those neighbors to queue whose indegree is now 0
        while(!zeroIndegreeNodes.isEmpty()) {
            Integer node = zeroIndegreeNodes.remove();
            visitedCount++;

            for(int i=0;i<numCourses;i++) {
                if(adjMatrix[node][i] != 0) {
                    // reduce indegree for this node's neighbours by 1
                    if(--indegree[i] == 0) {
                        // add i'th node to queue since its indegree is now zero
                        zeroIndegreeNodes.add(i);
                    }
                }
            }
        }

        return (visitedCount==numCourses); // No loop in graph as per Kahn's algo
    }
}
