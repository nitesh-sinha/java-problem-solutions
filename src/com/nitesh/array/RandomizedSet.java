//        Insert Delete GetRandom O(1)
//
//        Design a data structure that supports all following operations in average O(1) time.
//
//        insert(val): Inserts an item val to the set if not already present.
//        remove(val): Removes an item val from the set if present.
//        getRandom: Returns a random element from current set of elements. Each element must have the same probability of being returned.
//
//
//        Example:
//
//// Init an empty set.
//        RandomizedSet randomSet = new RandomizedSet();
//
//// Inserts 1 to the set. Returns true as 1 was inserted successfully.
//        randomSet.insert(1);
//
//// Returns false as 2 does not exist in the set.
//        randomSet.remove(2);
//
//// Inserts 2 to the set, returns true. Set now contains [1,2].
//        randomSet.insert(2);
//
//// getRandom should return either 1 or 2 randomly.
//        randomSet.getRandom();
//
//// Removes 1 from the set, returns true. Set now contains [2].
//        randomSet.remove(1);
//
//// 2 was already in the set, so return false.
//        randomSet.insert(2);
//
//// Since 2 is the only number in the set, getRandom always return 2.
//        randomSet.getRandom();
//
//


package com.nitesh.array;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class RandomizedSet {
    // List needed to ensure O(1) for getRandom()
    private List<Integer> nums;
    // Map needed to ensure O(1) for insert() and remove(). Stores num and its corresponding index in nums list
    private Map<Integer, Integer> numIndex;
    private java.util.Random rand;

    /** Initialize your data structure here. */
    public RandomizedSet() {
        nums = new ArrayList<Integer>();
        numIndex = new HashMap<>();
        rand = new java.util.Random();
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if(numIndex.containsKey(val))
            return false;
        numIndex.put(val, nums.size());
        nums.add(val);
        return true;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        int index, last;
        if(!numIndex.containsKey(val))
            return false;
        index = numIndex.get(val);
        if(index<nums.size()-1) {
            // element to tbe removed is not the last element in nums list
            // To maintain O(1) complexity for remove, swap last element and val in nums list
            // and remove last element
            last = nums.get(nums.size()-1);
            nums.set(index, last); // move last element to index position
            numIndex.put(last, index); // update the index of last element to the new one
        }
        nums.remove(nums.size()-1);
        numIndex.remove(val);
        return true;
    }

    /** Get a random element from the set. */
    public int getRandom() {
        int randIndex = rand.nextInt(nums.size());
        return nums.get(randIndex);
    }
}
