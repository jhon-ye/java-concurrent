package org.example.concurrence;

public class Producer extends Thread {

    private int need_num;
    private GoDown goDown;

    public Producer(int need_num, GoDown goDown) {
        this.need_num = need_num;
        this.goDown = goDown;
    }

    @Override
    public void run() {
        goDown.produce(need_num);
    }
}
