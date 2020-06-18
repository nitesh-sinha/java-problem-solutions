package com.nitesh.customDataStruct;

public class testCustomDS {
    public void testCustomDSFn() {
        /*
        The following is a snapshot of output returned by CircularBuffer test code below:
        -------------------------------------------------------------------------------------
        Num of chars written = 5
        Num of chars read = 2
        Num of chars written = 2
        Num of chars read = 3
        Num of chars written = 3
        Num of chars written = 0
        Num of chars read = 5
        Num of chars read = 0
         */
//        CircularBuffer x = new CircularBuffer(5);
//        char[] dest;
//        System.out.println("Num of chars written = " + x.write("abcblah", 7));
//        dest = new char[2];
//        System.out.println("Num of chars read = " + x.read(dest, 2));
//
//        System.out.println("Num of chars written = " + x.write("1234", 4));
//        dest = new char[3];
//        System.out.println("Num of chars read = " + x.read(dest, 3));
//
//        System.out.println("Num of chars written = " + x.write("xyzw", 4));
//        System.out.println("Num of chars written = " + x.write("foo", 3));
//        dest = new char[5];
//        System.out.println("Num of chars read = " + x.read(dest, 5));
//        dest = new char[3];
//        System.out.println("Num of chars read = " + x.read(dest, 3));


        // ------------------------------------------------------------
        /*
        The following is a snapshot of output from AllOne test code below:
        ------------------------------------------------------------------
        xyz
        bar
        abc
        xyz
        abc
        xyz
        abc
        foo
        foo
        abc
        foo
        foo


         */
        AllOne x = new AllOne();
        x.inc("abc");
        x.inc("xyz");
        x.inc("foo");
        x.inc("bar");
        x.inc("xyz");
        x.inc("xyz");
        x.inc("abc");
        x.inc("foo");
        x.inc("xyz");
        x.inc("xyz");
        // xyz = 5, abc = 2, foo = 2, bar = 1
        System.out.println(x.getMaxKey()); // should print xyz
        System.out.println(x.getMinKey()); // should print bar

        x.dec("bar"); // bar = 0 ==> gets evicted
        x.inc("foo");
        // xyz = 5, abc = 2, foo = 3
        System.out.println(x.getMinKey()); // should print abc
        System.out.println(x.getMaxKey()); // should print xyz
        x.dec("xyz");
        x.dec("xyz");
        x.dec("xyz");
        x.inc("abc");
        x.inc("abc");
        // xyz = 2, abc = 4, foo = 3
        System.out.println(x.getMaxKey()); // should print abc
        System.out.println(x.getMinKey()); // should print xyz
        x.dec("xyz");
        x.dec("xyz");
        // abc = 4, foo = 3
        System.out.println(x.getMaxKey()); // should print abc
        System.out.println(x.getMinKey()); // should print foo
        x.dec("abc");
        x.dec("abc");
        // abc = 2, foo = 3
        System.out.println(x.getMaxKey()); // should print foo
        System.out.println(x.getMinKey()); // should print abc
        x.dec("abc");
        x.dec("abc");
        // foo = 3
        System.out.println(x.getMaxKey()); // should print foo
        System.out.println(x.getMinKey()); // should print foo
        x.dec("foo");
        x.dec("foo");
        x.dec("foo");
        // No key exists in DS
        System.out.println(x.getMaxKey()); // should print empty-string("")
        System.out.println(x.getMinKey()); // should print empty-string("")
    }
}
