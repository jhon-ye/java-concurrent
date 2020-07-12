package org.example.concurrence;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class SafeCount {

    private AtomicInteger atomicI = new AtomicInteger(0);
    private int i = 0;

    public static void main(String[] args) {
        final SafeCount count = new SafeCount();
        List<Thread> ts = new ArrayList(600);
        long start = System.currentTimeMillis();

        for (int i = 0; i < 100; i++) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 10000; j++) {
                        count.safeCount();
                        count.count();
                    }
                }
            });

            ts.add(t);
            ts.forEach(thr -> thr.start());
            /*ts.forEach(thread -> {
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
*/
            System.out.println(count.i);
            System.out.println(count.atomicI.get());

            System.out.println(System.currentTimeMillis() - start);
        }
    }

    private void safeCount() {
        for (;;) {
            int i = atomicI.get();
            boolean suc = atomicI.compareAndSet(i, ++i);
            if (suc) break;
        }
    }

    private void count() {
        ++i;
    }
}
