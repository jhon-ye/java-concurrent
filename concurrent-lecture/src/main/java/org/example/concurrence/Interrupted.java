package org.example.concurrence;

import java.util.concurrent.TimeUnit;

public class Interrupted {
    public static void main(String[] args) throws InterruptedException {
        Thread sleepThread = new Thread(new SleepRunner(), "sleepThread");
        sleepThread.setDaemon(true);
        Thread busyThread = new Thread(new BusyRunner(), "busyThread");
        busyThread.setDaemon(true);

        sleepThread.start();
        busyThread.start();

        TimeUnit.SECONDS.sleep(5);
        sleepThread.interrupt();
        busyThread.interrupt();

        System.out.println("sleepThread interrupted is " + sleepThread.isInterrupted());
        System.out.println("busyThread interrupted is " + busyThread.isInterrupted());

        Thread.sleep(2000);
    }

    static class SleepRunner implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class BusyRunner implements Runnable {
        @Override
        public void run() {
            while (true) {}
        }
    }
}
