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


2. Move zeroes to the end of the array:

public class Solution {
    public void moveZeroes(int[] nums) {
        int holeIndex=0, numIndex=0, temp, numLength = nums.length;
        while (numIndex < numLength) {
            holeIndex = getHoleIndex(nums, holeIndex);
            numIndex = getNumIndex(nums, numIndex);

            // swap hole with number and increment hole index by 1
            if (holeIndex < numIndex && numIndex < numLength) {
                temp = nums[holeIndex];
                nums[holeIndex] = nums[numIndex];
                nums[numIndex] = temp;
                holeIndex++;
            }
            // increment numIndex always by 1
            numIndex++;
        }
    }

    private int getHoleIndex(int[] nums, int holeIndex) {
        while(holeIndex < nums.length && nums[holeIndex] != 0) {
            holeIndex++;
        }
        return holeIndex;
    }

    private int getNumIndex(int[] nums, int numIndex) {
        while(numIndex < nums.length && nums[numIndex] == 0) {
            numIndex++;
        }
        return numIndex;
    }
}

/*
[]nums = {1,0,0,3,0,4,5,0} -> {1,3,4,5,0,0,0,0}

*/

// Time complexity: O(n) where n=length of nums


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

7. Implement queue using stacks:

class MyQueue {
    Stack<Integer> inStack = new Stack<Integer>();
    Stack<Integer> outStack = new Stack<Integer>();
    // Push element x to the back of queue.
    public void push(int x) {
        inStack.push(x);
    }

    // Removes the element from front of queue.
    public void pop() {
        int element;
        if (!outStack.empty())
            outStack.pop();
        else {
            while(!inStack.empty()) {
                element = inStack.pop();
                outStack.push(element);
            }
            outStack.pop();
        }
    }

    // Get the front element.
    public int peek() {
        int element;
        if (!outStack.empty())
            return outStack.peek();
        else {
            while(!inStack.empty()) {
                element = inStack.pop();
                outStack.push(element);
            }
            return outStack.peek();
        }
    }

    // Return whether the queue is empty.
    public boolean empty() {
        return (outStack.empty() && inStack.empty());
    }
}

// Time complexities:
// Push: O(1)
// Pop/Peek: O(k) where k=size of inStack. Worst case is O(n) where n= no. of elements of queue
// empty: O(1)

==========================================
8. Given an array of integers, return indices of the two numbers such that they add up to a specific target.

You may assume that each input would have exactly one solution.


public int[] twoSum(int[] nums, int target) {
    Map<Integer, Integer> map = new HashMap<>(); // Stores number and its corresponding index
    for (int i = 0; i < nums.length; i++) {
        int complement = target - nums[i];
        if (map.containsKey(complement)) {
            return new int[] { map.get(complement), i };
        }
        map.put(nums[i], i);
    }
    throw new IllegalArgumentException("No two sum solution");
}

// Time complexity: O(n) where n is length of nums


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
10. Longest common prefix in array of strings:
https://leetcode.com/articles/longest-common-prefix/

// Algo: Horizontal scanning. Idea is LCP(S1, ....., Sn) = LCP(LCP(....LCP(S1, S2),S3)...Sn)

    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) return "";
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            while (strs[i].indexOf(prefix) != 0) {
                // continue until prefix string doesn't appear in current string
                prefix = prefix.substring(0, prefix.length() - 1); // Remove last char from prefix string
                if (prefix.isEmpty()) return "";
            }
        }
        return prefix;
    }

    // Time complexity: Worst case O(S) where S is sum of all chars in all strings.

=================================
11. Palindrome linked list -> O(n) time complexity and O(1) space complexity solution.

Algo:
    1. Keep slow and fast ptrs.
    2. Move slowptr by 1 and fast by 2 until (fast.next!=null && fast.next.next!=null)
    3. Reverse linked list starting from node after the slowptr node. i.e. now the second half of
        linked list should be reverse.
    4. Keep two ptrs(p1=head of original list, p2=head of reverse list). Iterate and compare for equality of elements.
    5. Return false if any comparison fails.

====================================
12. Count and Say problem(using StringBuilder):

    1, 11, 21, 1211, 111221, ...

    1 is read off as "one 1" or 11.
    11 is read off as "two 1s" or 21.
    21 is read off as "one 2, then one 1" or 1211.



    public String countAndSay(int n) {
        String element = "1";
        for(int i=1;i<n;i++) {
            element = getNextElement(element);
        }
        return element;
    }

    private String getNextElement(String element) {
        char c = element.charAt(0);
        int count=1;
        StringBuilder sb = new StringBuilder();

        for(int i=1;i<element.length();i++) {
            if(element.charAt(i) == c) {
                count++;
            } else {
                sb.append(count);
                sb.append(c);
                c=element.charAt(i);
                count=1;
            }
        }
        sb.append(count);
        sb.append(c);
        return sb.toString();
    }

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


