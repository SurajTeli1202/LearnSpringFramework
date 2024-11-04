package com.thread;

public class Thread1 implements Runnable {

    String name;
    public Thread1(String name) {
        this.name = name;
    }
    @Override
    public void run() {
        System.out.println("This is thread" + this.name);
        try {
            Thread.sleep(1000);
            throw new InterruptedException();
        } catch (InterruptedException e) {

        }
    }
}
