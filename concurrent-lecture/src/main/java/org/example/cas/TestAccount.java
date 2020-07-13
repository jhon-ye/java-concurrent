package org.example.cas;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class TestAccount {
    public static void main(String[] args) {

    }
}

class AccountCas implements Account {

    private AtomicInteger balancer;

    public AccountCas(int balance) {
        this.balancer = new AtomicInteger(balance);
    }

    @Override
    public Integer getBalance() {
        return balancer.get();
    }

    @Override
    public void withDraw(Integer amount) {
        while (true) {
            int prev = balancer.get();
            int next = prev - amount;
            if (balancer.compareAndSet(prev, next)) {
                break;
            }
        }
    }
}

class AccountUnsafe implements Account {

    private Integer balance;

    public AccountUnsafe(Integer balance) {
        this.balance = balance;
    }

    @Override
    public Integer getBalance() {
        return this.balance;
    }

    @Override
    public void withDraw(Integer amount) {
        synchronized (this) {
            this.balance -= amount;
        }
    }
}


interface Account {
    Integer getBalance();
    void withDraw(Integer amount);

    static void demo(Account account) {
        List<Thread> ts = new ArrayList<>();

        for (int i = 0; i < 1000; i++) {
            ts.add(new Thread(() -> {
                account.withDraw(10);
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

        System.out.println(account.getBalance() + ":" + (end - start));
    }
}
