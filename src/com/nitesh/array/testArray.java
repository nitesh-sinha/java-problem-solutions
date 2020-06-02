package com.nitesh.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class testArray {
//    private void deleteFn(List<Integer> l2) {
//        l2.remove(0);
//        System.out.println("size inside after delete = " + l2.size());
//        for(Integer elem : l2)
//            System.out.println(elem);
//        System.out.println("=========");
//    }

//    private int[] returnFn() {
//        int[] x = {1,2,3,4};
//        return x;
//    }
    public void testfn() {
//        for(int x : returnFn())
//            System.out.println(x);
        int[] arr1 = {9,7,6,5,4,3,2,3,3,4};
//        mergeSort x = new mergeSort();
//        x.mergeSortFn(arr1);
//        for(int num : arr1)
//            System.out.println(num);
//        countInversions x = new countInversions();
//        System.out.println(x.countInversionsFn(arr1));

//        topKFrequent x = new topKFrequent();
//        List<Integer> res = x.topKFrequentFn(arr1, 10);
//        for(Integer i : res)
//            System.out.println(i);

        increasingTriplet x = new increasingTriplet();
        System.out.println(x.increasingTripletFn(arr1));


//        int[] arr1 = {1,2,3,4,5};
//        int[] arr2;
//        arr2 = Arrays.copyOfRange(arr1, 0, arr1.length-1);
//        System.out.println("Size of arr2 = " + arr2.length);

//        LinkedList<Integer> l1 = new LinkedList<>();
//        l1.add(1);
//        l1.add(2);
//        l1.add(3);
//
//        System.out.println("size outside before delete = " + l1.size());
//        deleteFn(l1);
//        System.out.println("size outside after delete = " + l1.size());
//
//        for(Integer elem : l1)
//            System.out.println(elem);

//        System.out.println(l1.pop());
//        System.out.println("num at index 1 is " + l1.get(1));
//        l1.remove(1);
//        System.out.println("num at index 0 is " + l1.get(0));
//        System.out.println("size = " + l1.size());
//        l1.clear();
//        System.out.println("size = " + l1.size());
//        System.out.println(l1.peek());


//
//        Integer[] i = {1,2,3,4};
//        List<Integer> l2 = Arrays.asList(i);
//        System.out.println("l2 size = " + l2.size());
//        Integer[] i2 = new Integer[l2.size()];
//        l2.toArray(i2);
//        System.out.println("Lets print the integers obtained back");
//        for(Integer elem : i2)
//            System.out.println(elem);
//
//        // another diff test
//        int x1=Integer.MAX_VALUE;
//        int x2 = Integer.MIN_VALUE;
//        int diff=x1-x2; // -1 ; No overflow
//        System.out.println(diff);
//
//        // binary search index test
//        int[] a = {2,3,6,7,9,13,15,19};
//        int index = Arrays.binarySearch(a,10);
//        System.out.println("index = " + index);

        // 2 D array
//        int[][] twoD = {
//                {1,2,3,3},
//                {4,5,6,6},
//                {7,8,9,9}
//        };
//        int[][] twoD = {{1,2}};
//        //int[] firstCol = new int[5];
//        System.out.println("size = " + twoD.length + " " + twoD[0].length);
//        for(int j=0;j<twoD.length;j++)
//            firstCol[j] = twoD[j][0];
//        int index1 = Arrays.binarySearch(firstCol,5);
//        System.out.println("index1 = " + index1);

    }

}
