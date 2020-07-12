package org.example.concurrent.test;

public class Singleton04 {

    private static class LazyHolder {
        static final Singleton04 INSTANCE = new Singleton04();
    }

    public static Singleton04 getInstance() {
        return LazyHolder.INSTANCE;
    }
}
