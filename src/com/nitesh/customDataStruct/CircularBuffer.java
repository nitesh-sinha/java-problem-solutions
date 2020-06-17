//            Design a circular buffer of a given size. Design two of its interfaces:
//
//            write(input, len) -> Attempts to write the input of length "len" to buffer. Returns the actual length of chars written.
//            read(dest, len) -> Attempts to read len chars from buffer and copies it to dest. Returns the actual length of chars read.
//
//            Eg: If buffer is of length 5.
//
//            b = _ _ _ _ _
//
//            write("abcblah", 7) -> b = a b c b l, Returns 5
//            read(dest, 2)       -> b = _ _ c b l, dest = a b, Return 2
//
//            write("1234", 4)    -> b = 1 2 c b l, Returns 2
//            read(dest, 3)       -> b = 1 2 _ _ _, dest = c b l, Return 3
//
//            write("xyzw", 4)    -> b = 1 2 x y z, Returns 3
//            write('foo', 3)     -> b = 1 2 x y z, Returns 0
//            read(dest, 5)       -> b = _ _ _ _ _, dest = 1 2 x y z, Return 5
//            read(dest, 3)       -> b = _ _ _ _ _, dest = <empty>, Return 0

package com.nitesh.customDataStruct;

public class CircularBuffer {
    private char[] circBuff;
    private int front; // points to the next char to be read
    private int rear; // points to the last char written
    private int size;

    public CircularBuffer(int size) {
        this.size = size;
        this.circBuff = new char[size];
        front = rear = -1;
    }

    public int write(String text, int length) {
        int charsWritten = 0;

        while(charsWritten < length) {
            // check overflow cases
            if(front == 0 && rear == size-1 || rear == front - 1)
                break;

            // Adjust the rear pointer
            if(rear == -1)
                // buffer is empty - Special case: So adjust front pointer too
                front = rear = 0;
            else
                rear = (rear + 1) % size; // circular buffer; so loop it over to beginning

            // Now write to circular buffer
            circBuff[rear] = text.charAt(charsWritten);
            charsWritten++;
        }
        return charsWritten;
    }

    public int read(char[] dest, int length) {
        int charsRead = 0;

        while(charsRead < length) {
            // check underflow cases
            if(front == - 1 && rear == -1)
                break;

            // Read from circular buffer first
            dest[charsRead] = circBuff[front];
            charsRead++;
            // Now adjust the front pointer
            if(front == rear)
                // last char was just read
                front = rear = -1;
            else
                front = (front + 1) % size; // circular buffer; so loop it over to beginning
        }
        return charsRead;
    }
}
