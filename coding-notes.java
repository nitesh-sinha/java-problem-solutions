Start date: Aug 1st week, 2016
Author: Nitesh
-----------------------------------------------------------------------------------

1.  Sum of two integers without using + or -

public class Solution {
    public int getSum(int a, int b) {
        int sum = a;
        while(b != 0) {
            // continue adding until there is no carry generated from previous sum
            sum = a ^ b; // add w/o carry
            b = (a & b) << 1; // (a&b) gives carry. Shift carry left by 1
            a = sum;
        }
        return sum;
    }
}

/*
a=1010
b=1101

sum=1010 ^ 1101 = 0111
b=10000
a=0111

sum=00111 ^ 10000 = 10111
b=0
a=10111 -> result

*/


===================================================

3. Intersection of two arrays:

public class Solution {
    int prev = -1; // null equivalent
    int numResult = 0;
    public int[] intersection(int[] nums1, int[] nums2) {
        List<Integer> resultList = new ArrayList<Integer>();
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        for(int i=0; i< nums1.length; i++) {
            // compare only new nums in nums1 with nums2 array
            // except first num in num1(which you always compare)
            int searchResIndex = Arrays.binarySearch(nums2, nums1[i]);
            if (!seenBefore(nums1, i) && searchResIndex >= 0) {
                resultList.add(nums1[i]);
            }
        }

        // Convert List<Integer> to int[]
        int[] result = new int[resultList.size()];
        for(int i=0; i< result.length; i++) {
            result[i] = resultList.get(i).intValue();
        }
        return result;
    }

    private boolean seenBefore(int[] nums, int index) {
        if (index == 0) {
            // set prev for the first time
            prev = nums[index];
            return false;
        }
        if (nums[index] == prev)
            return true;
        prev = nums[index];
        return false;
    }
}

// Time complexity: O(n1 * log(n2)) where n1=length of nums1 and n2=length of nums2

==========================================================

4. Delete node in a linked list(given just the node to delete):

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public void deleteNode(ListNode node) {
        if (node == null)
            return;
        // copy values from next nodes to prev nodes
        if (node != null && node.next != null) {
            node.val = node.next.val;
            node.next = node.next.next;
            node = node.next;
        }
    }
}

// Time Complexity: O(1)

===========================================================

5. Reverse level order traversal(since return type is List<List<Integer>>, it is almost impossible to use
    recursive solution. hence using iterative solution):


public class Solution {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();

        if(root==null){
            return result;
        }
        Stack<List> stack = new Stack<List>();
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        while(!queue.isEmpty()){
            ArrayList <Integer> list =new ArrayList<Integer>();
            int n=queue.size();
            for(int i=0; i<n; i++){
                // one entire level of nodes
                TreeNode node=queue.remove();
                list.add(node.val);
                if(node.left!=null){
                    queue.add(node.left);
                }
                if(node.right!=null){
                    queue.add(node.right);
                }
            }
            stack.push(list);
        }
        while(!stack.isEmpty()){
            result.add(stack.pop());
        }
        return result;

    }
}

// Time complexity: O(n) where n=no. of nodes in the tree.

=================================================

6. Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).

public class Solution {
    public boolean isSymmetric(TreeNode root) {
        if (root == null)
            return true;
        return isLeftRightSymmetric(root.left, root.right);
    }

    private boolean isLeftRightSymmetric(TreeNode left, TreeNode right) {
        if (left == null || right==null)
            return left==right; // true for both null
        if (left.val!=right.val)
            return false;

        if(isLeftRightSymmetric(left.left, right.right) && isLeftRightSymmetric(left.right, right.left))
            return true;
        return false;

    }
}

// Time complexity: O(n) where n=no. of nodes in tree. Since each node is visited once.

==============================================


===============================
9. implement strStr() ---> KMP algo.

The naive implementation of "needle" search in a "haystack" takes O(mn) time where m is length of needle and n is length of haystack.
KMP algo achieves the same result in O(m+n) time.

Algo: Keep comparing needle in haystack until there is a mismatch in character.
      As soon as there is a mismatch, look at the substring in needle before the mismatch char.
      In that substring, check if there is a suffix which is also a prefix.
      If one exists, then continue match from the character after the prefix in needle.
      Eg: haystack = "abcxabcdabxabcdabcdabcy", needle="abcdabcy"
      When first mismatch occurs at 4th character "x" and "d" of haystack and needle respectively,
      the substr in needle before the mismatch is "abc" which does not have a sufix which is also a prefix.
      So, continue with comparing first char("a") of needle with 4th char("x") of haystack.

      Next match is starting 5th char of haystack until 10th char i.e. "abcdab" with that of needle. Mismatch is at "x" and "c"
      The substr in needle before the mismatch is "abcdab" which has a sufix "ab" which is also a prefix.
      So, continue comparing 3rd character onwards of needle which is "c" with "x". Now no suffix in prefix in substring "ab".


To achieve this, we need to pre-process the needle.
Pre-processing algo: Eg: needle = "aabaabaaa"

    first element of output of preprocessing list is always 0.
    Start with j=0, i=1;
        needle[j]==needle[i]. So output[i] = output[j]+1 = 0+1=1
        Increment both j and i.
    j=1, i=2;
        needle[j]!=needle[i] i.e. a!=b. So, j=output[j-1]=output[0]=0;
    j=0, i=2;
        needle[j]!=needle[i] i.e. a!=b, So,  output[i] = 0
        Increment i.
    j=0, i=3;
        needle[j]==needle[i]. So output[i] = output[j]+1 = 0+1=1
        Increment both j and i.
    j=1, i=4;
        needle[j]==needle[i]. So output[i] = output[j]+1 = 1+1=2
        Increment both j and i.
    j=2, i=5;
        needle[j]==needle[i]. So output[i] = output[j]+1 = 2+1=3
        Increment both j and i.
    j=3, i=6;
        needle[j]==needle[i]. So output[i] = output[j]+1 = 3+1=4
        Increment both j and i.
    j=4, i=7;
        needle[j]==needle[i]. So output[i] = output[j]+1 = 4+1=5
        Increment both j and i.
    j=5, i=8;
        needle[j]!=needle[i]. i.e. b!=a. So j=output[j-1]=output[4]=2
    j=2, i=8;
        needle[j]!=needle[i]. i.e. b!=a. So j=output[j-1]=output[1]=1;
    j=1, i=8;
        needle[j]==needle[i]. So output[i] = output[j]+1 = 1+1=2

    Final output array = [0,1,0,1,2,3,4,5,2]

    Now use this output array to search needle in haystack.

https://discuss.leetcode.com/topic/3576/accepted-kmp-solution-in-java-for-reference


=================================
11. Palindrome linked list -> O(n) time complexity and O(1) space complexity solution.

Algo:
    1. Keep slow and fast ptrs.
    2. Move slowptr by 1 and fast by 2 until (fast.next!=null && fast.next.next!=null)
    3. Reverse linked list starting from node after the slowptr node. i.e. now the second half of
        linked list should be reverse.
    4. Keep two ptrs(p1=head of original list, p2=head of reverse list). Iterate and compare for equality of elements.
    5. Return false if any comparison fails.


=====================================

13. Isomorphic strings:

Two strings are isomorphic if the characters in s can be replaced to get t.

All occurrences of a character must be replaced with another character while preserving the order of characters.
No two characters may map to the same character but a character may map to itself.

For example,
Given "egg", "add", return true.

Given "foo", "bar", return false.

Given "paper", "title", return true.




    public boolean isIsomorphic(String s, String t) {
        return isCorrectlyReplaced(s,t) && isCorrectlyReplaced(t,s);
    }

    private boolean isCorrectlyReplaced(String a, String b) {
        // Checks if a is correctly replaced by b
        Map<Character, Character> replacementMap = new HashMap<>();
        boolean isCorrect = true;
        for(int i=0;i<a.length();i++) {
            // Assuming both a and b have same length
            if(!replacementMap.containsKey(a.charAt(i))) {
                replacementMap.put(a.charAt(i), b.charAt(i));
            } else {
                if (b.charAt(i) != replacementMap.get(a.charAt(i))) {
                    isCorrect = false;
                    break;
                }
            }
        }
        return isCorrect;
    }

    // Time complexity: O(n) where n=length of string(assuming both strings are of same length)

==============================================

14. Number of set bits: (faster method. loop runs as many times as the number of set bits)


 public int hammingWeight(int n) {
        int count;
        for(count=0; n!=0; count++) {
            n = n&(n-1); // clears the rightmost set bit in n
        }
        return count;
 }

 // Time complexity: O(k) where k=no.of set bits in n. Worst case: All bits are set. O(32)

 =============================================

 15. Find the min depth of a binary tree: The minimum depth is the number of nodes along the shortest path from the root node
     down to the nearest leaf node.

  public int minDepth(TreeNode root) {
        if (root==null)
            return 0;

        int leftDepth = minDepth(root.left);
        int rightDepth = minDepth(root.right);

        // Dont consider the path whose depth is zero
        // Eg: root=1, leftchild=2, no rightchild.
        // Output should be 2 and not 1.
        if (leftDepth==0)
            return 1+rightDepth;

        if (rightDepth==0)
            return 1+leftDepth;

        // Compare depths only when both of them are non-zero
        if (leftDepth<rightDepth)
            return 1 + leftDepth;
        return 1 + rightDepth;
    }

    // Time complexity: O(n) where n=no. of nodes in the tree since each node is visited exactly once.


==================================================
17. Print all root to leaf paths:

public class Solution {
    private List<String> paths = new ArrayList<>();

    public List<String> binaryTreePaths(TreeNode root) {
        String s = "";
        getPaths(root, s);
        return paths;
    }

    private void getPaths(TreeNode root, String s) {
        if (root==null)
            return;

        s += String.valueOf(root.val);
        // Check for leafnode
        if(root.left==null && root.right==null) {
            paths.add(s);
            return;
        }
        // For non-leaf nodes
        s += "->";
        getPaths(root.left, s);
        getPaths(root.right, s);
    }
}

