package org.example.concurrence;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.util.concurrent.TimeUnit;

public class ConnectionDriver {

    static class ConnectionHandler implements InvocationHandler {
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if ("commit".equals(method.getName())) {
                TimeUnit.SECONDS.sleep(1);
            }
            return null;
        }
    }

    public static final java.sql.Connection createConnection() {
        return (Connection) Proxy.newProxyInstance(ConnectionDriver.class.getClassLoader(),
                new Class[] {Connection.class}, new ConnectionHandler());
    }
}
