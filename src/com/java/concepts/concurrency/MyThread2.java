package com.java.concepts.concurrency;

public class MyThread2 extends Thread{

    public static void main(String args[]){
        MyThread2 thread = new MyThread2();
        thread.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Or use enum TimeUnit for sleep
        //Throws InterruptedException to enable other thread check if the thread is interrupted for some reason
        System.out.println("Main ends, but JVM doesnt stop until all non-deamon threads are completed");
    }

    public void run(){
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