================================================
16. Remove duplicates from sorted array. Return the final length of the array after removing duplicates.
// Check next solution

    public int removeDuplicates(int[] nums) {
        int insertIndex, iteratorIndex, len=nums.length;

        if(len<2)
            return len;

        for(insertIndex=0,iteratorIndex=1; iteratorIndex<len; iteratorIndex++) {
            while(iteratorIndex<len && nums[iteratorIndex]==nums[insertIndex]) {
                iteratorIndex++;
            }

            if (iteratorIndex<len) {
                if (nums[iteratorIndex]!=nums[insertIndex]) {
                    // first new element seen
                    // copy it to insertIndex+1 position
                    nums[++insertIndex] = nums[iteratorIndex];
                }
            } else if(insertIndex==0){
                // only 1 element was repeated throughout the array
                return 1;
            }
        }

        return insertIndex+1;
    }


===================================================
16.5  Follow up for "Remove Duplicates":
What if duplicates are allowed at most twice?

For example,
Given sorted array nums = [1,1,1,2,2,3],

Your function should return length = 5, with the first five elements of nums being 1, 1, 2, 2, 3.
It doesnt matter what you leave beyond the final length.

    // Smart and elegant solution
    public int removeDuplicates(int[] nums) {
        int insertIndex=0;
        for(int n : nums)
            if(insertIndex<2 || n>nums[insertIndex-2])
                nums[insertIndex++] = n;
        return insertIndex;
    }

    NOTE: If we replace 2 by 1 in the above code at both places, solution is for Remove Duplicates(with no repetition).

    // Time complexity: O(n) where n=size of array nums

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

==================================================
18. Swap nodes of a linked list in pairs:

    public ListNode swapPairs(ListNode head) {
        // 0 or 1 node list
        if(head==null || head.next==null)
            return head;

        //a fake head
        ListNode h = new ListNode(0);
        h.next = head;

        ListNode p1 = head, p2 = head.next, pre = h; // pre always points to the node before the pair being swapped
        while(p1!=null && p2!=null) {
            // link prev list to the current pair
            pre.next = p2;

            // swap links here
            ListNode t = p2.next;
            p2.next = p1;
            p1.next = t;

            // Move pre ahead
            pre = p1;

            // Move p1,p2 to next pair of 2 nodes
            p1 = p1.next;
            if(p1!=null)
                p2 = p1.next;
        }

        return h.next;
    }

    // Time complexity: O(n) where n=no.of nodes in linked list.

================================================
19. Merge two sorted lists:

// Algo uses a dummy node in front.
public class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode prev = dummy;

        while(l1!=null && l2!=null) {
            if(l1.val<=l2.val) {
                prev.next = l1;
                l1=l1.next;
            } else {
                prev.next=l2;
                l2=l2.next;
            }
            prev=prev.next;
        }

        // Check if any one list is non-empty
        if(l1!=null)
            prev.next=l1;
        if(l2!=null)
            prev.next=l2;
        return dummy.next;
    }
}

// Time cimplexity: O(n1+n2) where n1=no. of nodes in l1, n2=no. of nodes in l2.

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

============================================

23. Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c + d = target?
    Find all unique quadruplets in the array which gives the sum of target.

    Note: The solution set must not contain duplicate quadruplets.


    public List<List<Integer>> fourSum(int[] nums, int target) {
        int len=nums.length, k,l,sum;
        List<List<Integer>> fourSumList = new ArrayList<List<Integer>>();
        if (nums == null || nums.length < 4)
            return fourSumList;

        Arrays.sort(nums);

        // Array is now sorted, so add a guard
        if (nums[0] * 4 > target || nums[len-1] * 4 < target)
            return fourSumList;

        for(int i=0;i<len-3;i++) {
            // Additional guards for faster completion
            if (nums[i] + nums[len - 1] * 3 < target) //nums[i] is too small
                continue;
            if (nums[i] * 4 > target) { //nums[i] is too big
                break;
            }

            // Skip duplicates
            if (i>0 && nums[i]==nums[i-1]) {
                continue;
            }
            for(int j=i+1; j<len-2;j++) {
                // Additional guards for faster completion
                if (nums[i] + nums[j] + nums[len - 1] * 2 < target) //nums[j] is too small
                    continue;
                if (nums[i] + nums[j] * 3 > target) { //nums[j] is too big
                    break;
                }
                // Skip duplicates
                if(j>(i+1)&& nums[j]==nums[j-1]) {
                    continue;
                }
                // Find other 2 nos. in O(n) time using linear search
                k=j+1;
                l=len-1;
                while (k<l) {
                    sum=nums[i]+nums[j]+nums[k]+nums[l];
                    if(sum==target) {
                        fourSumList.add(Arrays.asList(nums[i],nums[j],nums[k],nums[l]));

                        // Skip duplicates
                        while(k<l && nums[k]==nums[k+1])
                            k++;
                        while(k<l && nums[l]==nums[l-1])
                            l--;

                        k++; // Skips over the last repeating element
                        l--; // Skips over the last repeating element
                    }

                    if(sum<target)
                        k++;
                    else if(sum>target)
                        l--;
                }
            }
        }

        return fourSumList;
    }

    // Time complexity: O(n^3) where n=no. of elements in input array

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

