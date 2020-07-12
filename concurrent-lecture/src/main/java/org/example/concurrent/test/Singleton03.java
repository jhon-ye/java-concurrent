package org.example.concurrent.test;


import java.io.Serializable;

public final class Singleton03 implements Serializable {

    private Singleton03() {
    }

    private static Singleton03 INSTANCE = null;

    public static synchronized Singleton03 getInstance() {
        if (INSTANCE==null){
            INSTANCE = new Singleton03();
        }
        return INSTANCE;
    }
}
