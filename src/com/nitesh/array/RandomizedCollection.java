//  Design a data structure that supports all following operations in average O(1) time.
//
//        Note: Duplicate elements are allowed.
//
//        insert(val): Inserts an item val to the collection.
//        remove(val): Removes an item val from the collection if present.
//        getRandom: Returns a random element from current collection of elements. The probability of each element being returned is linearly related to the number of same value the collection contains.
//        Example:
//
//// Init an empty collection.
//        RandomizedCollection collection = new RandomizedCollection();
//
//// Inserts 1 to the collection. Returns true as the collection did not contain 1.
//        collection.insert(1);
//
//// Inserts another 1 to the collection. Returns false as the collection contained 1. Collection now contains [1,1].
//        collection.insert(1);
//
//// Inserts 2 to the collection, returns true. Collection now contains [1,1,2].
//        collection.insert(2);
//
//// getRandom should return 1 with the probability 2/3, and returns 2 with the probability 1/3.
//        collection.getRandom();
//
//// Removes 1 from the collection, returns true. Collection now contains [1,2].
//        collection.remove(1);
//
//// getRandom should return 1 and 2 both equally likely.
//        collection.getRandom();

package com.nitesh.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;

public class RandomizedCollection {
        private List<Integer> nums;
        private HashMap<Integer, LinkedHashSet<Integer>> numIndex;
        private java.util.Random rand;

        /** Initialize your data structure here. */
        public RandomizedCollection() {
            nums = new ArrayList<Integer>();
            numIndex = new HashMap<Integer, LinkedHashSet<Integer>>();
            rand = new java.util.Random();
        }

        /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
        public boolean insert(int val) {
            boolean found = numIndex.containsKey(val);
            if(!found)
                numIndex.put(val, new LinkedHashSet<>());
            numIndex.get(val).add(nums.size());
            nums.add(val);
            return !found;
        }

        /** Removes a value from the collection. Returns true if the collection contained the specified element. */
        public boolean remove(int val) {
            int index, last;
            boolean found = numIndex.containsKey(val);
            if(!found)
                return false;
            index = numIndex.get(val).iterator().next(); // get the first index where val is stored in nums list
            numIndex.get(val).remove(index); //remove the first index(from numIndex) where val is stored in nums list
            if(index<nums.size()-1) {
                last = nums.get(nums.size()-1);
                nums.set(index, last);
                numIndex.get(last).remove(nums.size()-1); // remove the old_index(last index) from the set
                numIndex.get(last).add(index); // add the new_index(index) into the set
            }
            nums.remove(nums.size()-1);
            if(numIndex.get(val).isEmpty())
                numIndex.remove(val);
            return true;
        }

        /** Get a random element from the collection. */
        public int getRandom() {
            return nums.get(rand.nextInt(nums.size()));
        }
}

// Based on this question, a followup question is: how do we get a random number from a list of numbers based on the
// probability of their occurrence. e.g.

// Generate random integers from within [1,3] with the following probabilities:

// P(1) = 0.2
// P(2) = 0.3
// P(3) = 0.5

// OPTION 1:
// Generate a random integer within [1,100] and do the following:

// If it is within [1,20] --> return 1.
// If it is within [21,50] --> return 2.
// If it is within [51,100] --> return 3.

// OPTION 2:
// Get relative probabilities of the numbers. Given

// P(1) = 0.2
// P(2) = 0.3
// P(3) = 0.5

// Their relative probabilities are 2,3 and 5. Add them to get 10.
// Arrange numbers as [1,1,2,2,2,3,3,3,3,3]
// Choose index=rand.nextInt(10). Return the number at "index" position.