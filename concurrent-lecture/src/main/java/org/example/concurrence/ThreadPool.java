package org.example.concurrence;

public interface ThreadPool {
    void excute(Job job);
    void shutDown();
    void removeWorker(int num);
    int getJobSize();
}