29. Generate n pairs of balanced parantheses.

    // Check this out for a DP solution to this problem: https://rekinyz.wordpress.com/2015/02/13/generate-parentheses/

    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        getAllPairs("", n, n, result);
        return result;
    }

    private void getAllPairs(String current, int open, int close, List<String> res) {
        if (close==0)
            res.add(current);

        if(open>0)
            // Put open bracket until we've exhausted all open brackets
            getAllPairs(current+"(", open-1, close, res);
        if(close>open)
            // Put close bracket only if there is a corresponding open bracket
            getAllPairs(current+")", open, close-1, res);
    }

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

==============================================

32. A robot is located at the top-left corner of a m x n grid. The robot can only move either down or right at any point in time.
    The robot is trying to reach the bottom-right corner of the grid.(1<=m,n<=100)

How many possible unique paths are there?

Note: Simple question although the logic involves the use of BigInteger since we are working on very large integers.
      Basically the robot has to go down "m-1" steps and go right "n-1" steps to go from top-left grid to bottom-right grid.
      So the questions asks the number of possible ways in which we can arrange (m-1) M's and (n-1) N's.

    import java.math.BigInteger;

    public int uniquePaths(int m, int n) {
        // Result = (m-1+n-1)! / ((m-1)! * (n-1)!)
        BigInteger[] fact = new BigInteger[m+n+1];
        fact[0]=BigInteger.ONE;
        factorial(m+n, fact);
        BigInteger res = fact[m+n-2].divide(fact[m-1].multiply(fact[n-1]));
        return res.intValue();

    }

    private void factorial(int x, BigInteger[] fact) {
        fact[1]=BigInteger.ONE;
        for(int i=2;i<x;i++)
            fact[i]=fact[i-1].multiply(BigInteger.valueOf(i));
    }

    // Time complexity: O(m+n)

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

=================================================
36. Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

    Integers in each row are sorted in ascending from left to right.
    Integers in each column are sorted in ascending from top to bottom.

    NOTE: Solution involves searching from top right corner(almost searching like binary search => deciding the direction of
        search based on the value of the current number)

    public boolean searchMatrix(int[][] matrix, int target) {
        // O(m+n) solution
        int numRows=matrix.length, numCols=matrix[0].length, row=0, col=numCols-1;

        // Start from the top right corner
        while(row<=numRows-1 && col>=0) {
            if (target==matrix[row][col])
                return true;
            if (target>matrix[row][col])
                row++;
            else
                col--;
        }
        return false;
    }

    // Time complexity: O(m+n); m=no. of rows; n=no. of cols

=============================================
37. Given a mxn grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.

    Note: You can only move either down or right at any point in time.

    public int minPathSum(int[][] grid) {
        int numRows = grid.length, numCols=grid[0].length;

        for(int i=0;i<numRows;i++) {
            for(int j=0;j<numCols;j++) {
                if (i==0 && j==0)
                    // 1st row, 1st col
                    grid[i][j] = grid[i][j];
                else if(i==0 && j!=0)
                    // 1st row
                    grid[i][j] = grid[i][j] + grid[i][j-1];
                else if(i!=0 && j==0)
                    // 1st col
                    grid[i][j] = grid[i][j] + grid[i-1][j];
                else
                    // other row and col(except 1st)
                    grid[i][j] = grid[i][j] + Math.min(grid[i][j-1], grid[i-1][j]);
            }
        }

        return grid[numRows-1][numCols-1];
    }

    // Time complexity: O(mn); m=no.of rows, n=no. of cols

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

===================================================

39. Given a nested list of integers, implement an iterator to flatten it.

Each element is either an integer, or a list -- whose elements may also be integers or other lists.

Example 1:
Given the list [[1,1],2,[1,1]],

By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,1,2,1,1].

Example 2:
Given the list [1,[4,[6]]],

By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,4,6]


/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
    public class NestedIterator implements Iterator<Integer> {
        NestedInteger nextElem;
        Stack<Iterator<NestedInteger>> stack;
        public NestedIterator(List<NestedInteger> nestedList) {
            stack = new Stack<>();
            stack.push(nestedList.iterator());
        }

        @Override
        public Integer next() {
            if(nextElem!=null)
                return nextElem.getInteger();
            return null;
        }

        @Override
        public boolean hasNext() {
            while(!stack.isEmpty()) {
                if(!stack.peek().hasNext())
                    // end of list reached
                    stack.pop();
                else if((nextElem = stack.peek().next()).isInteger())
                    // ensures nextElem is an Integer object
                    return true;
                else
                    // nextElem is a NestedInteger; so push the iterator of the inner list
                    stack.push(nextElem.getList().iterator());
            }
            return false;
        }
    }

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */

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

==================================================
44. Search in rotated sorted array with no duplicates:

Suppose a sorted array is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

You are given a target value to search. If found in the array return its index, otherwise return -1.


    // NOTE: The solution involves two algos:
    //     1. Search for pivot index
    //     2. Search for the target value in the sorted array


    public int search(int[] nums, int target) {
        int len=nums.length, low=0, high=len-1, mid, pivot;

        // Search for pivot index
        // For pivot index i, nums[i]<nums[i-1]
        while(nums[low] > nums[high]) {
            mid=low+(high-low)/2;
            if(nums[mid] > nums[high])
                low = mid+1;
            else
                high = mid; // Not (mid-1) since mid can also be the pivot
        }

        // "low" has the pivot index
        // Sorted array 1: [0...pivot-1]
        // Sorted array 2: [pivot...len-1]
        // Binary search in one of the two sorted arrays
        pivot=low;
        if(pivot!=0 && target>=nums[0]) {
            // target might be in sorted array 1
            low=0;
            high=pivot-1;
        } else {
            // target might be in sorted array 2
            // Or input is not even rotated(if pivot is 0)
            low=pivot;
            high=len-1;
        }
        // Perform usual binary search
        while(low<=high) {
            mid=low+(high-low)/2;
            if(target==nums[mid])
                return mid;
            if(target<nums[mid])
                high=mid-1;
            else
                low=mid+1;
        }
        return -1;
    }

    // Time complexity: O(log n) where n=no. of elements in nums[]

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

