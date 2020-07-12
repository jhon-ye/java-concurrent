package org.example.concurrence;

public class DeadLock {
    private static String A = "a";
    private static String B = "b";

    public static void main(String[] args) {
        new DeadLock().deadLock();
    }


    private void deadLock() {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (A) {
                    try {
                        Thread.currentThread().sleep(1500);
                        System.out.println(Thread.currentThread() + " sleep");
                    }catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }

                synchronized (B) {
                    System.out.println("1");
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (B) {
                    synchronized (A) {
                        System.out.println("2");
                    }
                }
            }
        });
        t2.start();
        t1.start();
    }
}
