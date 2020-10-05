//        You are given coins of different denominations and a total amount of money amount.
//        Write a function to compute the fewest number of coins that you need to make up that amount.
//        If that amount of money cannot be made up by any combination of the coins, return -1.
//
//        Example 1:
//
//        Input: coins = [1, 2, 5], amount = 11
//        Output: 3
//        Explanation: 11 = 5 + 5 + 1
//
//
//        Example 2:
//
//        Input: coins = [2], amount = 3
//        Output: -1
//        Note:
//        You may assume that you have an infinite number of each kind of coin.

// Time complexity: O(nk) where n = no. of coins in input array; k = amount

package com.nitesh.array;

public class coinChange {
    public int coinChangeFn(int[] coins, int amount) {
        if(amount <= 0)
            return 0;
        int len=coins.length, remAmount;
        int[] numCoins = new int[1+amount];
        numCoins[0] = 0;

        for(int i=1; i<=amount; i++) {
            numCoins[i] = Integer.MAX_VALUE;
            for(int j=0; j<len; j++) {
                // Pick every coin and subtract it from current amount(that we're working with)
                // Check if no. of coins needed with this coin is less than the current obtained number
                // If so, store this new min value. Else continue with the next coin value
                if(i >= coins[j]) {
                    // This coin value can be considered valid for our current amount
                    remAmount = i-coins[j];
                    if(numCoins[remAmount] != Integer.MAX_VALUE && 1+numCoins[remAmount] < numCoins[i])
                        numCoins[i] = 1 + numCoins[remAmount];
                }
            }
        }
        return (numCoins[amount]==Integer.MAX_VALUE ? -1 : numCoins[amount]);
    }
}
