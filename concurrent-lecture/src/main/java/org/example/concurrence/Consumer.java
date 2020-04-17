package org.example.concurrence;

public class Consumer extends Thread{

    private int need_num;
    private GoDown goDown;

    public Consumer(int need_num, GoDown goDown) {
        this.need_num = need_num;
        this.goDown = goDown;
    }

    @Override
    public void run() {
        goDown.consume(need_num);
    }
}
