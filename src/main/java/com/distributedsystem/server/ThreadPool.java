package com.distributedsystem.server;

import java.util.ArrayList;
import java.util.List;

public class ThreadPool {

    private Queue taskQueue = null;
    private List<ThreadExecuteTask> threads = new ArrayList<ThreadExecuteTask>();
    private boolean isStopped = false;

    public ThreadPool(int noOfThreads, int maxNoOfTasksInQueue) {
        //create a queue  taskQueue  with size maxNoOfTasksInQueue
        taskQueue = new Queue(maxNoOfTasksInQueue);
        System.out.println("initializing queue with size : " + maxNoOfTasksInQueue);

        for (int i = 0; i < noOfThreads; i++) {

            threads.add(new ThreadExecuteTask(taskQueue));
            System.out.println("Adding Task to the Thread Pool : " + i);

        }
        for (ThreadExecuteTask thread : threads) {
            thread.start();
            System.out.println("Start" + thread);

        }

    }

    public synchronized void execute(Runnable task) throws Exception {
        System.out.println("execute" + task.toString());
        if (this.isStopped)
            throw
                    new IllegalStateException("ThreadPool is stopped");


        this.taskQueue.enqueue(task);
    }

    public synchronized void stop() {
        this.isStopped = true;
        for (ThreadExecuteTask thread : threads) {
            thread.doStop();
        }
    }

}