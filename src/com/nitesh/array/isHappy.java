//        Write an algorithm to determine if a number is "happy".
//
//        A happy number is a number defined by the following process: Starting with any positive integer,
//        replace the number by the sum of the squares of its digits, and repeat the process until the number equals 1
//        (where it will stay), or it loops endlessly in a cycle which does not include 1. Those numbers for which this
//        process ends in 1 are happy numbers.
//
//        Example:
//
//        Input: 19
//        Output: true
//        Explanation:
//        1^2 + 9^2 = 82
//        8^2 + 2^2 = 68
//        6^2 + 8^2 = 100
//        1^2 + 0^2 + 0^2 = 1

package com.nitesh.array;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class isHappy {
    public boolean isHappyFn(int n) {
        Set<Integer> loop = new HashSet<>(); // Using an arraylist or hashset does not make much of difference in execution time
        int sum=0, isLoop=0;
        loop.add(n);
        while(sum!=1) {
            // System.out.println("Sum = " + sum);
            sum = getSumDigits(n);
            // store the sum in the loop list to detect a cycle later
            if(sum!= 1 && loop.contains(sum)) {
                isLoop=1;
                break;
            }
            loop.add(sum);
            n=sum;
        }
        return (isLoop!=1);
    }

    private int getSumDigits(int n) {
        int rem, sum=0;
        while(n!=0) {
            rem=n%10;
            sum = sum + (rem*rem);
            n=n/10;
        }
        return sum;
    }
}
