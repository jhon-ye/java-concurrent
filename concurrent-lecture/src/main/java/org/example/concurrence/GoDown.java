package org.example.concurrence;

public class GoDown {

    public static final int max_size = 100;
    public int curr_num;

    GoDown() {}

    public GoDown(int curr_num) {
        this.curr_num = curr_num;
    }

    public synchronized void produce(int need_num) {
        while (need_num + curr_num > max_size) {
            System.out.println("暂时不生成");
            try {
                wait();
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }

        curr_num += need_num;
        System.out.println("已经生成 " +  need_num  + " 个产品 现在仓储量为" + curr_num);
        notifyAll();
    }

    public synchronized void consume(int need_num) {
        while (curr_num < need_num) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        curr_num -= need_num;
        System.out.println("已经消费了" + need_num + " 个产品 现仓储量为" + curr_num);
        notifyAll();
    }

    public static void main(String[] args) {
        GoDown godown = new GoDown(30);
        Consumer c1 = new Consumer(50, godown);
        Consumer c2 = new Consumer(20, godown);
        Consumer c3 = new Consumer(30, godown);
        Producer p1 = new Producer(10, godown);
        Producer p2 = new Producer(10, godown);
        Producer p3 = new Producer(10, godown);
        Producer p4 = new Producer(10, godown);
        Producer p5 = new Producer(10, godown);
        Producer p6 = new Producer(10, godown);
        Producer p7 = new Producer(80, godown);

        c1.start();
        c2.start();
        c3.start();
        p1.start();
        p2.start();
        p3.start();
        p4.start();
        p5.start();
        p6.start();
        p7.start();
    }
}
