package com.java.concepts.concurrency;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/*Covers concept of atomic variables*/
public class MeetUpEventSimulator {
    public static class MeetUpEvent{
        private String name;
        AtomicInteger count = new AtomicInteger(1);

        public MeetUpEvent(String name){
            this.name = name;
        }
        public void attending(int guestCount){
            if(guestCount==1){
                count.incrementAndGet();
            }
            else{
                count.addAndGet(guestCount);
            }
        }
        public void notAttending(int guestCount){
            if(guestCount==1){
                count.decrementAndGet();
            }else{
                boolean updated = false;
                //compare and swap functionality used internally by atomic variables
                while(updated!=true){
                    int currentCount = count.get();
                    int newCount = currentCount - guestCount;
                    updated = count.compareAndSet(currentCount,newCount);
                }
            }
        }
        public int getCount(){
            return count.get();
        }
    }

    public static void main(String[] args){
        MeetUpEvent jgg = new MeetUpEvent("Java Guna Group");

        Thread user1 = new Thread(()->{
           jgg.attending(4);
            System.out.println(Thread.currentThread().getName() + ":" + jgg.getCount());
        });

        Thread user2 = new Thread(()->{
            jgg.attending(3);
            System.out.println(Thread.currentThread().getName() + ":" + jgg.getCount());
            jgg.notAttending(3);
            System.out.println(Thread.currentThread().getName() + ":" + jgg.getCount());
        });

        Thread user3 = new Thread(()->{
            jgg.attending(1);
            System.out.println(Thread.currentThread().getName() + ":" + jgg.getCount());
        });

        user1.setName("User 1");
        user2.setName("User 2");
        user3.setName("User 3");

        user1.start();
        user2.start();
        user3.start();
        sleep(3);
        System.out.println("Total attendees: "+ jgg.getCount());
    }

    private static void sleep(int i) {
        try {
            TimeUnit.SECONDS.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
