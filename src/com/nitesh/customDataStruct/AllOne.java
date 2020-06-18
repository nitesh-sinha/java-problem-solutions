//        All O(1) Data Structure:
//
//        Implement a data structure supporting the following operations:
//
//        Inc(Key) - Inserts a new key with value 1. Or increments an existing key by 1. Key is guaranteed to be a non-empty string.
//        Dec(Key) - If Key value is 1, remove it from the data structure. Otherwise decrements an existing key by 1.
//                   If the key does not exist, this function does nothing. Key is guaranteed to be a non-empty string.
//        GetMaxKey() - Returns one of the keys with maximal value. If no element exists, return an empty string "".
//        GetMinKey() - Returns one of the keys with minimal value. If no element exists, return an empty string "".
//
//        Perform all these in O(1) time complexity.

package com.nitesh.customDataStruct;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// A single node(in a doubly linked list) definition
class Node {
    private int count;
    private Set<String> keySet;
    Node prev;
    Node next;

    public Node(int c) {
        count = c;
        keySet = new HashSet<>();
        prev = next = null;
    }

    public Node(int c, String s) {
        count = c;
        keySet = new HashSet<>();
        keySet.add(s);
        prev = next = null;
    }

    public int getCount() {
        return count;
    }

    public Set<String> getKeys() {
        return keySet;
    }
}

// Doubly linked list of Nodes
class NodeList {
    Node first; // has node with smallest count(can have multiple keys in that node) next to it
    Node last; // has node with largest count(can have multiple keys in that node) before it

    public NodeList() {
        // create two dummy nodes(first and last)
        first = new Node(-1); // The node after the first(dummy) node will have its count>-1
        last = new Node(Integer.MAX_VALUE); // The node before the last(dummy) node will have its count<MAX_VALUE
        first.next = last;
        last.prev = first;
    }

    // Adds a new node before existing node
    public void addNodeBefore(Node newNode, Node existingNode) {
        Node prevNode = existingNode.prev;
        prevNode.next = newNode;
        newNode.prev = prevNode;
        newNode.next = existingNode;
        existingNode.prev = newNode;
    }

    public void deleteNode(Node existingNode) {
        Node prevNode = existingNode.prev;
        prevNode.next = existingNode.next;
        existingNode.next.prev = prevNode;
        existingNode.prev = existingNode.next = null;
    }
}


// The final data structure which performs increment, decrement, getMaxKey and getMinKey in O(1) time
public class AllOne {
    private NodeList nodes; // To maintain ordering so that getMaxKey() and getMinKey() is O(1)
    private Map<String, Node> strNodeMap;  // Allows indexing into any node in the doubly linked list(called "nodes") in O(1) time

    public AllOne() {
        nodes = new NodeList();
        strNodeMap = new HashMap<String, Node>();
    }

    public void inc(String key) {
        //System.out.println("Incrementing " + key);
        if(strNodeMap.containsKey(key)) {
            Node curNode = strNodeMap.get(key);
            Node nextToCurNode = curNode.next;
            int curCount = curNode.getCount();
            Set<String> curNodeKeys = curNode.getKeys();

            // Remove the input key from current node and delete it if there are no more keys left in current node
            curNodeKeys.remove(key);
            if(curNodeKeys.size() == 0)
                nodes.deleteNode(curNode);

            if(curCount + 1 == nextToCurNode.getCount()) {
                // next node's count is 1 more than current node's count. Insert this key in next node
                nextToCurNode.getKeys().add(key);
                // update the map entry with this new node's reference
                strNodeMap.put(key, nextToCurNode);
            } else {
                // next node's count is not 1 more than current node's count. Its even higher.
                // Insert a new node after current node
                Node newNode = new Node(curCount + 1, key);
                nodes.addNodeBefore(newNode, nextToCurNode);
                strNodeMap.put(key, newNode);
            }
        } else {
            // add a new key to map with count as 1
            Node nextToFirstNode = nodes.first.next;
            if(nextToFirstNode.getCount() == 1) {
                // add this key to nextToFirstNode since its count is 1
                nextToFirstNode.getKeys().add(key);
                strNodeMap.put(key, nextToFirstNode);
            } else {
                // add a new node between firstNode and nextToFirstNode with count as 1
                Node newNode = new Node(1, key);
                nodes.addNodeBefore(newNode, nextToFirstNode);
                strNodeMap.put(key, newNode);
            }
        }
        //System.out.println("Incrementing " + key + " done");
    }

    public void dec(String key) {
        if(strNodeMap.containsKey(key)) {
            Node curNode = strNodeMap.get(key);
            Node prevToCurNode = curNode.prev;
            int curCount = curNode.getCount();
            Set<String> curNodeKeys = curNode.getKeys(); // TODO: CHeck why IDE is giving this error

            // Remove the input key from current node and delete it if there are no more keys left in current node
            curNodeKeys.remove(key);
            if(curNodeKeys.size() == 0)
                nodes.deleteNode(curNode);

            if(curCount - 1 > 0) {
                if(prevToCurNode.getCount() == curCount - 1) {
                    // previous node's count is 1 less than curNode's count. Insert the key in previous node
                    prevToCurNode.getKeys().add(key);
                    // update the map entry with this new node reference
                    strNodeMap.put(key, prevToCurNode);
                } else {
                    // previous node's count is not 1 less than curNode's count. Its even lesser.
                    // Insert a new node between prevToCurNode and curNode
                    Node newNode = new Node(curCount-1, key);
                    nodes.addNodeBefore(newNode, prevToCurNode.next); // don't use the second arg as "curNode" bcoz it might have got deleted from the list if its keySet became empty after removing current key.
                    strNodeMap.put(key, newNode);
                }
            } else {
                // count of this key became zero after decrementing it. Delete this key
                strNodeMap.remove(key);
            }
        }
    }

    public String getMaxKey() {
        if(nodes.first.next != nodes.last) {
            // atleast one valid node exists in list
            Node lastNode = nodes.last.prev; // last valid node
            return String.valueOf(lastNode.getKeys().iterator().next()); // returns the first element in keySet of last node
        }
        return "";
    }

    public String getMinKey() {
        if(nodes.first.next != nodes.last) {
            // atleast one valid node exists in list
            Node firstNode = nodes.first.next; // first valid node
            return String.valueOf(firstNode.getKeys().iterator().next()); // returns the first element in keySet of first node
        }
        return "";
    }
}
