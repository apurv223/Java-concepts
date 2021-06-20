package com.java.concepts.concurrency;


import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class ExecutorNeedDemo {
    public static void main(String[] args){
        Callable<Integer> task = new MyTask();
        FutureTask<Integer>[] futureList = new FutureTask[5];
        for(int i=0;i<futureList.length;i++){
            futureList[i] = new FutureTask<>(task);
            Thread t = new Thread(futureList[i]);
            t.start();
        }

        for (int i = 0; i <= 4; i++) {
            FutureTask<Integer> result = futureList[i];
            try {
                System.out.println("Future Task" + i + ":" + result.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

    }
}

class MyTask implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        int sum=0;
        for(int i=1;i<=5;i++){
            sum+=i;
        }
        return sum;
    }
}
