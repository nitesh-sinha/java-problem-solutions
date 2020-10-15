package com.nitesh.multi;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class ProducerConsumerExample {
    // Java program to demonstrate inter-thread communication
    // (wait(), join() and notify()) in Java

    // PC (ProducerConsumer) class with produce() and consume() methods.
    public static class PC {
        // create a buffer of fixed size shared between producer and consumer
        List<Integer> buff = new LinkedList<>();
        int buffSize = 3;
        Random rand = new Random(); // to generate a random value in producer thread

        // Prints a string and waits for consume()
        public void produce() throws InterruptedException {
            while(true) {
                // synchronized block ensures only one thread holding the monitor PC object can run at a time.
                synchronized(this) {
                    while (buff.size() == buffSize)
                        // buffer is full. Cannot add any new item
                        wait(); // releases the lock until next time a notify() is received by this thread

                    int item = rand.nextInt(10);

                    System.out.println("Producer produced value " + item);
                    buff.add(item);

                    notify();
                }
                System.out.println("Producer: Going to sleep for 0.1 sec");
                Thread.sleep(100); // simulates a shorter delay to obtain a new element for producer
                System.out.println("Producer: Done sleeping");
            }
        }

        // Sleeps for some time and waits for a key press. After key
        // is pressed, it notifies produce().
        public void consume() throws InterruptedException {
            while(true) {
                // synchronized block ensures only one thread holding the monitor PC object can run at a time.
                synchronized(this) {
                    while (buff.size() == 0)
                        // empty buffer; can't read anything
                        wait(); // gives up the lock on monitor object

                    int item = buff.remove(0);
                    System.out.println("Consumer consumed value " + item);

                    // notifies the produce thread that it can wake up.
                    notify(); // wakes up producer thread but doesnt give up lock until synchronized block ends
                }
                // Sleep
                System.out.println("Consumer: Going to sleep for 2 sec");
                Thread.sleep(2000); // simulates a longer delay for consumer to process one item in buffer
                System.out.println("Consumer: Done sleeping");
            }
        }
    }


    // Main code to test the Producer Consumer threads
    public void PCTester()  throws InterruptedException {
        final PC pc = new PC();

        // Create a producer thread
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    pc.produce();
                } catch(InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // Create a consumer thread
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    pc.consume();
                } catch(InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // Create another consumer thread
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    pc.consume();
                } catch(InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // Start all 3 threads
        t1.start(); // JVM calls run() method defined in t1's runnable interface implementation
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();
    }
}

//  The above programs prints the following output:
//  -----------------------------------------------
//        Producer produced value 3
//        Producer: Going to sleep for 0.1 sec
//        Consumer consumed value 3
//        Consumer: Going to sleep for 2 sec
//        Producer: Done sleeping
//        Producer produced value 2
//        Producer: Going to sleep for 0.1 sec
//        Consumer consumed value 2
//        Consumer: Going to sleep for 2 sec
//        Producer: Done sleeping
//        Producer produced value 6
//        Producer: Going to sleep for 0.1 sec
//        Producer: Done sleeping
//        Producer produced value 2
//        Producer: Going to sleep for 0.1 sec
//        Producer: Done sleeping
//        Producer produced value 5
//        Producer: Going to sleep for 0.1 sec
//        Producer: Done sleeping
//        Consumer: Done sleeping
//        Consumer consumed value 6
//        Consumer: Going to sleep for 2 sec
//        Producer produced value 3
//        Producer: Going to sleep for 0.1 sec
//        Producer: Done sleeping
//        Consumer: Done sleeping
//        Consumer consumed value 2
//        Consumer: Going to sleep for 2 sec
//        Producer produced value 5
//        Producer: Going to sleep for 0.1 sec
//        Producer: Done sleeping
//        Consumer: Done sleeping
//        Consumer consumed value 5
//        Consumer: Going to sleep for 2 sec
//        Producer produced value 6
//        Producer: Going to sleep for 0.1 sec
//        Producer: Done sleeping
//        Consumer: Done sleeping
//        Consumer consumed value 3
//        Consumer: Going to sleep for 2 sec
//        Producer produced value 0
//        Producer: Going to sleep for 0.1 sec
//        Producer: Done sleeping
//        Consumer: Done sleeping
//        Consumer consumed value 5
//        Consumer: Going to sleep for 2 sec
//        Producer produced value 0
//        Producer: Going to sleep for 0.1 sec
//        Producer: Done sleeping
//        Consumer: Done sleeping
//        Consumer consumed value 6
//        Consumer: Going to sleep for 2 sec
//        Producer produced value 7
//        Producer: Going to sleep for 0.1 sec
//        Producer: Done sleeping