// Time complexity: O(n) where n=no. of nodes in tree since each node is visited once.

================================================
20. Given an array of numbers nums, in which exactly two elements appear only once and all the other elements appear exactly twice.
    Find the two elements that appear only once.

     public int[] singleNumber(int[] nums) {
        int xor=0, oneGrp=0, otherGrp=0;
        for (int num : nums)
            xor = xor ^ num;

        // Nullify all bits except the rightmost bit in xor number
        xor = xor & ~(xor-1);

        // Bitwise AND "xor" with all nums
        // and divide into two groups(based on 0 and non-zero values)
        // distinct nums should now be in two groups
        // xor nums in both grps to get the distinct nums
        for(int num : nums) {
            if ((xor&num)==0)
                oneGrp = oneGrp ^ num;
            else
                otherGrp = otherGrp ^ num;
        }
        int[] result={oneGrp, otherGrp};
        return result;
    }

    // Time complexity: O(n) where n=no. fo elements in nums

================================================
21. Find the minimum number of squares whose sum gives a certain number.
    // For more info: http://www.geeksforgeeks.org/minimum-number-of-squares-whose-sum-equals-to-given-number-n/
    // Dynamic programming solution is a lot faster for n>50

    public int numSquares(int n) {
        // All numbers can be obtained from sum of perfect squares
        // in worst case, n = (1*1 + 1*1 + ....n times)
        if (n<=3)
            return n;

        // For n>=4
        int[] dp = new int[n+1]; // dp[i] is the no. of squares which gives i.
        // For n<=3, result=n
        dp[0]=0;
        dp[1]=1;
        dp[2]=2;
        dp[3]=3;

        for(int j=4;j<=n;j++) {
            dp[j]=j; // Assuming worst case scenario(addition of all 1*1)
            for (int i=1;i*i<=j;i++) {
                dp[j] = Math.min(dp[j], 1 + dp[j-i*i]); // Adding 1 here to consider i*i that we are subtracting from j here
            }
        }
        return dp[n];
    }

    // Time complexity: O(nk) where n= input number; k=no. of squares below n

=================================================

22. Coin change problem: Min. no. of coins to obtain a certain amount.

    public int coinChange(int[] coins, int amount) {
        int len=coins.length;

        if (amount<0)
            return -1;

        if (amount==0)
            return 0;

        int[] dp = new int[amount+1]; // dp[i] is the min. no. of coins to obtain amount i
        dp[0]=0;
        for(int j=1;j<=amount;j++) {
            dp[j]=Integer.MAX_VALUE;
            for(int i=0;i<len;i++) {
                // Pick every coin and subtract it from current amount(that we're working with)
                // Check if no. of coins needed with this coin is less than the current obtained number
                // If so, store this new min value. Else continue with the next coin value
                if (coins[i] <= j) {
                    // This coin value can be considered valid for our current amount
                    int subResult = dp[j-coins[i]];
                    if (subResult != Integer.MAX_VALUE && subResult+1 < dp[j])
                        dp[j] = 1+subResult;
                }
            }
        }

        if (dp[amount]==Integer.MAX_VALUE)
            return -1; // Not possible with the current set of coin values
        return dp[amount];
    }

    // Time complexity: O(nk) where n=no. of coins in input array; k=amount


========================================

24. Top K Frequent numbers in an array. Solution should have time complexity better than O(NlogN).

Check my solution for using various methods of iterating over Map etc.

    public List<Integer> topKFrequent(int[] nums, int k) {
        int count, cur=0;
        if (nums.length==0)
            return null;

        Map<Integer, Integer> countMap = new HashMap<>();
        List<Integer> result = new ArrayList<>();

        // Create map of num to its count
        for(int num : nums) {
            count = countMap.getOrDefault(num, 0);
            countMap.put(num, count+1);
        }


        // Alternate logic: Create a maxHeap of number to its count
        // Arrange maxHeap based on count values
        // Pick k elements from maxheap
        // Time complexity of alternate solution: O(k * log(n)) where n=length of nums in maxHeap; k=input k.
        while(cur<k) {
            Map.Entry<Integer, Integer> maxEntry = null;
            for(Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
                if (maxEntry==null || entry.getValue().compareTo(maxEntry.getValue()) > 0)
                    maxEntry = entry;
            }
            result.add(maxEntry.getKey());
            maxEntry.setValue(Integer.MIN_VALUE);
            cur++;
        }
        return result;
    }

    // Time complexity: O(kn) where n=length of nums in maxHeap; k=input k.
========================================

25. Break an integer N into two or more positive integers such that they sum upto N.
    Return the maximum product of those positive integers you can get.


    public int integerBreak(int n) {
        // 2<=n<=58
        int[] dp = new int[n+1]; // dp[i] stores the max product of two or more integers adding up to 'i'
        int max;
        dp[0]=0;
        dp[1]=0;
        dp[2]=1;

        // For every i, go upto i/2(since after j=i/2, the pair j & (i-j) start to repeat)
        for(int i=3;i<=n;i++) {
            dp[i]=Integer.MIN_VALUE; // Start with MIN_VALUE since we've to find the max product
            for(int j=1;j<=i/2;j++) {
                // Inner Math.max() needed so that we pick max of either the number or the parts which make up that number.
                // Eg: If number=2 then dp[2]=1. If 2 is one of the +ve integers that makes up N, then we should select 2
                // i.e. (i-j) when considering the parts of N and not dp[i-j] i.e.dp[2]=1
                dp[i]=Math.max(dp[i], j*Math.max(i-j, dp[i-j]));
            }
        }
        return dp[n];
    }

    // Time complexity: O(n^2)

=======================================

26. House robber when you can only rob alternate houses. Houses are in a straight line. Get max money while robbing tonight.

    public int rob(int[] nums) {
        // zero house
        int len=nums.length;
        if(len==0)
            return 0;

        // more than 0 house
        int[] maxMoney = new int[len]; // maxMoney[i] is the max money that can be robbed if i'th house is the last house in the row of houses
        if(len>1) {
            maxMoney[0] = nums[0];
            maxMoney[1] = Math.max(nums[0], nums[1]);
            for(int i=2;i<len;i++) {
                // since we accumulate sum in maxMoney array as we iterate
                // to get max money select max of previous or (previous-1 + current)
                maxMoney[i] = Math.max(maxMoney[i-2]+nums[i], maxMoney[i-1]);
            }
        }
        return maxMoney[len-1];
    }

    // Time complexity: O(n) where n=no. of houses

========================================

27. Given a string array words, find the maximum value of length(word[i]) * length(word[j]) where the two words do not share common letters.
You may assume that each word will contain only lower case letters. If no such two words exist, return 0.

// The algo uses a very intelligent logic of calculating duplicate letters across all
// words in an array.
// CAUTION: This algo considers duplicate letters in the same word as a single occurrence of that letter.

    public int maxProduct(String[] words) {
        if(words==null || words.length==0)
            return 0;

        int len=words.length, maxProduct=0;
        // letterBitSet[i] stores a number whose binary equivalent has set bits at position per the order of that
        // letter in English lang for word[i].
        // Eg: if word="abc", then binary
        // equivalent of corresponding number is 0000....111
        int[] letterBitSet = new int[len];

        for(int i=0;i<len;i++) {
            String word = words[i];
            for(int j=0;j<word.length();j++) {
               letterBitSet[i] |= (1<<word.charAt(j)-'a');
            }
        }

        for(int i=0;i<len;i++) {
            for(int j=i+1;j<len;j++) {
                // No letter should be the same and product of lengths should be max
                if((letterBitSet[i] & letterBitSet[j])==0 && words[i].length() * words[j].length() > maxProduct)
                    maxProduct = words[i].length() * words[j].length();
            }
        }

        return maxProduct;
    }

    // Time complexity: O(n) + O(k^2) where n=no. of all chars in all words; k=no. of words

========================================

28.  Given an integer array with all positive numbers and no duplicates, find the number of possible combinations that add
     up to a positive integer target.

Example:

nums = [1, 2, 3]
target = 4

The possible combinations are:
(1, 1, 1, 1)
(1, 1, 2)
(1, 2, 1)
(1, 3)
(2, 1, 1)
(2, 2)
(3, 1)

Note that different sequences are counted as different combinations.

Therefore the output is 7.


    DP solution:
    -------------
    public int combinationSum4(int[] nums, int target) {
        int[] result = new int[target+1];
        Arrays.sort(nums);
        for(int i=1;i<=target;i++) {
            result[i] = 0; // result[i] is the no. of different sequences where each sequence adds up to 'i'
            for(int num : nums) {
                if (num > i)
                    break;
                else if(num==i)
                    result[i]++;
                else
                    result[i] += result[i-num];
            }
        }
        return result[target];
    }

    // Time complexity: O(nk) where n=length of nums; k=target


===============================================

30. Given n, how many structurally unique BSTs (binary search trees) that store values 1...n?

    public int numTrees(int n) {
        /**
         * To build a tree that contains {1,2,3,4,5}. First we pick 1 as root; for the left sub tree,
         * there are no numbers; for the right sub tree, we need to count how many possible trees can be
         * constructed from {2,3,4,5}, apparently it's the same number as {1,2,3,4}. So the total number
         * of trees under "1" picked as root is dp[0] * dp[4] = 14. (assume dp[0]=1). Similarly, root 2
         * has dp[1]*dp[3] = 5 trees. root 3 has dp[2]*dp[2] = 4, root 4 has dp[3]*dp[1]= 5 and root 5 has
         * dp[4]*dp[0] = 14. Finally sum them up and it's done.
         **/

        int[] dp = new int[n+1]; // dp[i] is the number of unique BST's possible with i nodes
        dp[0]=1;
        for(int i=1;i<=n;i++) {
            for(int j=0;j<=i-1;j++) {
                dp[i] += dp[j] * dp[i-j-1];
            }
        }
        return dp[n];

    }

