package com.distributedsystem.server;

import java.util.LinkedList;
import java.util.List;

public class Queue {

    private List queue = new LinkedList();
    private int size = 5;

    public Queue(int size) {
        this.size = size;
        System.out.println("The Queue size is: " + size);
    }

    /*
    insert threads to the queue
    check queue is empty notyfy all threads and add threads to the queue
    queue is full wait until queue is free
     */
    public synchronized void enqueue(Object item) throws InterruptedException {
        while (this.queue.size() == this.size) {
            wait();
            System.out.println("Queue is full please wait");
            System.out.println("Wait is executed");
        }
        if (this.queue.size() == 0) {
            notifyAll();
            System.out.println("Queue is free");
        }

        this.queue.add(item);
        System.out.println("added thread" + item.toString());
    }

    /*
    remove threads to the queue
    check queue is empty notyfy all threads and add threads to the queue
    queu
    */
    public synchronized Object dequeue() throws InterruptedException {

        while (this.queue.size() == 0) {
            wait(3000);
            System.out.println("no threads in the queue so wait");
        }
        if (this.queue.size() == this.size) {
            notifyAll();
        }

        System.out.println("dequeue a thread");
        return this.queue.remove(0);

    }

}