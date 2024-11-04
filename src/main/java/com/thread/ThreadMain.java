package com.thread;

public class ThreadMain {

    public static void main(String args[]) throws InterruptedException {
        System.out.println("This is main thread");
        Thread t1 = new Thread(new Thread1("First Thread"));

        Thread t2 = new Thread(new Thread1("Second Thread"));

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("main thread finished");


    }
}