===============================================
31. Generate permutations of a given set of numbers:

    public List<List<Integer>> permute(int[] nums) {
        /** Heap's algorithm : This algorithm minimizes movement of numbers: it generates each permutation from the previous
            one by interchanging a single pair of elements; the other N−2 elements are not disturbed. The idea is to fix the
            last element and generate permutations of the first (N-1) elements recursively.
         * */
        List<List<Integer>> results = new ArrayList<>();
        List<Integer> eachPerm = new ArrayList<>();

        getPermutations(results, eachPerm, nums, nums.length);
        return results;
    }

    private void getPermutations(List<List<Integer>> results, List<Integer> eachPerm, int[] nums, int size) {
        int temp;
        // size is the length of the array we are currently working on
        if (size==1) {
            // we have one unique permutation
            // generate eachPerm list and add it to results
            for(int i=0;i<nums.length;i++)
                eachPerm.add(nums[i]);
            List<Integer> innerRes = new ArrayList<>(eachPerm);
            results.add(innerRes);
        }

        /**
         * In each iteration, the algorithm will produce all of the permutations that end with the element that has just been moved to the "last" position
         * */

        for(int i=0;i<size;i++) {
            eachPerm = new ArrayList<>();
            getPermutations(results, eachPerm, nums, size-1);

            if (size%2 ==1) {
                // Current array is odd sized, so swap first and last element
                temp=nums[0];
                nums[0]=nums[size-1];
                nums[size-1]=temp;
            } else {
                // Current array is even sized, so swap i'th and last element
                temp=nums[i];
                nums[i]=nums[size-1];
                nums[size-1]=temp;
            }
        }
    }


============================================

33. Binary tree right side view: Given a binary tree, imagine yourself standing on the right side of it,
    return the values of the nodes you can see ordered from top to bottom.

For example:
Given the following binary tree,

   1            <---
 /   \
2     3         <---
 \     \
  5     4       <---

You should return [1, 3, 4]


    public class Solution {
        int maxHeight;
        public List<Integer> rightSideView(TreeNode root) {
            List<Integer> result = new ArrayList<>();
            // Empty tree
            if(root==null)
                return result;

            // Atleast 1 node tree
            int curHeight=0;
            maxHeight=0;

            traverseTree(root, curHeight, result);
            return result;
        }

        private void traverseTree(TreeNode root, int curHeight, List<Integer> result) {
            if(root==null)
                return;
            curHeight++;

            if(curHeight>maxHeight) {
                result.add(root.val);
                maxHeight=curHeight;
            }

            traverseTree(root.right, curHeight, result);
            traverseTree(root.left, curHeight, result);

        }
    }

    // Time complexity: O(n) where n=no. of nodes in the tree

==============================================

34.  Given a binary tree:

Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.

Initially, all next pointers are set to NULL. Eg:

Given the following perfect binary tree,

         1
       /  \
      2    3
     / \  / \
    4  5  6  7


After calling your function, the tree should look like:

         1 -> NULL
       /  \
      2 -> 3 -> NULL
     / \  / \
    4->5->6->7 -> NULL


Very elegant solution(If you consider the list in one level as a linked list while iterating it, the solution is very obvious).


    /**
     * Definition for binary tree with next pointer.
     * public class TreeLinkNode {
     *     int val;
     *     TreeLinkNode left, right, next;
     *     TreeLinkNode(int x) { val = x; }
     * }
     */
    public class Solution {
        public void connect(TreeLinkNode root) {
            if(root==null)
                return;

            TreeLinkNode depth=root, level=null;
            while(depth.left!=null) {
                // depth ptr to iterate from top to bottom
                level=depth;
                while(level!=null) {
                    // level ptr to iterate over all nodes at same level
                    level.left.next=level.right;
                    if(level.next!=null)
                        level.right.next=level.next.left;
                    level=level.next;
                }
                depth=depth.left;
            }
        }
    }

    // Time complexity: O(n) where n=no. of nodes in the tree

================================================
35.  Given an unsorted array return whether an increasing subsequence of length 3 exists in the array or not.

Formally the function should:

    Return true if there exists i, j, k
    such that arr[i] < arr[j] < arr[k] given 0 ≤ i < j < k ≤ n-1 else return false.

Your algorithm should run in O(n) time complexity and O(1) space complexity. Very clever solution!!


    public boolean increasingTriplet(int[] nums) {
        if(nums.length<3)
            return false;

        int small=Integer.MAX_VALUE, middle=Integer.MAX_VALUE;
        for(int num : nums) {
            if (num>middle)
                return true; // because middle is guaranteed to be greater than small

            if(num>small && num<middle)
                middle=num;

            if(num<small)
                small=num;
        }
        return false;
    }

    // Time complexity: O(n) where n=length of nums


==============================================

38. Longest Increasing Subsequence(Very important question):

Given an unsorted array of integers, find the length of longest increasing subsequence.

For example,
Given [10, 9, 2, 5, 3, 7, 101, 18],
The longest increasing subsequence is [2, 3, 7, 101], therefore the length is 4.
Note that there may be more than one LIS combination, it is only necessary for you to return the length.


    public int lengthOfLIS(int[] nums) {
        int len=nums.length, max;
        if(len==0)
            return 0;

        int[] lis = new int[len]; // lis[i] is the length of LIS with num[i] as the last number in LIS

        // prepopulate lis with all 1's(Assuming input in decreasing order)
        for(int i=0;i<len;i++)
            lis[i]=1;

        for(int i=1;i<len;i++) {
            for(int j=0;j<i;j++) {
                if (nums[i]>nums[j] && lis[i]<lis[j]+1)
                    // multiple LIS might be possible when considering array ending with nums at i'th postion;
                    // so (lis[i]<lis[j]+1) condition ensures that we select the LIS which has the maximum number of numbers
                    lis[i]=lis[j]+1;
            }
        }

        // Pick max in the lis array
        max=lis[0];
        for(int i=1;i<len;i++)
            if(lis[i]>max)
                max=lis[i];

        return max;
    }

    // Time complexity: O(n^2)

=====================================

40. Peeking iterator: Given an Iterator 'class' intrface with methods: next() and hasNext(), design and
    implement a PeekingIterator that support the peek() operation -- it essentially peek() at the element
    that will be returned by the next call to next().

    /** My solution: https://leetcode.com/problems/peeking-iterator/
    **/
=======================================

41. Wiggle Subsequence: A sequence of numbers is called a wiggle sequence if the differences between successive numbers strictly
    alternate between positive and negative. The first difference (if one exists) may be either positive or negative. A sequence with
    fewer than two elements is trivially a wiggle sequence.

For example, [1,7,4,9,2,5] is a wiggle sequence because the differences (6,-3,5,-7,3) are alternately positive and negative.
In contrast, [1,4,7,2,5] and [1,7,4,5,5] are not wiggle sequences, the first because its first two differences are positive and
the second because its last difference is zero.

Given a sequence of integers, return the length of the longest subsequence that is a wiggle sequence. A subsequence is obtained by
deleting some number of elements (eventually, also zero) from the original sequence, leaving the remaining elements in their original
order.

Examples:

Input: [1,7,4,9,2,5]
Output: 6
The entire sequence is a wiggle sequence.

Input: [1,17,5,10,13,15,10,5,16,8]
Output: 7
There are several subsequences that achieve this length. One is [1,17,10,13,10,16,8].

Input: [1,2,3,4,5,6,7,8,9]
Output: 2


    public int wiggleMaxLength(int[] nums) {
        int len=nums.length, maxLength=1, prev, startIndex=1;
        boolean needHigher;

        if(len<2)
            return len;

        // Skip over same elements from start
        while(startIndex<len && nums[startIndex]==nums[startIndex-1])
            startIndex++;

        if(startIndex==len)
            // All elements in nums are repeating
            return 1;

        // Atleast two unique elements in nums
        needHigher = nums[startIndex]>nums[startIndex-1];
        for(int i=startIndex;i<len;i++) {
            if(needHigher) {
                if(nums[i]>nums[i-1]) {
                    // Needed higher and got higher
                    maxLength++;
                    needHigher = !needHigher;

                }
            } else {
                if(nums[i]<nums[i-1]) {
                    // Needed lower and got lower
                    maxLength++;
                    needHigher = !needHigher;
                }
            }
        }
        return maxLength;
    }

    // Time complexity: O(n) where n= no. of elements in num

 ================================================
 42. A general approach to backtracking questions in Java (Subsets, Permutations, Combination Sum, Palindrome Partitioning)

 https://discuss.leetcode.com/topic/46159/a-general-approach-to-backtracking-questions-in-java-subsets-permutations-combination-sum-palindrome-partitioning

=================================================
43. H-index:  Given an array of citations (each citation is a non-negative integer) of a researcher, write a function to compute the
    researchers h-index.

According to the definition of h-index on Wikipedia: "A scientist has index h if h of his/her N papers have at least h citations each,
and the other N−h papers have no more than h citations each."

For example, given citations = [3, 0, 6, 1, 5], which means the researcher has 5 papers in total and each of them had received 3, 0, 6, 1, 5 citations respectively.
Since the researcher has 3 papers with at least 3 citations each and the remaining two with no more than 3 citations each, his h-index is 3.

Note: If there are several possible values for h, the maximum one is taken as the h-index.

    public int hIndex(int[] citations) {
        int len=citations.length, count=0;
        int[] bucket = new int[len+1]; // bucket[i] is the no. of papers which have citations > i

        // Populate bucket array(counting sort)
        for(int c : citations) {
            if(c>len)
                // if number is > max bucket index, then count it in the last bucket
                bucket[len]++;
            else
                bucket[c]++;
        }

        // Iterate from end of bucket to start
        for(int i=len;i>=0;i--) {
            count+=bucket[i];
            if(count>=i)
                return i; // since count no. of papers have citations of atleast i; and i<count which means i no. of papers have citations of atleast i(which is the h-index definition)
        }
        return 0;
    }

    // Time complexity: O(n) where n=length of citations[]