=============================================
49. Letter Combinations of a Phone Number: Given a digit string, return all possible letter combinations that the number could represent.

    public List<String> letterCombinations(String digits) {
        LinkedList<String> res = new LinkedList<>();
        String[] mapping = {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        if(digits.length()==0)
            return res;
        res.add("");

        for(int i=0;i<digits.length();i++) {
            // Iterate over every digit in input
            int d = Character.getNumericValue(digits.charAt(i));
            while(res.peek().length()==i) {
                // Iterate over all existing elements in the queue
                // Existing elements are all possible combinations of
                // i length strings for the mapping of equivalent i-length digits from input.
                // Eg: If input string="234", queue will be filled in this order
                // 1st iteration: [a,b,c]
                // 2nd iteration: remove a,b,c and add [ad,ae,af,bd,be,bf,cd,ce,cf]
                // 3rd iteration: remove existing 2 length elements and add [adg,adh,adi,aeg,aeh,aei,...cfg,cfh,cfi]
                String t = res.remove();
                for(char x : mapping[d].toCharArray())
                    // Add every character in the mapping of the digit to all exisiting elements in the queue
                    res.add(t+x);
            }
        }

        return res;
    }

    // Time complexity: 3^N or 4^N depending on whether the digit maps to 3 char string or 4 char string.
    // Worst case=4^N where N=length of input "digit" string

=================================================
50. Follow up for "Unique Paths":

Now consider if some obstacles are added to the grids. How many unique paths would there be to go from top-left to bottom-right?

An obstacle and empty space is marked as 1 and 0 respectively in the grid.

For example,

There is one obstacle in the middle of a 3x3 grid as illustrated below.

[
  [0,0,0],
  [0,1,0],
  [0,0,0]
]

The total number of unique paths is 2.

Note: m and n will be at most 100.

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int rows=obstacleGrid.length, cols=obstacleGrid[0].length;
        if(obstacleGrid[0][0]==1 || obstacleGrid[rows-1][cols-1]==1)
            return 0;

        int[][] dp = new int[rows][cols];

        // Fill 1st row
        for(int i=0;i<cols;i++) {
            if(obstacleGrid[0][i]==1) {
                while(i<cols) {
                    // Further cells in that row can't be reached(since we can only move right or down)
                    dp[0][i]=0;
                    i++;
                }
            } else if(obstacleGrid[0][i]==0) {
                    dp[0][i]=1;
            }
        }

        // Fill 1st col
        for(int i=0;i<rows;i++) {
            if(obstacleGrid[i][0]==1) {
                while(i<rows) {
                    // Further cells in that column can't be reached(since we can only move right or down)
                    dp[i][0]=0;
                    i++;
                }
            } else if(obstacleGrid[i][0]==0) {
                    dp[i][0]=1;
            }
        }

        // Fill rest of rows and cols
        for(int i=1;i<rows;i++) {
            for(int j=1;j<cols;j++) {
                if(obstacleGrid[i][j]==1)
                    dp[i][j] = 0;
                else
                    dp[i][j]=dp[i-1][j] + dp[i][j-1]; // We can reach this from one of the two neighboring cells(top or left)
            }
        }

        return dp[rows-1][cols-1];
    }

    // Time complexity: O(mn) where m=no. of rows; n=no. of cols.

========================================
51. Given a sorted array of integers, find the starting and ending position of a given target value.

If the target is not found in the array, return [-1, -1].

For example,
Given [5, 7, 7, 8, 8, 10] and target value 8,
return [3, 4].


    // Two binary searches. Elegant solution
    public int[] searchRange(int[] nums, int target) {
        return helper(nums, target, 0, nums.length - 1);
    }
    private int[] helper(int[] nums, int target, int lo, int hi) {
        if (nums[lo] == target && nums[hi] == target)
            return new int[]{lo, hi};
        if (nums[lo] <= target && nums[hi] >= target) {
            int mid = lo + (hi - lo) / 2;
            int[] left = helper(nums, target, lo, mid);
            int[] right = helper(nums, target, mid + 1, hi);
            if (left[0] == -1) return right; // Not found in left half
            if (right[0] == -1) return left; // Not found in right half
            return new int[]{left[0], right[1]}; // Found in both half(so choose startIndex in left half & endIndex in right half)
        }
        return new int[]{-1, -1};
    }

    // Time complexity: O(log n)

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

=================================================

53. Given an array of strings, group anagrams together.

For example, given: ["eat", "tea", "tan", "ate", "nat", "bat"],
Return:

