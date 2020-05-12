package com.nitesh.array;

public class wiggleMaxLength {
    // Solution 3: Dynamic programming(Time: O(n), Space: O(1))
    public int wiggleMaxLengthFn(int[] nums) {
        int len=nums.length;

        if(len==0)
            return 0;
        // This is an evolved version of Solution 2 below.
        // Note that we only need the latest value of up and down
        // So just int vars will suffice. Arrays not needed like in Solution 2
        int up=0, down=0;

        for(int i=1; i<len; i++) {
            if(nums[i] > nums[i-1]) {
                up = 1+down;

            } else if(nums[i] < nums[i-1]) {
                down = 1+up;
            }
        }
        return 1 + Math.max(up, down);
    }
}



// Solution 2: Dynamic programming(Time: O(n), Space: 2*O(n) = O(n))
//
//    public int wiggleMaxLengthFn(int[] nums) {
//        int len=nums.length;
//
//        if(len==0)
//            return 0;
//
//        int[] up = new int[len];
//        int[] down = new int[len];
//
//        for(int i=1; i<len; i++) {
//            if(nums[i] > nums[i-1]) {
//                up[i] = 1+down[i-1];
//                down[i] = down[i-1]; //simply copy previous value over
//                // Note: Copying previous value over just means that we do not care
//                // about the length of the wiggle subsequence by adding this number
//                // as a "down" number at this location because that subsequence will
//                // not be the one with max length. And we care about only max length wiggle
//                // in this problem.
//            } else if(nums[i] < nums[i-1]) {
//                down[i] = 1+up[i-1];
//                up[i] = up[i-1];
//            } else {
//                // nums[i] is equal to nums[i-1]
//                // simply copy both values over
//                down[i] = down[i-1];
//                up[i] = up[i-1];
//            }
//        }
//        return 1 + Math.max(up[len-1], down[len-1]);
//    }

// ------------------------------------------------------------------------------------------


// Solution 1: Dynamic programming(Time: O(n^2), Space: 2*O(n) = O(n))
//
//    public int wiggleMaxLengthFn(int[] nums) {
//        int len=nums.length;
//
//        if(len==0)
//            return 0;
//
//        int[] up = new int[len];
//        int[] down = new int[len];
//
//        for(int i=1; i<len; i++) {
//            // Notice that if we instead go from j=i-1 to 0, it might be faster for trivial inputs.
//            // Worst case performance will still be the same of O(n^2)
//            for(int j=0; j<i; j++) {
//                if(nums[i] > nums[j])
//                    // Select this number in this subsequence if i is greater than j.
//                    // Now since i is greater than j, update the length of max wiggle considering down[j]
//                    up[i] = Math.max(up[i], 1+down[j]);
//                if(nums[i] < nums[j])
//                    // Vice versa of above comment
//                    down[i] = Math.max(down[i], 1+up[j]);
//            }
//        }
//        // Adding 1 here because we started with 0 in up[0] and down[0]
//        // but as per problem statement, wiggle length of array of size 1 is 1
//        return 1 + Math.max(up[len-1], down[len-1]);
//    }