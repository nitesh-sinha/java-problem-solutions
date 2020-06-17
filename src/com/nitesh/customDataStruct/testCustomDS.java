package com.nitesh.customDataStruct;

public class testCustomDS {
    public void testCustomDSFn() {
        /*
        The following is a snapshot of the output returned by test code below:
        ----------------------------------------------------------------------
        Num of chars written = 5
        Num of chars read = 2
        Num of chars written = 2
        Num of chars read = 3
        Num of chars written = 3
        Num of chars written = 0
        Num of chars read = 5
        Num of chars read = 0
         */
        CircularBuffer x = new CircularBuffer(5);
        char[] dest;
        System.out.println("Num of chars written = " + x.write("abcblah", 7));
        dest = new char[2];
        System.out.println("Num of chars read = " + x.read(dest, 2));

        System.out.println("Num of chars written = " + x.write("1234", 4));
        dest = new char[3];
        System.out.println("Num of chars read = " + x.read(dest, 3));

        System.out.println("Num of chars written = " + x.write("xyzw", 4));
        System.out.println("Num of chars written = " + x.write("foo", 3));
        dest = new char[5];
        System.out.println("Num of chars read = " + x.read(dest, 5));
        dest = new char[3];
        System.out.println("Num of chars read = " + x.read(dest, 3));
    }
}
