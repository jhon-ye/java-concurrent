package org.example.concurrent.test;

public class TestVolatile {

    //读写屏障，防止指令重排序
    volatile boolean initialized = false;

    void init() {
        if (initialized) {
            return;
        }
        doInit();
        initialized = true;
    }

    private void doInit() {
    }
}
