package org.example.concurrence;

import java.util.concurrent.TimeUnit;

public class Profiler {

    private static final ThreadLocal TIME_THREAD_LOCAL = new ThreadLocal();

    protected Long initialValue() {
        return System.currentTimeMillis();
    }

    public static final void begin() {
        TIME_THREAD_LOCAL.set(System.currentTimeMillis());
    }

    public static final long end() {
        long current = System.currentTimeMillis();
        return current - (Long) TIME_THREAD_LOCAL.get();
    }

    public static void main(String[] args) throws InterruptedException {
        Profiler.begin();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Cost: " + Profiler.end() + "mills");
    }
}
