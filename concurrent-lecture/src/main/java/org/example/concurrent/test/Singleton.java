package org.example.concurrent.test;

public class Singleton {

    //DCL DOUBLE CHECK
    private static volatile Singleton INSTANCE = null;
    //double check
    public static Singleton getInstance() {
        if (INSTANCE == null) {
            synchronized (Singleton.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Singleton();
                }
            }
        }
        return INSTANCE;
    }
}
