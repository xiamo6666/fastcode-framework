package com.ssos.study;

import java.util.concurrent.*;

public class TaskCallable {
    public static void main(String[] args) throws Exception {
//        Executors.newCachedThreadPool()
        long l = System.currentTimeMillis();
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future<Long> value1 = executorService.submit(() -> {
            long a = 0;
            for (int i = 0; i < Integer.MAX_VALUE/2; i++) {
                a += i;
            }
            return a;
        });
        Future<Long> value2 = executorService.submit(() -> {
            long a = 0;
            for (int i = Integer.MAX_VALUE+1; i < Integer.MAX_VALUE; i++) {
                a += i;
            }
            return a;
        });
        System.out.println(value1.get()+value2.get());
        System.out.println(System.currentTimeMillis()-l);

        long l1 = System.currentTimeMillis();
        long q = 0;
        for (int i = 0; i < Integer.MAX_VALUE ; i++) {
            q += i;
        }
        System.out.println(q);
        System.out.println(System.currentTimeMillis() - l1);
    }
}
