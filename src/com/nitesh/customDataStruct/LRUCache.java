//        Design an LRU Cache:
//        An LRU cache is a data structure which stores data("integers" for this problem statement) for quick access.
//        New entries are added to the cache until the max size of the cache is hit. Once the cache is full, any new entry to be added
//        to the cache evicts an already existing entry in the cache. The eviction strategy employed is called Least Recently Used(LRU).
//        This means that during eviction process, the cache controller evicts that element from the cache which has been least recently accessed.
//        This just means that the cache entry which is accessed very frequently stays in the cache; the unused entries are evicted to make way for new
//        elements coming in to the cache. This increases the cache hit rate and thereby the overall efficiency of the cache.

// Implement two of the interfaces of the cache:
// get(int key) --> Lookup cache using a key value
// put(int key, int value)  --> Adds an entry into the cache(if it doesn't exist already)


// The idea is to store the cache entries in the form of a doubly linked list.
// so that the LRU order is maintained inherently by the linked list.

package com.nitesh.customDataStruct;

import java.util.HashMap;
import java.util.Map;

// Doubly linked list cache node entry
class cacheNode {
    private int val;
    private cacheNode prev; // points to prev cache node
    private cacheNode next; // points to the next cache node

    public cacheNode(int v) {
        val = v;
        prev = null;
        next = null;
    }

    public int getVal() {
        return val;
    }
}

public class LRUCache {
    private Map<Integer, cacheNode> cacheMap; // key to cache entry mapping
    cacheNode front, rear; // Add a new node to front end; delete from rear end
    int maxSize; // size of the cache(in terms of max number of entries it can store)

    public LRUCache(int size) {
        cacheMap = new HashMap<Integer, cacheNode>();
        front = rear = null;
        maxSize = size;
    }

    // Lookup cache using a key value
    public int get(int key) throws Exception {
        if(cacheMap.containsKey(key)) {
            cacheNode node = cacheMap.get(key);
            moveToFront(node);
            return node.getVal();
        } else
            throw new Exception("This key does not exist in cache");
    }

    // Adds an entry into the cache
    public void put(int key, int value) {
        if(cacheMap.containsKey(key)) {
            // Key exists in cache
            cacheNode node = cacheMap.get(key);
            moveToFront(node);
            return;
        }
        // Key does not exist in cache. Add this new entry
        if(cacheMap.size() == maxSize) {
            // Cache is full. Delete LRU entry from cache to make way for this new entry
            deleteFromRear();
        }
        cacheNode node = new cacheNode(value);
        cacheMap.put(key, node);
        // add this node to the front of doubly linked list
        addToFront(node);
    }

    // Adds the input node to the front of the doubly linked list
    private void addToFront(cacheNode node) {}

    // Deletes the LRU entry(which is at the rear end of the cache)
    private void deleteFromRear() {}

    // Moves the input node to the front of the doubly linked list
    private void moveToFront(cacheNode node) {}
}