[
  ["ate", "eat","tea"],
  ["nat","tan"],
  ["bat"]
]


    public class Solution {
        // Idea is to sort each string and check if it exists as key in HashMap
        // If it exists, then add the current string as value in hashmap
        // Else add current string as value in a new entry in hashmap
        public List<List<String>> groupAnagrams(String[] strs) {
            int numStrings=strs.length;
            Map<String, List<String>> sortedStrAnagrams = new HashMap<>(); // Key=sorted string, value=anagrams

            for(String str : strs) {
               char[] eachStr = str.toCharArray();
               Arrays.sort(eachStr);
               String sortedStr = new String(eachStr);
               if(sortedStrAnagrams.containsKey(sortedStr)) {
                   List<String> existingList = sortedStrAnagrams.get(sortedStr);
                   existingList.add(str);
                   sortedStrAnagrams.put(sortedStr, existingList);
               } else {
                   List<String> newList = new ArrayList<String>();
                   newList.add(str);
                   sortedStrAnagrams.put(sortedStr, newList);
               }
            }

            return new ArrayList<>(sortedStrAnagrams.values());
        }
    }

    // Time complexity: nklog(k) where n=no. of strings in input; k=length of each string

=============================================

54. Combination Sum II:  Given a collection of candidate numbers (C) and a target number (T), find all unique combinations
    in C where the candidate numbers sums to T.

Each number in C may only be used once in the combination.

Note:

    All numbers (including target) will be positive integers.
    The solution set must not contain duplicate combinations.

For example, given candidate set [10, 1, 2, 7, 6, 1, 5] and target 8,
A solution set is:

[
  [1, 7],
  [1, 2, 5],
  [2, 6],
  [1, 1, 6]
]



    public class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        int len=candidates.length, start=0;
        List<List<Integer>> res = new ArrayList<>();
        if(len==0)
            return res;
        Arrays.sort(candidates);
        getcombinationSum2(res, new ArrayList<>(), candidates, target, start);
        return res;
    }

    private void getcombinationSum2(List<List<Integer>> res, List<Integer> combo, int[] nums, int target, int start) {
        if(target<0)
            return;
        if(target==0)
            res.add(new ArrayList<>(combo));
        for(int i=start;i<nums.length;i++) {
            // With condition as (i-1>=0 && nums[i]==nums[i-1]), result will not have any duplicates within combo even if there are duplicates in input
            // With condition as (i>start && nums[i]==nums[i-1]), result will have duplicates within combo only if there are duplicates in input
            if(i>start && nums[i]==nums[i-1])
                continue; // Skip duplicates in nums
            combo.add(nums[i]);
            getcombinationSum2(res, combo, nums, target-nums[i], i+1);
            combo.remove(combo.size()-1);
        }
    }
}

=========================================
55. Permutations II:  Given a collection of numbers that might contain duplicates, return all possible unique permutations.

For example,
[1,1,2] have the following unique permutations:

[
  [1,1,2],
  [1,2,1],
  [2,1,1]
]



    public class Solution {
        public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(res, new ArrayList<>(), nums, new boolean[nums.length]);
        return res;
    }

        private void backtrack(List<List<Integer>> res, List<Integer> combo, int [] nums, boolean [] used){
            if(combo.size() == nums.length){
                res.add(new ArrayList<>(combo));
            } else{
                for(int i = 0; i < nums.length; i++){
                    if(used[i] || i > 0 && nums[i] == nums[i-1] && !used[i - 1])
                        // !used[i-1] ensures that we don't choose a duplicate as a starting number in combo
                        // i.e. if we start with 2 and get all possible permutations with 2(this might contain another 2 in the same combo if number 2 is repeated input). But after we've got all possible permutations with first number as 2, we will skip over all remaining 2's while selecting the next number to be the first number
                        continue;
                    used[i] = true;
                    combo.add(nums[i]);
                    backtrack(res, combo, nums, used);
                    used[i] = false;
                    combo.remove(combo.size() - 1);
                }
            }
        }
    }

========================================
56. Jump Game:  Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Determine if you are able to reach the last index.

For example:
A = [2,3,1,1,4], return true.

A = [3,2,1,0,4], return false.

    /* Starting from the second to last element in the array we
    continue to iterate towards the start of the array. Only stopping if we hit an element with a value of 0; in this
    case we evaluate if there exist an element somewhere at the start of the array which has a jump value large enough
    to jump over this 0 value element.
    */

    public boolean canJump(int[] nums) {
       if(nums.length < 2) return true;

       for(int curr = nums.length-2; curr>=0;curr--){
           if(nums[curr] == 0){
               int neededJumps = 1;
               while(neededJumps > nums[curr]){
                   neededJumps++;
                   curr--;
                   if(curr < 0) return false;
               }
           }
       }
       return true;
    }

    // Time complexity: O(n) since each element in the array in traversed exactly once.

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

======================================================

