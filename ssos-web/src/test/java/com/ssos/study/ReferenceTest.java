package com.ssos.study;

public class ReferenceTest {

    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (Object.class) {
                try {
                    Thread.sleep(20000);
                } catch (InterruptedException e) {
                    System.out.println(e.toString());
                }
                synchronized (String.class) {
                    System.out.println("wocao");
                }
            }
        }).start();
        new Thread(() -> {
            synchronized (String.class) {
                try {
                    Thread.sleep(20000);
                } catch (InterruptedException e) {
                    System.out.println(e.toString());
                }
                synchronized (Object.class) {
                    System.out.println("wocao");
                }
            }
        }).start();
    }
}
