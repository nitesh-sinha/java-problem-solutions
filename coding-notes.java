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

=====================================
39. Minimax problem:        https://leetcode.com/problems/guess-number-higher-or-lower-ii/

=====================================

40. Peeking iterator: Given an Iterator 'class' intrface with methods: next() and hasNext(), design and
    implement a PeekingIterator that support the peek() operation -- it essentially peek() at the element
    that will be returned by the next call to next().

    /** My solution: https://leetcode.com/problems/peeking-iterator/
    **/

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

=============================

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