62. Longest Substring Without Repeating Characters:

    Given a string, find the length of the longest substring without repeating characters.

    Examples:

    Given "abcabcbb", the answer is "abc", with the length of 3.

    Given "bbbbb", the answer is "b", with the length of 1.

    Given "pwwkew", the answer is "wke", with the length of 3. Note that the answer must be a substring, "pwke" is a subsequence and not a substring.


    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> charLastIndexMap = new HashMap<>(); // Stores the last index where a character was seen
        int len=s.length(), subStringstart=0, curLen=0, maxLen=0, lastIndex;

        for(int i=0;i<len;i++) {
            char c = s.charAt(i);
            if(!charLastIndexMap.containsKey(c)) {
                charLastIndexMap.put(c,i);
                curLen++;
            } else {
                // current char seen before. Check if it is being considered in max substring
                lastIndex = charLastIndexMap.get(c);
                if (lastIndex>=subStringstart) {
                    // Reset curLen since current char is one of the chars considered in curLen
                    subStringstart=lastIndex+1; // remove this char from substring consideration
                    curLen=i-subStringstart+1;
                    charLastIndexMap.put(c,i);
                } else {
                    // Don't reset curLen since current char is not one of the chars considered in curLen
                    // (Eg: this code path is taken when current char is the second "a" in input string abba")
                    charLastIndexMap.put(c,i);
                    curLen++;
                }
            }

            if(curLen>maxLen)
                maxLen=curLen;
        }
        return maxLen;
    }

    // Time complexity: O(n) where n=length of String s.

======================================================
63. Maximum Product Subarray:  Find the contiguous subarray within an array (containing at least one number) which has the largest product.

For example, given the array [2,3,-2,4],
the contiguous subarray [2,3] has the largest product = 6.


    public int maxProduct(int[] A) {
        // Logic: Similar to max-sum-subarray problem except that here we should keep track of maxTillNow and minTillNow.
        // And use both these to multiply with the current num to see which one gives max value(Remember -ve * -ve = +ve).

        if(A.length==0)
            return 0;

        int globalMax=A[0], maxTillNow, minTillNow, maxPre=A[0], minPre=A[0];
        for(int i=1;i<A.length;i++) {
            // max of current and previous_max*current and previous_min*current
            maxTillNow = Math.max(Math.max(maxPre*A[i], minPre*A[i]), A[i]);
            // min of current and previous_max*current and previous_min*current
            minTillNow = Math.min(Math.min(maxPre*A[i], minPre*A[i]), A[i]);
            globalMax=Math.max(maxTillNow, globalMax);
            // Store max and min state for next iteration
            maxPre=maxTillNow;
            minPre=minTillNow;
        }
        return globalMax;
    }

    // Time complexity: O(n) where n=length of input array


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

=============================================
65. Given a string containing only digits, restore it by returning all possible valid IP address combinations.

For example:
Given "25525511135",

return ["255.255.11.135", "255.255.111.35"]. (Order does not matter)


    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        String s1, s2, s3, s4;
        int len=s.length();

        // i,j and k divide the input into 4 substrings - (0,i),(i,j),(j,k),(k,len)
        // (0,i) covers first 3 chars or less(if input size < 12)
        // (i,j) covers next 3 characters or less(if input size < 12)
        // (j,k) covers the next 3 chars or less(if input size < 12)
        // (k,len) covers the remaining chars in input
        for(int i=1;i<4 && i<len-2;i++) {
            for(int j=i+1; j<i+4 && j<len-1;j++) {
                for(int k=j+1; k<j+4 && k<len;k++) {
                    s1=s.substring(0,i);
                    s2=s.substring(i,j);
                    s3=s.substring(j,k);
                    s4=s.substring(k,len);
                    if(isValid(s1) && isValid(s2) && isValid(s3) && isValid(s4))
                        res.add(s1+"."+s2+"."+s3+"."+s4);
                }
            }
        }
        return res;
    }

    private boolean isValid(String x) {
        if(x.length()>3 || x.length()<=0 || (x.charAt(0)=='0' && x.length()>1) || Integer.parseInt(x)>255)
            return false;
        return true;
    }

    // Time complexity: Worst case: Inner most loop runs 3*3*3=27 times.
=====================================================

66. Word Search:  Given a 2D board and a word, find if the word exists in the grid.

The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring.
The same letter cell may not be used more than once.

For example, given board =

[
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]

word = "ABCCED", -> returns true,
word = "SEE", -> returns true,
word = "ABCB", -> returns false.


    public class Solution {
        boolean[][] visited;
        public boolean exist(char[][] board, String word) {
            int rows=board.length, cols=board[0].length;
            visited = new boolean[rows][cols];
            for(int i=0;i<rows;i++) {
                for(int j=0;j<cols;j++) {
                    // loop used to change the starting search position on the board
                    if (exist(board, word, i, j, 0))
                        return true;
                }
            }
            return false;
        }

        private boolean exist(char[][] board, String word, int row, int col, int index) {
            // All chars matched in the input word. So, match found
            if(index==word.length())
                return true;
            // index went beyond the board. Stop searching further
            if(row<0 || row>=board.length || col<0 || col>=board[0].length)
                return false;
            // This cell has been searched before. Don't search again
            if(visited[row][col]==true)
                return false;
            // Char doesn't match with input word. Don't continue further
            if(board[row][col]!=word.charAt(index))
                return false;
            // Current cell char matches with char at cur index in word
            // Continue search for remaining chars in word
            visited[row][col]=true;
            boolean isPresent = exist(board, word, row-1, col, index+1) ||
                                exist(board, word, row+1, col, index+1) ||
                                exist(board, word, row, col-1, index+1) ||
                                exist(board, word, row, col+1, index+1);
            visited[row][col]=false;
            return isPresent;
        }
    }

    // Time complexity: O(m*n*4*k) where m=no. of rows in board, n=no. of cols in board, k=length of word. 4 b'coz we search for
    // that word in all 4 neighboring directions.

