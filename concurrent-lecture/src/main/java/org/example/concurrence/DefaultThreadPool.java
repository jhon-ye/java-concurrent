package org.example.concurrence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class DefaultThreadPool implements ThreadPool{
    private static final int MAX_WORKER_NUMBERS = 10;
    private static final int DEFAULT_WORKER_NUMBERS = 5;
    private static final int MIN_WORKER_NUMBERS = 1;

    private final LinkedList<Job> jobs = new LinkedList<>();
    private final List workers = Collections.synchronizedList(new ArrayList<>());

    private int workerNum = DEFAULT_WORKER_NUMBERS;
    private AtomicLong threadNum = new AtomicLong();

//    public DefaultThreadPool() {
//        initializeWokers(DEFAULT_WORKER_NUMBERS);
//    }
//
//    public DefaultThreadPool(int num) {
//        workerNum = num > MAX_WORKER_NUMBERS? MAX_WORKER_NUMBERS: num < MIN_WORKER_NUMBERS? MIN_WORKER_NUMBERS: num;
//        initializeWokers(DEFAULT_WORKER_NUMBERS);
//    }
//
//    private initializeWokers(int workerNum){
//        for (int i = 0; i < workerNum; i++) {
//
//        }
//    }

    @Override
    public void excute(Job job) {

    }

    @Override
    public void shutDown() {

    }

    @Override
    public void removeWorker(int num) {

    }

    @Override
    public int getJobSize() {
        return 0;
    }
}
