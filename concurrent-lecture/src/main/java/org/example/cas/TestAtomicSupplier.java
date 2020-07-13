package org.example.cas;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;
import java.util.function.Consumer;
import java.util.function.Supplier;



/*
* LongAdder
*
*
*
* */
class LockCas {
    private AtomicInteger state = new AtomicInteger(0);

    public void lock() {
        while (true) {
            if (state.compareAndSet(0, 1)) {
                break;
            }
        }
    }

    public void unlock () {
        state.set(0);
    }
}




public class TestAtomicSupplier {
    public static void main(String[] args) {
        demo(
                ()->new AtomicLong(0),
                (adder)-> adder.getAndIncrement()
        );

        demo(
                ()->new LongAdder(),
                (adder) ->adder.increment()
        );
    }


    private static <T> void demo(Supplier<T> adderSupplier, Consumer<T> action) {
        T t = adderSupplier.get();
        List<Thread> ts = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            ts.add(new Thread(() -> {
                for (int j = 0; j < 50000; j++) {
                    action.accept(t);
                }
            }));
        }

        long start = System.nanoTime();

        ts.forEach(Thread::start);
        ts.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        long end = System.nanoTime();
        System.out.println(t + " cost:" + (end - start)/100000);
    }
}


