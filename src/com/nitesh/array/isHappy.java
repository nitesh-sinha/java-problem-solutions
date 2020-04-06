//        Write an algorithm to determine if a number is "happy".
//
//        A happy number is a number defined by the following process: Starting with any positive integer, replace the number by the sum of the squares of its digits, and repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1. Those numbers for which this process ends in 1 are happy numbers.
//
//        Example:
//
//        Input: 19
//        Output: true
//        Explanation:
//        12 + 92 = 82
//        82 + 22 = 68
//        62 + 82 = 100
//        12 + 02 + 02 = 1

package com.nitesh.array;

import java.util.ArrayList;
import java.util.List;

public class isHappy {
    public boolean isHappyFn(int n) {
        List<Integer> loop = new ArrayList<Integer>();
        int sum=0, isLoop=0;
        loop.add(n);
        while(sum!=1) {
            System.out.println("Sum = " + sum);
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