=======================================================

45. Bitwise AND of number range: Given a range [m, n] where 0 <= m <= n <= 2147483647, return the
bitwise AND of all numbers in this range, inclusive.

    public int rangeBitwiseAnd(int m, int n) {
        if(m == 0){
            return 0;
        }
        int numRightShifts = 0;
        // Keep shifting m and n by 1 bit position to right
        // until both are equal
        while(m != n){
            m >>= 1;
            n >>= 1;
            numRightShifts++;
        }
        // Shift m left by number of times it was shifted right before
        return m * (int)Math.pow(2, numRightShifts);
    }

    // Time complexity: O(32) or O(64) in worst case scenario for 32/64 bit int size.

================================================
46. Longest Substring with At Least K Repeating Characters: Find the length of the longest substring T of a
given string (consists of lowercase letters only) such that every character in T appears no less than k times.

Example 1:
Input:
s = "aaabb", k = 3
Output:
3
The longest substring is "aaa", as 'a' is repeated 3 times.


Example 2:
Input:
s = "ababbc", k = 2
Output:
5
The longest substring is "ababb", as 'a' is repeated 2 times and 'b' is repeated 3 times.


    public int longestSubstring(String s, int k) {
        Map<Character, Integer> charCount = new HashMap<>();
        int i,j, max=0;
        char c;

        for(i=0;i<s.length();i++) {
            c = s.charAt(i);
            if(charCount.get(c)==null)
                charCount.put(c, 1);
            else
                charCount.put(c, charCount.get(c)+1);
        }

        // Characters to split the string at(for individual substring calculation)
        Set<Character> splitChars = new HashSet<>();
        for(Map.Entry<Character, Integer> entry : charCount.entrySet()) {
            if(entry.getValue()<k)
                splitChars.add(entry.getKey());
        }

        if(splitChars.size()==0)
            return s.length();

        i=0;
        j=0;
        while(j<s.length()) {
            c = s.charAt(j);
            if(splitChars.contains(c)) {
                if(i!=j) {
                    // Connsider the left half of the string after split
                    max=Math.max(max, longestSubstring(s.substring(i,j), k));
                }
                i=j+1;
            }
            j++;
        }

        // Consider the right half of the string after split
        if(i!=j)
          max=Math.max(max, longestSubstring(s.substring(i,j), k));

        return max;
    }

==========================================
47. Convert Sorted List to Binary Search Tree(Remember inorder traversal??):

Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.


    public class Solution {
        ListNode node; // pointer to iterate over the linked list
        public TreeNode sortedListToBST(ListNode head) {
            int size=0;
            ListNode iter;
            iter=node=head;
            // Calculate length of sorted list
            while(iter!=null) {
                size++;
                iter=iter.next;
            }
            return createBSTinorder(0, size-1);
        }

        // Create BST by traversing from start to end of "sorted" list, thereby picking
        // each node from list and creating the BST as we traverse in-order. In-order traversal
        // because thats the inherent nature of adding sorted numbers to BST
        private TreeNode createBSTinorder(int start, int end) {
            if(start>end)
                return null;
            // Here start, mid and end are not used for indexing;
            // rather they just serve as counters to check when to stop iterating recursively
            int mid = start + (end-start)/2;
            // LEFT
            TreeNode left = createBSTinorder(start, mid-1);

            // ROOT
            TreeNode bst = new TreeNode(node.val);
            bst.left = left;
            node=node.next;

            // RIGHT
            TreeNode right = createBSTinorder(mid+1, end);
            bst.right=right;

            return bst;
        }
    }

    // Time complexity: O(n) where n=size of the linked list since each node is visited exactly once.

===========================================
 48. Convert Sorted Array to Binary Search Tree(Remember preorder Traversal??)
Given an array where elements are sorted in ascending order, convert it to a height balanced BST.

    // Very similar to a binary search solution
    public class Solution {
        public TreeNode sortedArrayToBST(int[] nums) {
            return createBST(nums, 0, nums.length-1);
        }

        private TreeNode createBST(int[] nums, int start, int end) {
            if (start>end)
                return null;
            int mid = start + (end-start)/2;
            // ROOT
            TreeNode node = new TreeNode(nums[mid]);
            // LEFT
            node.left = createBST(nums, start, mid-1);
            // RIGHT
            node.right = createBST(nums, mid+1, end);

            return node;
        }
    }

    // Time complexity: O(n) where n=size of the input array since each element is visited exactly once.


============================================

52. Count no. of inversions in an array:
    How far (or close) the array is from being sorted. If array is already sorted then inversion count is 0. If array is
    sorted in reverse order that inversion count is the maximum. Formally speaking, two elements a[i] and a[j] form an
    inversion if a[i] > a[j] for i < j.


    public int countInversions(int[] nums) {
        if(nums==null || nums.length==0)
            return 0;

        return mergeSort(nums, 0, nums.length-1);
    }

    private int mergeSort(int[] nums, int lo, int hi) {
        if(lo==hi)
            return 0;

        int mid=lo+(hi-lo)/2;
        return mergeSort(nums, lo, mid) + mergeSort(nums, mid+1, hi) + merge(nums, lo, mid+1, hi);
    }

    private int merge(int[] nums, int lo, int mid, int hi) {
        int[] buff = new int[nums.length];
        int count=0;

        for(int buffIndex=lo, lptr=lo, hptr=mid; buffIndex<=hi; buffIndex++) {
            if(hptr>hi || (lptr<mid && nums[lptr] <= nums[hptr])) {
                // lptr is within left subarray and num in left subarray at current index <= that of right subarray
                buff[buffIndex] = nums[lptr++];
            } else {
                // Either lptr is beyond left subarray length(so copy remaining nums from right subarray to buff) or num is inverted.
                count=count+(mid-lptr); // since left subarray is sorted, all numbers beyond lptr in left subarray is greater than nums[hptr]
                buff[buffIndex] = nums[hptr++];
            }
        }

        System.arraycopy(buff, lo, nums, lo, hi-lo+1); // arraycopy(src, srcIndex, dest, destIndex, length)
        return count;
    }

    // Time complexity: O(n*log n)



===============================================
Check topological sorting in graphs using DFS: http://www.geeksforgeeks.org/topological-sorting/

==============================================
58. Maximal Square:  Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1s and return its area.

For example, given the following matrix:

1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0

Return 4.


    public class Solution {
        // Logic: dp[i][j] is the length of the side of the square with bottom-right corner at cell (i,j)
        // If current element in original matrix is zero, no square possible with bottom-right corner at current element. So Skip
        // Else check if top,left and top-left of current element are all 1's(i.e. a square is formed with bottom-right corner ar current element).
        // Side of square formed is 1 + min(top,left,top-left values).
        public int maximalSquare(char[][] matrix) {
            if(matrix==null || matrix.length==0)
                return 0;
            int rows=matrix.length, cols=matrix[0].length, squareSide=0;
            int[][] dp = new int[rows+1][cols+1];

            for(int i=1;i<=rows;i++) {
                for(int j=1;j<=cols;j++) {
                    if(matrix[i-1][j-1]=='1') {
                        // Find min of top, left and top-left cells of dp and add 1
                        dp[i][j] = Math.min(Math.min(dp[i-1][j-1], dp[i-1][j]), dp[i][j-1]) + 1;
                        squareSide = Math.max(squareSide, dp[i][j]);
                    }
                }
            }

            return squareSide * squareSide;
        }
    }

    // Time complexity: O(mn) where m=no. of rows; n=no. of cols

====================================================
59. Implement trie data structure(Assuming input is only lowercase letters).

    // Checkout a related add/search words in dictionary question(where words can be regexp containing a '.' which signifies any lowercase letter)
    // https://leetcode.com/problems/add-and-search-word-data-structure-design/

    class TrieNode {
        TrieNode[] arr;
        // to check for overlapping strings(eg: door vs do)
        boolean isEnd;

        public TrieNode() {
            arr = new TrieNode[26]; // 26 lowercase letters
            isEnd=false;
        }
    }

    public class Trie {
        private TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        // Inserts a word into the trie.
        public void insert(String word) {
            int index;
            TrieNode p = root;
            for(int i=0;i<word.length();i++) {
               index = word.charAt(i) - 'a';
               if(p.arr[index]==null) {
                   TrieNode temp = new TrieNode();
                   p.arr[index] = temp;
                   p=temp; // Move p forward
               } else {
                   p=p.arr[index];
               }
            }
            p.isEnd=true; // One node beyond the last character in word
        }

        // Returns if the word is in the trie.
        public boolean search(String word) {
            TrieNode p = searchString(word);
            if(p!=null) {
                if(p.isEnd)
                    // ensures that overlapping strings("word"+some_other_chars) are not incorrectly
                    // identified as "word"
                    return true;
            }
            return false;
        }

        // Returns if there is any word in the trie
        // that starts with the given prefix.
        public boolean startsWith(String prefix) {
            TrieNode p = searchString(prefix);
            return (p!=null);
        }

        // Searches for existence of str and returns one node beyond the last character in str
        private TrieNode searchString(String str) {
            int index;
            TrieNode p = root;
            if(str.length()==0)
                return null;

            for(int i=0;i<str.length();i++) {
               index = str.charAt(i) - 'a';
               if(p.arr[index]==null) {
                   return null;
               } else {
                   p=p.arr[index];
               }
            }
            return p;
        }
    }

    // Your Trie object will be instantiated and called as such:
    // Trie trie = new Trie();
    // trie.insert("somestring");
    // trie.search("key");

    // Time complexity: All operations implemented here take O(n) time where n=length fo input word

==========================================

60. Largest number: Given a list of non negative integers, arrange them such that they form the largest number.

For example, given [3, 30, 34, 5, 9], the largest formed number is 9534330.