========================================
67. Remove K Digits: Given a non-negative integer num represented as a string, remove k digits from the number so that the "new" number is the smallest possible.

Note:

    The length of num is less than 10002 and will be ≥ k.
    The given num does not contain any leading zero.

Example 1:

Input: num = "1432219", k = 3
Output: "1219"
Explanation: Remove the three digits 4, 3, and 2 to form the "new" number 1219 which is the smallest.

Example 2:

Input: num = "10200", k = 1
Output: "200"
Explanation: Remove the leading 1 and the number is 200. Note that the output must not contain leading zeroes.

Example 3:

Input: num = "10", k = 2
Output: "0"
Explanation: Remove all the digits from the number and it is left with nothing which is 0.



    public String removeKdigits(String num, int k) {
        // Idea: Remove digit from the input if it is greater than its succeeding digit
        int len=num.length();
        // Corner case
        if(k>=len)
            return "0";

        Stack<Character> s = new Stack<>();
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<len;i++) {
            char c = num.charAt(i);
            // Check if any digit needs to be evicted from the stack
            while(k>0 && !s.isEmpty() && s.peek()>c) {
                s.pop();
                k--;
            }
            // Insert the current digit into stack
            s.push(c);
        }

        // For cases like repeating or increasing digits. eg: "22222" or "12345"
        while(k>0) {
            s.pop();
            k--;
        }

        while(!s.isEmpty())
            sb.append(s.pop());
        sb.reverse();

        // Remove any zeros at the beginning of the result
        while(sb.length()>1 && sb.charAt(0)=='0')
            sb.deleteCharAt(0);

        return sb.toString();
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

============================================
71. Given a non-empty string, check if it can be constructed by taking a substring of it and appending multiple copies of the substring together.
You may assume the given string consists of lowercase English letters only and its length will not exceed 10000.

Example 1:

