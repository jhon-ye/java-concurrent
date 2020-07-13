package org.example.cas;

import java.sql.Time;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

public class TestABAAtomicStampereference {
    static AtomicStampedReference<String> ref = new AtomicStampedReference<>("A",0);
    public static void main(String[] args) throws InterruptedException {
        String prev = ref.getReference();
        int stamp = ref.getStamp();
        System.out.println("stamp:" + stamp);

        Thread.sleep(1);

        ref.compareAndSet(prev, "C", stamp, stamp +1);
    }

    private static void other() throws InterruptedException {
        new Thread(() ->{
            int stamp = ref.getStamp();
            ref.compareAndSet(ref.getReference(), "B", stamp, stamp + 1);
        }, "t1").start();

        Thread.sleep(1);

        new Thread(() ->{
            int stamp = ref.getStamp();
            ref.compareAndSet(ref.getReference(), "A", stamp, stamp + 1);
        }, "t2").start();

    }
}
