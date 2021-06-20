package com.java.concepts.concurrency;

import java.util.concurrent.*;

public class ExecutorDemo {
    public static void main(String[] args){
        ExecutorService service = Executors.newFixedThreadPool(3);
        Callable<Integer> task = new MyNewTask();
        for (int i = 0; i < 10; i++) {
            Future<Integer> future = service.submit(task);
            try {
                System.out.println("Result from Future " + i + ":" + future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        service.shutdown();
    }
}


class MyNewTask implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        int sum=0;
        for(int i=1;i<=5;i++){
            sum+=i;
        }

        return sum;
    }
}