//        Given an array of citations (each citation is a non-negative integer) of a researcher, write a function to compute the researcher's h-index.
//
//        According to the definition of h-index on Wikipedia: "A scientist has index h if h of his/her N papers have at least h citations each,
//        and the other N − h papers have no more than h citations each."
//
//        Example:
//
//        Input: citations = [3,0,6,1,5]
//        Output: 3
//        Explanation: [3,0,6,1,5] means the researcher has 5 papers in total and each of them had
//        received 3, 0, 6, 1, 5 citations respectively.
//        Since the researcher has 3 papers with at least 3 citations each and the remaining
//        two with no more than 3 citations each, her h-index is 3.
//
//
//        Note: If there are several possible values for h, the maximum one is taken as the h-index.
//
//   Time complexity: O(n) where n = length of citations[]

package com.nitesh.array;

public class hIndex {
    public int hIndexFn(int[] citations) {
        if(citations == null || citations.length==0)
            return 0;
        int len = citations.length, paperCount = 0;
        int[] bucket = new int[1+len];

        // Fill bucket array(modified counting sort)
        for(int c: citations) {
            if(c > len)
                // count this citation in last bucket
                bucket[len]++;
            else
                bucket[c]++;
        }

        for(int i = len; i>=0; i--) {
            paperCount += bucket[i];
            if(paperCount >= i)
                return i; // since paperCount no. of papers have citations of atleast i; and (i < paperCount) which means i no. of papers have citations of atleast i(which is the h-index definition)
        }
        return 0; // no valid hIndex found
    }
}


// TIME COMPLEXITY:  O(n * log(n)), where n = total number of citations
//
//    public int hIndexFn(int[] citations) {
//        if(citations == null || citations.length==0)
//            return 0;
//        int hIndex=1;
//        Arrays.sort(citations);
//        for(int i=citations.length-1; i>0; i--) {
//            if(hIndex <= citations[i] && hIndex >=citations[i-1])
//                // Since array is sorted and hIndex is used as a counter from right end of array,
//                // (hIndex <= citations[i]) implies "hIndex" number of papers have atleast hIndex citations
//                // (hIndex >=citations[i-1]) implies "N-hIndex" number of papers have atmost hIndex citations(N=total no. of papers)
//                return hIndex;
//            hIndex++;
//        }
//        // Since above loop ends at citations[1], perform a check for citations[0] specifically
//        if(hIndex <= citations[0])
//            return hIndex;
//        return 0;
//    }