Input: "abab"
Output: True
Explanation: Its the substring "ab" twice.

    // For the repeating substring sequence to hold true, the substring length should divide the input string length
    // So, calculate divisors of input string length and for each of those divisors, get divisor length substring from start
    // of input string. Repeat it quotient times and check if we get the input string.
    public boolean repeatedSubstringPattern(String str) {
        int len = str.length(), quot;
        StringBuilder sb;
        String finalStr;

        for(int i=1;i<=len/2;i++) {
            if(len%i==0) {
                // i length string can be a potential repeating pattern
                quot = len/i;
                sb = new StringBuilder("");
                // Append this substr len/i times and check if you get input string
                for(int count=1;count<=quot;count++)
                    sb.append(str.substring(0, i));
                finalStr = sb.toString();
                //System.out.println(finalStr);
                if(finalStr.equals(str))
                    return true;
            }
        }
        return false;
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

=========================================

76.  Insert Delete GetRandom O(1)

    Design a data structure that supports all following operations in average O(1) time.

    insert(val): Inserts an item val to the set if not already present.
    remove(val): Removes an item val from the set if present.
    getRandom: Returns a random element from current set of elements. Each element must have the same probability of being returned.


    public class RandomizedSet {
        // List needed to ensure O(1) for getRandom()
        List<Integer> nums;
        // Map needed to ensure O(1) for insert() and remove(). Stores num and its corresponding index in nums list
        Map<Integer, Integer> numIndex;
        java.util.Random rand;

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

===========================================

77. Same question as above but duplicates are allowed while inserting elements. getRandom: Returns a random element from
    current collection of elements. The probability of each element being returned is linearly related to the number of same value the collection contains.

    // Idea is to use a LinkedHashSet as the value in numIndex map.
    // Using a LinkedHashSet instead of a HashSet because the set.iterator() might be costly when a number has too many
    // duplicates. Using LinkedHashSet can be considered as O(1) if we only get the first element to remove.

    public class RandomizedCollection {

        List<Integer> nums;
        HashMap<Integer, LinkedHashSet<Integer>> numIndex;
        java.util.Random rand;

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
    // probability of their occurence. e.g.

    // Generate random integers from within [1,3] with the following probabilities:

    // P(1) = 0.2
    // P(2) = 0.3
    // P(3) = 0.5

    // OPTION 1:
    // Generate a random integer within [0,100] and do the following:

    // If it is within [0,20] --> return 1.
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

===========================================

81. Iterative solution of inorder traversal(Left->ROOT->Right) of binary tree:

    // Similar to a recursive solution, store all left nodes in a user-stack
    // When left node becomes null, store the current node in result list
    // Repeat the same for curNode.right
    // Basically we are following LEFT->ROOT->RIGHT pattern
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode elem;

        if(root==null)
            return res;

        pushAllLeft(root, stack);
        while(!stack.isEmpty()) {
            elem = stack.pop();
            res.add(elem.val);
            pushAllLeft(elem.right, stack);
        }
        return res;
    }

    private void pushAllLeft(TreeNode root, Stack<TreeNode> s) {
        while(root!=null) {
            s.push(root);
            root=root.left;
        }
    }


==============================================

82. Iterative solution of preorder traversal(ROOT->LEFT->RIGHT) of binary tree:

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();

        if(root==null)
            return result;
        while(root!=null) {
            result.add(root.val);
            if(root.right!=null)
                stack.push(root.right);
            root=root.left; // Always go left
            if(root==null && !stack.isEmpty())
                // If left child was null then pop(right child) from stack if non-empty
                root = stack.pop();
        }

        return result;
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

==============================================

84. Binary Tree Zigzag Level Order Traversal: Given a binary tree, return the zigzag level order traversal of its nodes values.
(ie, from left to right, then right to left for the next level and alternate between).

For example:
Given binary tree [3,9,20,null,null,15,7],

    3
   / \
  9  20
    /  \
   15   7

return its zigzag level order traversal as:

[
  [3],
  [20,9],
  [15,7]
]


    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root==null)
            return res;

        getZigZag(root, res, 0);
        return res;
    }

    private void getZigZag(TreeNode root, List<List<Integer>> res, int level) {
        if(root==null)
            return;

        if(res.size()<=level) {
            // A new level is seen. Create its own level order list
            List<Integer> innerList = new ArrayList<>();
            res.add(innerList);
        }

        List<Integer> list = res.get(level);
        if(level%2==0)
            list.add(root.val); // Add at the end of the list
        else
            list.add(0, root.val); // Add at the front of the list

        getZigZag(root.left, res, level+1);
        getZigZag(root.right, res, level+1);
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

==================================

88. Decode String:
     Given an encoded string, return its decoded string.

The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times.
Note that k is guaranteed to be a positive integer.

You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.

Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k.
For example, there wont be input like 3a or 2[4].

Examples:

s = "3[a]2[bc]", return "aaabcbc".
s = "3[a2[c]]", return "accaccacc".
s = "2[abc]3[cd]ef", return "abcabccdcdcdef".



    public String decodeString(String s) {
        Stack<Integer> countStack = new Stack<>();
        Stack<String> charStack = new Stack<>();
        String res = "";
        int index=0, num=0, repeatCount;

        while(index < s.length()) {
            if(Character.isDigit(s.charAt(index))) {
                while(Character.isDigit(s.charAt(index))) {
                    num = num*10 + (s.charAt(index) - '0');
                    index++;
                }
                countStack.push(num);
                num=0;
            } else if(s.charAt(index)=='[') {
                charStack.push(res);
                res="";
                index++;
            } else if(s.charAt(index)==']') {
                StringBuilder temp = new StringBuilder(charStack.pop());
                repeatCount = countStack.pop();
                for(int i=0; i<repeatCount; i++)
                    temp.append(res);

                res = temp.toString();
                index++;
            } else {
                // its a letter
                res += s.charAt(index);
                index++;
            }
        }

        return res;
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

===========================================

91. Find K Pairs with Smallest Sums:

    You are given two integer arrays nums1 and nums2 sorted in ascending order and an integer k.

    Define a pair (u,v) which consists of one element from the first array and one element from the second array.

    Find the k pairs (u1,v1),(u2,v2) ...(uk,vk) with the smallest sums.

    Example 1:
    Given nums1 = [1,7,11], nums2 = [2,4,6],  k = 3

    Return: [1,2],[1,4],[1,6]

    The first 3 pairs are returned from the sequence:
    [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]


    Example 2:
    Given nums1 = [1,1,2], nums2 = [1,2,3],  k = 2

    Return: [1,1],[1,1]

    The first 2 pairs are returned from the sequence:
    [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]


    Example 3:
    Given nums1 = [1,2], nums2 = [3],  k = 3

    Return: [1,3],[2,3]

    All possible pairs are returned from the sequence:
    [1,3],[2,3]


    public class Solution {
        class Pair implements Comparable<Pair> {
            int n1, n2;
            int index; // index of n2 in nums2 array
            long sum;

            public Pair(int n1, int n2, int index) {
                this.n1 = n1;
                this.n2 = n2;
                this.index=index;
                this.sum = n1+n2;
            }

            @Override
            public int compareTo(Pair that) {
                return (int)(this.sum - that.sum);
            }
        }


        public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
            int len1=nums1.length, len2=nums2.length;
            List<int[]> res = new ArrayList<>();
            PriorityQueue<Pair> pq = new PriorityQueue<>();
            int nextIndex;

            if(nums1==null || len1==0 || nums2==null || len2==0)
                return res;

            // Add k(or len1 whichever is smaller) pairs by always taking first element from nums2[] and all elements from nums1[]
            for(int i=0; i<k && i<len1; i++) {
                pq.add(new Pair(nums1[i], nums2[0], 0));
            }

            for(int i=1; i<=k && !pq.isEmpty(); i++) {
                Pair p = pq.poll();
                res.add(new int[]{p.n1, p.n2});
                nextIndex = p.index + 1;
                if(nextIndex < len2) {
                    // within bounds of nums2[]
                    pq.add(new Pair(p.n1, nums2[nextIndex], nextIndex));
                }
            }
            return res;
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




