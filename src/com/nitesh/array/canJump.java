package com.nitesh.array;

enum IndexType {
    GOOD,
    BAD,
    UNKNOWN
}

//-------------------------------------------------------------------------------------

// Dynamic programming solution(memoization top-down approach) shown below
// which takes too much time for larger arrays(size>=50)

// Time complexity : O(n^2): For every element in the array, say i, we are looking at the next nums[i] elements
//                   to its right aiming to find a GOOD index. nums[i] can be at most nn, where nn is the length of array nums.
//
// Space complexity : O(2n) = O(n). First n originates from recursion. Second n comes from the usage of the memoization table.

//-------------------------------------------------------------------------------------

//public class canJump {
//    private IndexType[] indexState; // Memoization array
//    public boolean canJumpFn(int[] nums) {
//        int len=nums.length;
//        if(len==0)
//            return false;
//        if(len==1)
//            return true;
//
//        // Initialize the memoization array
//        indexState = new IndexType[len];
//        for(int i=0;i<len;i++)
//            indexState[i] = IndexType.UNKNOWN;
//
//        indexState[len-1] = IndexType.GOOD;
//        return canJumpFromPos(nums, 0);
//    }
//
//    private boolean canJumpFromPos(int[] nums, int position) {
//        int maxJump;
//        // exit condition from recursive loop
//        if(indexState[position] != IndexType.UNKNOWN)
//            return (indexState[position] == IndexType.GOOD);
//
//        maxJump = Math.min(nums.length-1, position + nums[position]);
//        for(int nextPos = position+1; nextPos<=maxJump; nextPos++) {
//            if(canJumpFromPos(nums, nextPos))
//                return true;
//        }
//        return false;
//    }
//}


//-------------------------------------------------------------------------------------

// Dynamic programming: Bottom-up approach.
// (Even though worst case time complexity is same as top-down approach, this runs much faster on large inputs).

// Time complexity : O(n^2):  For every element in the array, say i, we are looking at the next nums[i] elements
//                            to its right aiming to find a GOOD index. nums[i] can be at most nn, where nn is the length of array nums.

// Space complexity : O(n). This comes from the usage of the memoization table.

public class canJump {
    IndexType[] indexState; // Memoization array
    public boolean canJumpFn(int[] nums) {
        int len=nums.length, maxJump;
        if(len==0)
            return false;
        if(len==1)
            return true;

        // Initialize the memoization array
        indexState = new IndexType[len];
        for(int i=0;i<len;i++)
            indexState[i] = IndexType.UNKNOWN;

        indexState[len-1] = IndexType.GOOD;

        for(int position=len-2; position>=0; position--) {
            maxJump = Math.min(len-1, position + nums[position]);
            for(int nextPos = position+1; nextPos<=maxJump; nextPos++) {
                if(indexState[nextPos] == IndexType.GOOD) {
                    indexState[position] = IndexType.GOOD;
                    break;
                }
            }
        }
        return indexState[0] == IndexType.GOOD;
    }
}