Note: The result may be very large, so you need to return a string instead of an integer.

    // Note: Solution overrides comparator used during sorting of strings thereby achieveing the goal.

    public String largestNumber(int[] nums) {
        boolean allZero=true;
        String[] numStr = new String[nums.length];
        StringBuilder sb = new StringBuilder();

        // Convert int[] to String[]
        for(int i=0;i<nums.length;i++) {
            numStr[i]=String.valueOf(nums[i]);
            // Additional check to see if all nos. in input is zero.
            if(nums[i]!=0)
                allZero=false;
        }

        if (allZero)
            return "0";

        // Custom comparator for sorting string array
        Comparator<String> comp = new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                String str1 = s1 + s2; // Assuming s1=3, s2=91 => str1=391
                String str2 = s2 + s1; // Assuming s1=3, s2=91 => str2=913
                return str2.compareTo(str1); // reverse the order of comparison since our intention is to get opposite of the lexicographic order of s1 and s2
            }
        };

        Arrays.sort(numStr, comp);

        for(String s : numStr)
            sb.append(s);

        return sb.toString();
    }

    // Time complexity: O(nlogn) where n=length of input array

=====================================

61. Given a 2D matrix, find the sum of the elements inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).

Example:

Given matrix = [
  [3, 0, 1, 4, 2],
  [5, 6, 3, 2, 1],
  [1, 2, 0, 1, 5],
  [4, 1, 0, 1, 7],
  [1, 0, 3, 0, 5]
]

sumRegion(2, 1, 4, 3) -> 8
sumRegion(1, 1, 2, 2) -> 11
sumRegion(1, 2, 2, 4) -> 12

