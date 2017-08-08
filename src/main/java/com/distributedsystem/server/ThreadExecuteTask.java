package com.distributedsystem.server;

/*
implement thread that execute tasks
 */
public class ThreadExecuteTask extends Thread {

    private Queue taskQueue = null;
    private boolean isStopped = false;

    public ThreadExecuteTask(Queue queue) {
        taskQueue = queue;

    }

    public void run() {
        System.out.println("run" + currentThread());

        while (!isStopped()) {

            try {
                System.out.println("deque thread " + currentThread());
                Runnable runnable = (Runnable) taskQueue.dequeue();
                System.out.println("running thread " + currentThread());
                runnable.run();
                System.out.println("running thread  now " + currentThread());

            } catch (Exception e) {
                e.printStackTrace();
                //log or otherwise report exception,
                //but keep pool thread alive.
            }
        }
    }

    public synchronized void doStop() {
        isStopped = true;
        this.interrupt(); //break pool thread out of dequeue() call.
    }

    public synchronized boolean isStopped() {
        return isStopped;
    }
}