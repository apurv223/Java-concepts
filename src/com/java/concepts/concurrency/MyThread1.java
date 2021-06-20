package com.java.concepts.concurrency;

/*
* 1. First way of creating a thread is to implement runnable interface
* 2. Thread scheduler decides itself the different states the thread must be in.
* 3. Runnable, running, blocked */
public class MyThread1 {

    public static void main(String args[]){

        Work work = new Work();
        Thread thread = new Thread(work);
        thread.start();
        System.out.println("Main ends, but JVM doesnt stop until all non-deamon threads are completed");
    }

}

class Work implements Runnable{

    @Override
    public void run() {
        System.out.println("Call stack Main->Work->run");
        go();
    }

    private void go() {
        System.out.println("Inside Main->Work->run->go");
        beyondPlusUltra();
    }
    private void beyondPlusUltra() {
        System.out.println("Inside Main->Work->run->go->beyondPlusUltra");
    }
}
