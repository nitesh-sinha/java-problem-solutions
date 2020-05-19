//        Given a list of airline tickets represented by pairs of departure and arrival airports [from, to],
//        reconstruct the itinerary in order. All of the tickets belong to a man who departs from JFK. Thus, the itinerary must begin with JFK.
//
//        Note:
//
//        If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order
//        when read as a single string. For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
//        All airports are represented by three capital letters (IATA code).
//        You may assume all tickets form at least one valid itinerary.
//        Example 1:
//
//        Input: [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
//        Output: ["JFK", "MUC", "LHR", "SFO", "SJC"]
//        Example 2:
//
//        Input: [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
//        Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]
//        Explanation: Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"].
//        But it is larger in lexical order.

// Time complexity: O(V+E) where V=no. of vertices; E=no. of edges

package com.nitesh.graph;

import java.util.*;

public class findItinerary {
    // Eulerian path for a directed graph??

    public List<String> findItineraryFn(List<List<String>> tickets) {
        // PriorityQueue is implemented as a minHeap which means Type's(String here) default comparator
        // will ensure that PriorityQueue.poll() will return destinations in sorted(lexicographic) order
        Map<String, PriorityQueue<String>> adjList = new HashMap<String, PriorityQueue<String>>();
        Stack<String> stack = new Stack<>(); // to store airports as they are visited in order
        List<String> result = new ArrayList<String>();
        PriorityQueue<String> destQueue;
        String src, dest, stackTop;

        // convert input edge list to adjacency list
        for(List<String> ticket : tickets) {
            src = ticket.get(0);
            dest = ticket.get(1);
            destQueue = adjList.getOrDefault(src, new PriorityQueue<>());
            destQueue.add(dest);
            adjList.put(src, destQueue);
        }

        stack.push("JFK");
        while(!stack.isEmpty()) {
            // Continue until all edges have been visited once
            stackTop = stack.peek();
            while(adjList.containsKey(stackTop)) {
                // Follow a path and push all nodes to stack until we hit a deadend(similar to DFS)
                PriorityQueue<String> pq = adjList.get(stackTop);
                stack.push(pq.poll());
                if(pq.isEmpty())
                    adjList.remove(stackTop);
                stackTop = stack.peek();
            }
            // Deadend hit. Add last node(deadend node) to result
            // This means we backtrack by one node(i.e. go to node before deadend node) and follow a different path
            // until the next deadend is hit. Rinse and repeat
            result.add(0, stack.pop());
        }

        return result;
    }
}
