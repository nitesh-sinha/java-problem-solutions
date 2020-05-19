//        There are a total of n courses you have to take, labeled from 0 to n-1.
//
//        Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
//
//        Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take to finish all courses.
//
//        There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all courses, return an empty array.
//
//        Example 1:
//
//        Input: 2, [[1,0]]
//        Output: [0,1]
//        Explanation: There are a total of 2 courses to take. To take course 1 you should have finished
//        course 0. So the correct course order is [0,1] .
//        Example 2:
//
//        Input: 4, [[1,0],[2,0],[3,1],[3,2]]
//        Output: [0,1,2,3] or [0,2,1,3]
//        Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both
//        courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0.
//        So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3] .
//        Note:
//
//        The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
//        You may assume that there are no duplicate edges in the input prerequisites.

// This is an extension of the problem described in courseSchedule. java

package com.nitesh.graph;

import java.util.LinkedList;
import java.util.Queue;

public class courseScheduleOrder {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[][] adjMatrix = new int[numCourses][numCourses]; // numCourses = num of vertices in the graph
        int[] indegree = new int[numCourses]; // stores the no. of incoming edges for every vertex in the graph
        int src, dst, visitedCount=0, index = 0;
        Queue<Integer> zeroIndegreeNodes = new LinkedList<>();
        int[] topoSort = new int[numCourses];

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
            topoSort[index++] = node;
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

        if(visitedCount == numCourses) {
            return topoSort;
        }
        // not possible to take all courses, so return empty array [].
        return new int[0];
    }
}
