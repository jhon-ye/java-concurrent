package org.example.concurrent.test;


import java.io.Serializable;

// final 修饰：防止子类覆盖父类方法，破坏单例


//实现了序列化接口，防止反序列化破坏单例
public final class Singleton01 implements Serializable {

    private Singleton01() {
    }

    private static final Singleton01 INSTANCE = new Singleton01();

    public static Singleton01 getInstance() {
        return INSTANCE;
    }

    public Object readResovle () {
        return INSTANCE;
    }
}
