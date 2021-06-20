package com.java.concepts.concurrency;

public class ThreadPriorityDemo {

    public static void main(String[] args){
        System.out.println(Thread.currentThread());
        Thread t1 = new Thread(new EmailCampain());
        Thread t2 = new Thread(new DataAggregator());
        t1.setName("EmailCampain");
        t2.setName("DataAggregator");

        t1.setPriority(Thread.MAX_PRIORITY);

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Main Thread ends");
    }


}

class EmailCampain implements Runnable{

    @Override
    public void run() {
        for(int i=0;i<10;i++){
            System.out.println(Thread.currentThread().getName());
        }
    }
}

class DataAggregator implements Runnable{

    @Override
    public void run() {
        for(int i=0;i<10;i++){
            System.out.println(Thread.currentThread().getName());
        }
    }
}