Note:

    You may assume that the matrix does not change.
    There are many calls to sumRegion function.
    You may assume that row1 ≤ row2 and col1 ≤ col2.


    public class NumMatrix {
        int[][] dp;
        public NumMatrix(int[][] matrix) {
            if(matrix==null || matrix.length==0 || matrix[0].length==0)
                return;
            int rows=matrix.length, cols=matrix[0].length;
            dp = new int[1+rows][1+cols]; // dp[i][j] is the sum of all elements contained in rectangle with topleft corner at (0,0) at bottom-right corner at (i,j)

            // Populate dp matrix
            for(int i=1;i<=rows;i++) {
                for(int j=1;j<=cols;j++) {
                    dp[i][j] = dp[i][j-1] + dp[i-1][j] - dp[i-1][j-1] + matrix[i-1][j-1]; // cell (i,j) for dp matrix is equivalent to cell (i-1,j-1) for input matrix
                }
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            // Assuming row1<=rows and col1<=col2
            return dp[1+row2][1+col2] - dp[1+row2][col1] - dp[row1][1+col2] + dp[row1][col1]; // adding the intersection of two areas which were subtracted twice
        }
    }

    // Your NumMatrix object will be instantiated and called as such:
    // NumMatrix numMatrix = new NumMatrix(matrix);
    // numMatrix.sumRegion(0, 1, 2, 3);
    // numMatrix.sumRegion(1, 2, 3, 4);

    // Time complexity: O(1) for sumRegion()


============================================
64. Reverse a singly linked list:

    private ListNode reverse(ListNode head) {
        if(head==null || head.next==null)
            return head;

        ListNode pre=null;
        ListNode cur=head, after;
        while(cur!=null) {
            after=cur.next;
            cur.next=pre;
            pre=cur;
            cur=after;
        }
        return pre;
    }


=======================================

68. Count Complete Tree Nodes

Given a complete binary tree, count the number of nodes.

Definition of a complete binary tree from Wikipedia:
In a complete binary tree, every level, except possibly the last, is completely filled, and all nodes in
the last level are as far left as possible. It can have between 1 and 2^h nodes inclusive at the last level h.


    Explanation:

The height of a tree can be found by just going left. Let a single node tree have height 0. Find the height "h" of the whole tree.
If the whole tree is empty, i.e., has height -1, there are 0 nodes.

Otherwise check whether the height of the right subtree is just one less than that of the whole tree, meaning left and right subtree have the same height.

    If yes, then the last node on the last tree row is in the right subtree and the left subtree is a full tree of height h-1.
        So we take the 2^h-1 nodes of the left subtree plus the 1 root node plus recursively the number of nodes in the right subtree.
    If no, then the last node on the last tree row is in the left subtree and the right subtree is a full tree of height h-2.
        So we take the 2^(h-1)-1 nodes of the right subtree plus the 1 root node plus recursively the number of nodes in the left subtree.

Since I halve the tree in every recursive step, I have O(log(n)) steps. Finding a height costs O(log(n)). So overall O(log(n)^2).


    class Solution {
        private int height(TreeNode root) {
            return root == null ? -1 : 1 + height(root.left);
        }
        public int countNodes(TreeNode root) {
            int h = height(root);
            return h < 0 ? 0 :
                   height(root.right) == h-1 ? (1 << h) + countNodes(root.right)
                                             : (1 << h-1) + countNodes(root.left);
        }
    }



    Alternative solution to check if the tree is a complete tree:

    boolean isCompleteUtil(Node root, int index, int number_nodes)
    {
        // An empty tree is complete
        if(root == null)
            return true;

        // If index assigned to current node is more than
        // number of nodes in tree, then tree is not complete
        if(index >= number_nodes)
            return false;

        // Recur for left and right subtrees
        return isCompleteUtil(root.left, 2*index+1, number_nodes) &&
            isCompleteUtil(root.right, 2*index+2, number_nodes);

    }

===================================================

69. Reconstruct Itinerary:
    Given a list of airline tickets represented by pairs of departure and arrival airports [from, to], reconstruct the itinerary in order.
    All of the tickets belong to a man who departs from JFK. Thus, the itinerary must begin with JFK.

Note:

    If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string.
    For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
    All airports are represented by three capital letters (IATA code).
    You may assume all tickets form at least one valid itinerary.

Example 1:
tickets = [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
Return ["JFK", "MUC", "LHR", "SFO", "SJC"].

Example 2:
tickets = [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
Return ["JFK","ATL","JFK","SFO","ATL","SFO"].
Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"]. But it is larger in lexical order.

Example 3:
tickets = [["JFK","KUL"],["JFK","NRT"],["NRT","JFK"]]
Return ["JFK","NRT","JFK","KUL"]



    public List<String> findItinerary(String[][] tickets) {
        // Eulerian path for a directed graph??

        // PriorityQueue is implemented as a minHeap which means Type's(String here) default comparator
        // will ensure that PriorityQueue.poll() will return destinations in sorted(lexicographic) order
        Map<String, PriorityQueue<String>> targets = new HashMap<>();
        for (String[] ticket : tickets)
            targets.computeIfAbsent(ticket[0], k -> new PriorityQueue()).add(ticket[1]);
        List<String> route = new LinkedList();
        Stack<String> stack = new Stack<>();
        stack.push("JFK");
        while (!stack.empty()) {
            // Continue until all edges have been visited once
            while (targets.containsKey(stack.peek()) && !targets.get(stack.peek()).isEmpty()) {
                // Follow a path and push all nodes to stack until we hit a deadend
                String s = targets.get(stack.peek()).poll();
                stack.push(s);
            }
            // Deadend hit. Add last node(deadend node) to result
            // Backtrack by one node(i.e. go to node before deadend node) and follow a different path
            // until the next deadend is hit. Rinse and repeat
            route.add(0, stack.pop());
        }
        return route;
    }

    // Time complexity: O(V+E) where V=no. of vertices; E=no. of edges

==============================================

70. Word Break:  Given a string s and a dictionary of words dict, determine if s can be segmented into a space-separated sequence of one or more dictionary words.

For example, given
s = "leetcode",
dict = ["leet", "code"].

Return true because "leetcode" can be segmented as "leet code".


    public boolean wordBreak(String s, Set<String> wordDict) {
        // dp[i] denotes whether substring(0,i) can be broken using input dict
        boolean[] dp = new boolean[s.length()+1];
        dp[0]=true;

        for(int i=1;i<=s.length();i++) {
            for(int j=0;j<i;j++) {
                if(dp[j] && wordDict.contains(s.substring(j,i))) {
                    // if dp[j] is true => substring(0,j) can be broken down using input dict
                    // Now if substring(j,i) is contained in input dict, this implies substring(0,i) can be broken down too
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[s.length()];
    }


==================

72. Given a 2D array of friends containing Y or N indicating whether a person is a friend of another person or not.
  Find out how many distinct friend circles exist. Note that a person is his own friend.

  // Assuming char[][] friends = {"YYNN".toCharArray(), "YYYN".toCharArray(), "NYYN".toCharArray(), "NNNY".toCharArray()};
  // numCircles = 2 i.e. circle1={0,1,2} and circle2={3}

    public static int getFriendCircles(char[][] friends) {

        if (friends == null || friends.length < 1)
            return 0;

        int noOfCircles = 0;

        boolean visited[] = new boolean[friends.length];

        for (int i = 0; i < visited.length; i++)
            visited[i] = false;

        for (int i = 0; i < friends.length; i++) {
            if (!visited[i]) {
                noOfCircles++;
                visited[i] = true;
                findFriends(friends, visited, i);
            }
        }

        return noOfCircles;
    }

    // This function is just used to update visited[]
    public static void findFriends(char[][] friends, boolean[] visited, int id) {

        for (int i = 0; i < friends.length; i++) {
            if (!visited[i] && 'Y' == friends[id][i]) {
                visited[i] = true;
                findFriends(friends, visited, i);
            }
        }
    }

    // Worst case time complexity: O(N^2) where N=no. of friends

======================================

73. Design an LRU Cache.

    // The idea is to store the cache entries in the form of a doubly linked list.
    // so that the LRU order is maintained inherently by the linked list.

    // Doubly linked list cache node entry
    class CacheNode {
        int value;
        CacheNode prev; // points to prev cache node
        CacheNode next;  // points to the next cache node
    }


    public class LRUCache {
        Map<Integer,CacheNode> cache = new HashMap<Integer, CacheNode>(); // key to cahce entry mapping
        int curCount, maxSize;
        CacheNode front, rear;

        public LRUCache(int size) {
            curCount = 0;
            maxSize = size;
            front=rear=null;
        }

        // Moves an existing node to front of linked list
        private void moveToFront(CacheNode node);

        // Delete a node from end of linked list(LRU policy)
        private void removeFromRear();

        // Adds a new node to front of linked list
        private void addToFront(CacheNode node);

        // Lookup cache using a key value
        public int get(int key) {
            CacheNode node = cache.get(key);
            if(node == null) {
                throw new Exception("Node does not exist");
            }
            // Make it most recently used node
            // So move it to front of linked list
            moveToFront(node);
            return node.value;
        }

        // Adds an entry into the cache
        public void put(int key, int value) {
            // Check if this node exists already
            CacheNode node = cache.get(key);
            if(node!=null) {
                node.value = value;
                moveToFront(node);
            } else {
                // Create a new node in linked list at the head
                // Delete from rear end if curCount>maxSize
                CacheNode n = new CacheNode();
                n.value = value;
                cache.put(key, n); // Put entry in hashmap
                addToFront(n);
                curCount++;

                if(curCount>maxSize) {
                    removeFromRear();
                    curCount--;
                }
            }
        }
    }

===============================================

74. Serialize and Deserialize a binary tree:
    Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization
    algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be
    deserialized to the original tree structure.

For example, you may serialize the following tree

    1
   / \
  2   3
     / \
    4   5

as "[1,2,3,null,null,4,5]", just the same as how LeetCode OJ serializes a binary tree. You do not necessarily need to follow
this format, so please be creative and come up with different approaches yourself.

Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.

    // It doesn't work with index being an int variable. Why do we need to declare it as a single element array
    // With former, the output is [1,2] whereas the expected output is [1,2,3,null,null,4,5]

    public class Codec {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            StringBuilder sb = new StringBuilder();
            serializeHelper(root, sb);
            return sb.toString();
        }

        // Pre-order traversal is encoded in the form of string
        private void serializeHelper(TreeNode root, StringBuilder sb) {
            if(root==null) {
                sb.append("N");
                sb.append(",");
                return;
            }
            sb.append(root.val);
            sb.append(",");
            serializeHelper(root.left, sb);
            serializeHelper(root.right, sb);
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            String[] dataParts = data.split(",");
            int[] index = new int[]{0}; // an integer on JVM's heap acting like a global variable to retain value across recursive calls of deserializeHelper()
            return deserializeHelper(dataParts, index);
        }

        private TreeNode deserializeHelper(String[] dataParts,  int[] index) {
            if(index[0]==dataParts.length || dataParts[index[0]].equals("N"))
                return null;

            TreeNode root = new TreeNode(Integer.parseInt(dataParts[index[0]]));
            index[0]++;
            root.left = deserializeHelper(dataParts, index);
            index[0]++;
            root.right = deserializeHelper(dataParts, index);
            return root;
        }
    }

    // Your Codec object will be instantiated and called as such:
    // Codec codec = new Codec();
    // codec.deserialize(codec.serialize(root));

    // Time complexity of both serialize() and deserialize() is O(n) where n=no. of nodes in tree

==========================================

75. Djikstra shortest path first algorithm:

    // For explanation see the geeksforgeeks page:
    http://www.geeksforgeeks.org/greedy-algorithms-set-6-dijkstras-shortest-path-algorithm/

    // Funtion that implements Dijkstra's single source shortest path
    // algorithm for a graph represented using adjacency matrix
    // representation
    void dijkstra(int graph[][], int src)
    {
        int dist[] = new int[V]; // The output array. dist[i] will hold
                                 // the shortest distance from src to i

        // sptSet[i] will be true if shortest distance from src to i is finalized
        Boolean sptSet[] = new Boolean[V];

        // Initialize all distances as INFINITE and stpSet[] as false
        for (int i = 0; i < V; i++)
        {
            dist[i] = Integer.MAX_VALUE;
            sptSet[i] = false;
        }

        // Distance of source vertex from itself is always 0
        dist[src] = 0;

        // Find shortest path for all vertices
        for (int count = 0; count < V-1; count++) {
            // Pick the minimum distance vertex from the set of vertices
            // not yet processed. u is always equal to src in first
            // iteration.
            int u = minDistance(dist, sptSet);

            // Mark the picked vertex as processed
            sptSet[u] = true;

            // Update dist value of the adjacent vertices of the
            // picked vertex.
            for (int v = 0; v < V; v++)

                // Update dist[v] only if is not in sptSet, there is an
                // edge from u to v, and total weight of path from src to
                // v through u is smaller than current value of dist[v]
                if (!sptSet[v] && graph[u][v]!=0 &&
                        dist[u] != Integer.MAX_VALUE &&
                        dist[u]+graph[u][v] < dist[v])
                    dist[v] = dist[u] + graph[u][v];
        }

        // print the constructed distance array
        printSolution(dist, V);
    }

    // Time complexity: O(V^2) but using a priority queue(to replace outer for loop and minDistance() fn) it can be brought down to O(E*logV)


==================================================

78. All O(1) Data Structure:

Implement a data structure supporting the following operations:

    Inc(Key) - Inserts a new key with value 1. Or increments an existing key by 1. Key is guaranteed to be a non-empty string.
    Dec(Key) - If Key value is 1, remove it from the data structure. Otherwise decrements an existing key by 1.
               If the key does not exist, this function does nothing. Key is guaranteed to be a non-empty string.
    GetMaxKey() - Returns one of the keys with maximal value. If no element exists, return an empty string "".
    GetMinKey() - Returns one of the keys with minimal value. If no element exists, return an empty string "".

Challenge: Perform all these in O(1) time complexity.


    // Note that the same Java file can't have >1 public class. So, keep other classes without the keyword "public"
    // By default, the class visibility is package private, i.e. only visible for classes in the same package.
    class Node {
        Node prev;
        Node next;
        // Stores the count of strings in strSet
        int count;
        // All the keys(strings) that have the same count stored in "count"
        Set<String> strSet;

        Node(int count, String key) {
            this.count=count;
            this.strSet = new LinkedHashSet<String>(); // Use LinkedHashSet instead of HashSet 'coz avg. time complexity of remove() is O(1)
            strSet.add(key);
        }

        Node() {} // If a parametrized contructor is defined in a class, then JVM does not provide a default no arg constuctor.
    }

    class NodeList {
        Node first; // dummy node
        Node last; // dummy node
        int listLen;

        NodeList() {
            listLen=0;
            first = new Node();
            first.count=-1; // The node after the first(dummy) node will have its count>-1
            last = new Node();
            last.count=Integer.MAX_VALUE; // The node before the last(dummy) node will have its count<MAX_VALUE
            first.next = last;
            last.prev = first;
        }

        // Inserts "item" node before "nextNode" in NodeList
        void insert(Node item, Node nextNode) {
            Node pre = nextNode.prev;
            item.next=pre.next;
            pre.next=item;
            nextNode.prev=item;
            item.prev=pre;
            listLen++;
        }

        // Removes "item" node from NodeList
        void remove(Node item) {
            Node pre=item.prev;
            Node next=item.next;
            pre.next=item.next;
            next.prev=pre;
            listLen--;
        }
    }

    public class AllOne {
        // To maintain the ordering so that getMaxKey() and getMinKey() is O(1)
        NodeList list;
        // map is used so that we can index into the list in O(1) time complexity
        Map<String, Node> strNodeMap;
        public AllOne() {
            list = new NodeList();
            strNodeMap = new HashMap<>();
        }

        /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
        public void inc(String key) {
            Node keyNode, nextToKeyNode, nextToFirstNode;
            int keyCount;
            Set<String> keySet;
            if(strNodeMap.containsKey(key)) {
                keyNode = strNodeMap.get(key);
                keyCount = keyNode.count;
                keySet = keyNode.strSet;

                // Remove this key from this node's set
                keySet.remove(key);
                if(keySet.size()==0)
                    list.remove(keyNode); // delete the node if its set becomes empty

                 // Add this key to a new node's set based on count value
                nextToKeyNode = keyNode.next;
                if(keyCount+1 < nextToKeyNode.count) {
                    // Add a new node in between keyNode and nextToKeyNode
                    Node newNode = new Node(keyCount+1, key);
                    list.insert(newNode, nextToKeyNode);
                    strNodeMap.put(key, newNode);
                } else {
                    // Insert the key in the set in nextToKeyNode
                    nextToKeyNode.strSet.add(key);
                    strNodeMap.put(key, nextToKeyNode);
                }
            } else {
                nextToFirstNode = list.first.next;
                if(1 != nextToFirstNode.count) {
                    // Add a new node in between firstNode and nextToFirstNode
                    Node newNode = new Node(1, key);
                    list.insert(newNode, nextToFirstNode);
                    strNodeMap.put(key, newNode);
                } else {
                    // Insert the key in the set in nextToFirstNode
                    nextToFirstNode.strSet.add(key);
                    strNodeMap.put(key, nextToFirstNode);
                }
            }
        }

        /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
        public void dec(String key) {
            Node keyNode, prevToKeyNode;
            int keyCount;
            Set<String> keySet;
            if(strNodeMap.containsKey(key)) {
                keyNode = strNodeMap.get(key);
                keyCount = keyNode.count;
                prevToKeyNode = keyNode.prev;

                // Remove this key from this node's set
                keySet = keyNode.strSet;
                keySet.remove(key);
                if(keySet.size()==0)
                    list.remove(keyNode); // delete the node if its set becomes empty

                 // Add this key to a new node's set based on count value
                if(keyCount-1 == prevToKeyNode.count){
                    // Insert the key in the set in prevToKeyNode
                    prevToKeyNode.strSet.add(key);
                    strNodeMap.put(key, prevToKeyNode);
                } else if(keyCount>1) {
                    // Add a new node in between keyNode and prevToKeyNode
                    Node newNode = new Node(keyCount-1, key);
                    list.insert(newNode, prevToKeyNode.next); // don't use the second arg as "keyNode" bcoz it might have got deleted from the list if its keySet became empty after removing the key.
                    strNodeMap.put(key, newNode);
                } else {
                    strNodeMap.remove(key); // key value was 1. So decrementing it by 1 would evict the key
                }
            }
        }

        /** Returns one of the keys with maximal value. */
        public String getMaxKey() {
            if(list.listLen!=0) {
                Node maxNode = list.last.prev;
                //System.out.println("maxCount="+maxNode.count);
                //System.out.println("listLen="+list.listLen);
                return maxNode.strSet.iterator().next();
            }
            return "";
        }

        /** Returns one of the keys with Minimal value. */
        public String getMinKey() {
            if(list.listLen!=0) {
                Node minNode = list.first.next;
                //System.out.println("minCount="+minNode.count);
                return minNode.strSet.iterator().next();
            }
            return "";
        }
    }

===================================

79. Delete Node in a BST:

Given a root node reference of a BST and a key, delete the node with the given key in the BST. Return the root node reference (possibly updated) of the BST.

Basically, the deletion can be divided into two stages:

    Search for a node to remove.
    If the node is found, delete the node.

Example:

root = [5,3,6,2,4,null,7]
key = 3

    5
   / \
  3   6
 / \   \
2   4   7

Given key to delete is 3. So we find the node with value 3 and delete it.

One valid answer is [5,4,6,2,null,null,7], shown in the following BST.

    5
   / \
  4   6
 /     \
2       7

Another valid answer is [5,2,6,null,4,null,7].

    5
   / \
  2   6
   \   \
    4   7

    // This solution implements the first valid answer(described above)
    // So always replaces the key node with its inorder successor if
    // both left and right child for the key node exist

    public class Solution {
        public TreeNode deleteNode(TreeNode root, int key) {
            if(root==null)
                return null;
            if(key < root.val) {
                root.left = deleteNode(root.left, key);
            } else if (key > root.val) {
                root.right = deleteNode(root.right, key);
            } else {
                // found node to be deleted
                // Case 1: no left child or leaf node
                if(root.left==null)
                    return root.right;
                // Case 2: no right child
                else if(root.right==null)
                    return root.left;
                // Case 3: both left and right child exist
                // Get the smallest successor of key Node
                TreeNode minNode = findMin(root);
                // Swap key Node with minNode
                root.val = minNode.val;
                // Delete the minNode
                root.right = deleteNode(root.right, minNode.val);
            }
            return root;
        }

        private TreeNode findMin(TreeNode root) {
            TreeNode res = root.right;
            while(res.left!=null) {
                res = res.left;
            }
            return res;
        }
    }

    // Time complexity: O(height of tree).

===========================================

80. Check whether a graph is bipartite or not.

A Bipartite Graph is a graph whose vertices can be divided into two independent
sets, U and V such that every edge (u, v) either connects a vertex from U to V or a vertex from V to U. In other words, for
every edge (u, v), either u belongs to U and v to V, or u belongs to V and v to U. We can also say that there is no edge that
connects vertices of same set.

    /**
    Following is a simple algorithm to find out whether a given graph is Birpartite or not using Breadth First Search (BFS).
1. Assign RED color to the source vertex (putting into set U).
2. Color all the neighbors with BLUE color (putting into set V).
3. Color all neighbor’s neighbor with RED color (putting into set U).
4. This way, assign color to all vertices such that it satisfies all the constraints of m way coloring problem where m = 2.
5. While assigning colors, if we find a neighbor which is colored with same color as current vertex, then the graph cannot be colored with 2 vertices (or graph is not Bipartite)

    **/


    // Input graph is in adj matix form. So, time complexity is O(V^2).
    // FOr O(V+E) time complexity, use input as adj list format.
    // This function returns true if graph G[V][V] is Bipartite, else false
    boolean isBipartite(int G[][],int src)
    {
        // Create a color array to store colors assigned to all veritces.
        // Vertex number is used as index in this array. The value '-1'
        // of  colorArr[i] is used to indicate that no color is assigned
        // to vertex 'i'.  The value 1 is used to indicate first color
        // is assigned and value 0 indicates second color is assigned.
        int colorArr[] = new int[V];
        for (int i=0; i<V; ++i)
            colorArr[i] = -1;

        // Assign first color to source
        colorArr[src] = 1;

        // Create a queue (FIFO) of vertex numbers and enqueue
        // source vertex for BFS traversal
        LinkedList<Integer>q = new LinkedList<Integer>();
        q.add(src);

        // Run while there are vertices in queue (Similar to BFS)
        while (q.size() != 0)
        {

            // Dequeue a vertex from queue
            int u = q.poll();

            // Find all non-colored adjacent vertices
            for (int v=0; v<V; ++v)
            {
                // An edge from u to v exists and destination v is
                // not colored
                if (G[u][v]==1 && colorArr[v]==-1)
                {
                    // Assign alternate color to this adjacent v of u
                    colorArr[v] = 1-colorArr[u];
                    q.add(v);
                }

                // An edge from u to v exists and destination v is
                // colored with same color as u
                else if (G[u][v]==1 && colorArr[v]==colorArr[u])
                    return false;
            }
        }
        // If we reach here, then all adjacent vertices can
        //  be colored with alternate color
        return true;
    }

==============================================

83. Iterative solution of postorder traversal(LEFT->RIGHT->ROOT) of binary tree:

    // Algo is a little tricky because we store left->right in stack
    // and then pop them in right->left order and add them to head of list
    // thereby making it root->left->right

    public List<Integer> postorderTraversal(TreeNode root) {
        LinkedList<Integer> res = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();

        if(root==null)
            return res;

        stack.push(root);
        while(!stack.isEmpty()) {
            TreeNode node = stack.pop();
            res.addFirst(node.val); // This is the trick

            // Since left is pushed to stack before right
            // and we add the popped item to the head of the list,
            // we get left->right->root order in list
            if(node.left!=null)
                stack.push(node.left);

            if(node.right!=null)
                stack.push(node.right);
        }

        return res;
    }

==================================================

85. Lowest Common Ancestor of a Binary Tree:

     Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.

According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes v and w as the lowest
node in T that has both v and w as descendants (where we allow a node to be a descendant of itself).”

        _______3______
       /              \
    ___5__          ___1__
   /      \        /      \
   6      _2       0       8
         /  \
         7   4

For example, the lowest common ancestor (LCA) of nodes 5 and 1 is 3. Another example is LCA of nodes 5 and 4 is 5, since a
node can be a descendant of itself according to the LCA definition.


    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> listP = new ArrayList<TreeNode>();
        List<TreeNode> listQ = new ArrayList<TreeNode>();
        int i;

        // null tree input
        if(root==null)
            return null;

        // if both inputs are same
        if(p==q)
            return p;

        // either one or both p and q not found in the tree
        if(!isFound(root, p, listP) || !isFound(root, q, listQ))
            return null;

        // both nodes found in the tree. Get the first unequal node in the lists
        for(i=0; i<listP.size() && i<listQ.size(); i++) {
            if(listP.get(i).val != listQ.get(i).val)
                break;
        }

        return listP.get(i-1);
    }

    private boolean isFound(TreeNode root, TreeNode x, List<TreeNode> list) {
        if(root==null)
            return false;

        list.add(root);
        if(root==x)
            return true;

        if(isFound(root.left, x, list) || isFound(root.right, x, list))
            return true;

        list.remove(root);
        return false;
    }

    // Time complexity(worst case): O(n) where n=no. of nodes in tree

============================================

86**. Recover Binary Search Tree:
     Two elements of a binary search tree (BST) are swapped by mistake.

    Recover the tree without changing its structure.
    Note:
    A solution using O(n) space is pretty straight forward. Could you devise a constant space solution?


    public class Solution {
        TreeNode firstNode = null, secondNode=null;
        TreeNode prevNode = new TreeNode(Integer.MIN_VALUE);

        public void recoverTree(TreeNode root) {
            if(root==null)
                return;

            traverseInorder(root);

            // swap firstNode and secondNode
            int temp=firstNode.val;
            firstNode.val=secondNode.val;
            secondNode.val=temp;
        }

        private void traverseInorder(TreeNode root) {
            if(root==null)
                return;

            traverseInorder(root.left);

            // Consider example: 6,3,4,5,2. Out of order nodes is 6 and 2.
            if(firstNode==null && root.val <= prevNode.val)
                firstNode = prevNode; // for the first time, previous node is the one out of order

            if(firstNode!=null && root.val<prevNode.val)
                secondNode = root; // for the second time, current node is the one out of order

            prevNode=root; // Assign current node to prevNode for next iteration

            traverseInorder(root.right);
        }
    }

=======================================

87. Design a circular buffer of a given size. Design two of its interfaces:

    write(input, len) -> Attempts to write the input of length "len" to buffer. Returns the actual length of chars written.
    read(dest, len) -> Attempts to read len chars from buffer and copies it to dest. Returns the actual length of chars read.

    Eg: If buffer is of length 5.

    b = _ _ _ _ _

    write("abcblah", 7) -> b = a b c b l, Returns 5
    read(dest, 2)       -> b = _ _ c b l
                           dest = a b
                           Return 2
    write("1234", 4)    -> b = 1 2 c b l, Returns 2
    read(dest, 3)       -> b = 1 2 _ _ _
                           dest = c b l
                           Return 3
    write("xyzw", 4)    -> b = 1 2 x y z, Returns 3
    write('foo', 3)     -> b = 1 2 x y z, Returns 0
    read(dest, 5)       -> b = _ _ _ _ _
                            dest = 1 2 x y z
                            Return 5


    public class CircularBuffer {
        char[] buffer;
        int front; // points to the char to be read
        int rear; // points to the last char inserted
        int size;

        public CircularBuffer(int size) {
            this.size = size;
            buffer = new char[size];
            front=rear=-1;
        }

        public int write(String text, int length) {
            int countWritten=0;

            while(countWritten<length) {
                // Check for overflow condition
                if(rear+1==front || (rear==size-1 && front==0))
                    return countWritten;

                // First increment rear, then insert
                rear = (rear + 1) % size;
                if(front==-1)
                    front=0;
                buffer[rear] = text.charAt(countWritten);
                countWritten++;
            }
            return countWritten;
        }

        // Reads the chars from buffer and stores it in dest
        // Returns the no. of chars that was actually read
        public int read(char[] dest, int length) {
            int countRead =0;

            while(countRead<length) {
                // Check for underflow
                if(front==-1 && rear==-1)
                    return countRead;

                // Read first, then increment front
                dest[countRead] = buffer[front];
                countRead++;
                // Check if this was last char in buffer
                if(front==rear) {
                    front = rear = -1;
                    break;
                }
                front = (front+1) % size;
            }

            return countRead;
        }
    }



==========================================

89. Find all possible interpretations of an array of digits

Consider a coding system for alphabets to integers where ‘a’ is represented as 1, ‘b’ as 2, .. ‘z’ as 26. Given an array of digits
(1 to 9) as input, write a function that prints all valid interpretations of input array.

Examples

Input: {1, 1}
Output: ("aa", "k")
[2 interpretations: aa(1, 1), k(11)]

Input: {1, 2, 1}
Output: ("aba", "au", "la")
[3 interpretations: aba(1,2,1), au(1,21), la(12,1)]

Input: {9, 1, 8}
Output: {"iah", "ir"}
[2 interpretations: iah(9,1,8), ir(9,18)]



Very nice explanation about visualizing the problem in the form of a tree.
http://www.geeksforgeeks.org/find-all-possible-interpretations/

============================================

90. Kth Smallest Element in a Sorted Matrix:

    Given a n x n matrix where each of the rows and columns are sorted in ascending order, find the kth smallest element in the matrix.

Note that it is the kth smallest element in the sorted order, not the kth distinct element.

Example:

matrix = [
   [ 1,  5,  9],
   [10, 11, 13],
   [12, 13, 15]
],
k = 8,

return 13.
Note:
You may assume k is always valid, 1 ≤ k ≤ n^2.



    public class Solution {
        class Cell implements Comparable<Cell> {
            int row, col, val; // Stores row num, col num and value of a matrix cell

            public Cell(){}

            public Cell(int r, int c, int v) {
                row = r;
                col = c;
                val = v;
            }

            @Override
            public int compareTo(Cell that) {
                return (this.val - that.val);
            }
        }


        public int kthSmallest(int[][] matrix, int k) {
            int numRows = matrix.length;
            Cell c = new Cell();
            PriorityQueue<Cell> pq = new PriorityQueue<>();

            // Add all numbers in first column to pq
            for(int i=0; i<numRows; i++)
                pq.add(new Cell(i, 0, matrix[i][0]));

            for(int j=0;j<k;j++) {
                c = pq.poll(); // Pick the lowest value
                if(c.col==numRows-1)
                    continue; // exhausted all elements in that row
                pq.add(new Cell(c.row, c.col+1, matrix[c.row][c.col+1])); // add the value in the next col for this particular row
            }
            return c.val;
        }
    }


====================================================

92. Implement a method to count the number of unival subtrees. A unival subtree is a subtree within a tree with root node and all its
    children nodes(upto the leaf node) have the same value. NOte that all leaf nodes are considered unival subtrees.


    Eg:
            5
           / \
          5   5
            /  \
           5    4

    The above binary tree has five unival subtrees namely:
    1. Three leaf nodes
    2. The right subtree with root at 5(at level 1) and its two child leaf nodes as 5
    3. Entire binary tree rooted at 5(at level 0)



    public class Solution {
        private int count = 0;

        public int countUnivalSubtrees(TreeNode root) {
            countUnivalSubtreesHelper(root);
            return count;
        }

        private boolean countUnivalSubtreesHelper(TreeNode root) {
            if(root==null)
                return true;

            boolean isLeftUnival = countUnivalSubtreesHelper(root.left);
            boolean isRightUnival = countUnivalSubtreesHelper(root.right);

            if(isLeftUnival && isRightUnival) {
                if(root.right!=null && root.val != root.right.val)
                    return false;

                if(root.left!=null && root.val != root.left.val)
                    return false;

                count++;
                return true;
            }

            return false;
        }
    }

    Time complexity: O(n) where n = no. of nodes of the input tree

============================================

Some more questions:
1. If there are objects(in a HashSet) whose hashCode is made up of all the attributes of that object. Suppose some attributes of an object
   changes after it has been inserted in the HashSet. What happens? Eg:

   class Person {
        String firstName;
        String lastName;
        Date DOB;
   }

Ans: Its hashCode is not recomputed automatically in Java. The setter method(used to change the attributes) needs to call the hashCode()
     method explicitly so that the new hashCode is recomputed and the updated object is rehashed into a different hash bucket.

     Followup: Is it possible to change an object without recomputing its hashCode(assuming that setter methods for each attribute calls
        hashCode() method to recalculate the hashcode.)
     Ans: Yes its possible. Eg:
            Person p1 = new Person(....);
            p1.DOB.setMonth("Feb"); // since this doesn't call the setter of Person class, hashcode is not recomputed.

    Followup: Is it possible to change one of the String type attributes and does hashcode of object change then?
    Ans: String is immutable data type. So, changing String attribute will not change the hashcode of the object.

    Followup: Is it possible to call a setter method to change an attribute of an object with its hashcode still being the original one?
    Ans: Yes. Call getter method before calling setter method. Modify getter method to return a copy of the original object.
         And then pass this copy to setter method to change the attribute. Thus the original object attribute remains the same
         and so does its hashcode.


2. Design a ticket system which contain Person type objects, where each Person has firstName, lastName and DOB.
    Functionalities to be supported are - insert(), delete() and findRandomPerson(). Perform these in the most optimal manner.

3. Given the root to a binary tree, design an algo to return the first seen local minimum in the tree. A local minimum is a node which
   is smaller than its parent node(if there is any) and both its children(if there are any).

   Discussion involved proving(by sorta mathematical induction) that a binary tree will always have a local minimum.

   Ans: Trivial solution - O(n) where n=no. of nodes in the tree. Iterate over all nodes to check local minimum.
        Improvement: Starting from the root, traverse down only on the path where the child node is smaller in value than the parent root node.


        Eg:     70
              /    \
            77      60
          /   \    /   \
         1    8    9    14
        / \  / \  / \   / \
       3   4 5 6  2 7  15 13

       Starting from 70, traverse through 60, 9 and 2. At every node, we are already guaranteed that it is smaller than its parent node.
       So, check whether it is smaller than its children or not. If yes, then we found our local minima.
       If not, continue traversing on the path which has the smaller/smallest child until we hit a leaf node.
       Eventually the leaf node(here 2) will be the local minima.

       Complexity: O(log n).


4. Given N sorted lists, design a solution to return a final sorted list containing all the numbers from the input list.

Ans: Use PriorityQueue to store k elements if there are k sorted lists in input. If there are a total of N elements in input, then
     time complexity of the solution is O(N logk)


5. Given a list of pointers as input where each pointer points to one of the nodes in a doubly linked list, return the no. of connected
   components. A connected component is a list of pointers which are pointing to nodes adjacent to each other. Note that a single node pointer
   pointing to a node which is not adjacent to any other node is still considered a connected component. Note that the doubly linked list
   can be almost a million nodes long.

   Ans: Similar to no. of friend circles given a group of friends and their friendships. Graph problem.

6. Given a list of Runnable tasks which take very less time to execute(~1msec) and a frequency after which each of those tasks need to be
   rescheduled, design a single threaded solution which takes these tasks as they become available to execute and executes them.

   Ans: Use a PriorityQueue<RunTask> to hold the tasks as they are added to the system. The comparator for PriorityQueue should place tasks
        in the increasing order of wait times i.e. frequencyMillis

   class RunTask {
        Runnable task;
        long frequencyMillis;
        long nextSchedTime;
   }


   public void schedule() {
        // adds task to the PriorityQueue
        // with next scheduled time = current time + frequencyMillis
   }

   public void run() {
        // dequeue a RunTask from queue
        // check if it is scheduled to run now
        // if yes, run it and call reschedule
        // if not, then add it back to end of priorityQueue w/o updating the nextSchedTime
   }


7. Design a garage system with the following specifications:

    Type of cars it can park:
        Minivan
        SUV
        Sedan

    Types of parking spots available:
        Compact
        Regular
        High Clearance


    There are a certain no. of spots available of each type. And the cars can fit in the following way:
        Minivan - can fit in all three types of spots
        SUV - can fit only in High Clearance spots
        Sedan - can fit in both High Clearance and Regular spots.

    Note that a valet guy knows the location of the parking spots, so we dont have to solve the geography problem of the garage.


    Public APIs expected out of the system are:
    1. Given a type of car, get an available parking spot ID so that the valet guy can park the car in that spot.
        Note that every spot has some form of String ID which need not be sequential.
    2. After the person is done using the garage, he shows the valet guy a ticket which contains his car ID. Given this car ID,
        determine the spot where this car is parked so that the valet guy can bring the car back.


    Improvements needed: Move the car to the most optimal spot(eg: a minivan should move to the compact location) if one becomes available
                         even after the car is parked.


===================================

A few more to think about:

++ Elevator design question - 50 floors, 4 elevators
++ Solve is-X-prime at high-scale
++ Given an array of integers, how would you remove all duplicates in linear time, i.e. O(n)?
    - Easy to do if its sorted array
    - Algo exists for O(n) if array of size>n contains numbers from [0,n] -> http://stackoverflow.com/questions/5414854/remove-duplicates-from-array-in-linear-time-and-without-extra-arrays




