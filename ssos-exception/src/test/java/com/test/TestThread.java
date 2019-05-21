package com.test;

public class TestThread implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 1; i++) {

            add();
        }
    }

    private volatile static long a;

    private void add() {
        a++;

    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void main(String[] args) throws Exception {
        TestThread testThread = new TestThread();
        new Thread(() -> testThread.a()).start();
        new Thread(() -> testThread.b()).start();

    }
    public void a() {
        synchronized (Object.class) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"执行");
            b();
        }
    }
    public void b() {
        synchronized (String.class) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"执行");
            a();
        }
    }
}
