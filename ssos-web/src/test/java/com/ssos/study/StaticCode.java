package com.ssos.study;

public class StaticCode {

    private boolean status = false;
    private volatile int a = 0;
    private volatile int result = 0;

    public static void main(String[] args) throws InterruptedException {
        StaticCode staticCode = new StaticCode();
        new Thread(() -> {
            if (!staticCode.status) {
                staticCode.wirte();
            }else{
                staticCode.read();
            }
        }).start();
        new Thread(() ->{
            if (!staticCode.status) {
                staticCode.wirte();
            }else{
                staticCode.read();
            }
        }).start();
        System.out.println(staticCode.result);
        System.out.println(staticCode.status);
        System.out.println(staticCode.a);
        System.out.println(Runtime.getRuntime().maxMemory()/1024.0/1024);
        System.out.println(Runtime.getRuntime().freeMemory()/1024.0/1024);
        System.out.println(Runtime.getRuntime().totalMemory()/1024.0/1024);
    }

    public synchronized void read() {
        if (status) {
            result = a * 3;
        }
    }

    public synchronized void wirte() {
        status = true;
        a = 2;
    }


}
