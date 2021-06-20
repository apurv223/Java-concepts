package com.java.concepts.concurrency;

public class RaceConditionDemo {

    public static void main(String[] args){
        BankAccount task = new BankAccount();
        task.setBalance(100);

        Thread john = new Thread(task);
        Thread anitha = new Thread(task);

        john.setName("John");
        anitha.setName("Anitha");
        john.start();
        anitha.start();
    }

}

class BankAccount implements Runnable{
    private int balance;
    public void setBalance(int balance){
        this.balance = balance;
    }
    
    public void run(){
        makeWithdrawel(75);
        if(balance<0){
            System.out.println("Money overdrawn.");
        }
    }

    /*
    * Two ways to synchronize
    * 1. use keyword
    * 2. synchronized(Object){
    * // vulnerable code}
    *
    * Synchronized creates a lock on an object and the code inside synchronized method
    * is not shared by any other thread except one.
    *
    * On same instance variable we should not create more than one lock */
    synchronized private void makeWithdrawel(int amount) {
        if(amount>balance){
            System.out.println("Not enough balance");
        }else{
            System.out.println(Thread.currentThread().getName() + " is withdrawing "+ amount);
            balance-=amount;
        }
    }
}
