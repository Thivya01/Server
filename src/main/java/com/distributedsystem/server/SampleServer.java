package com.distributedsystem.server;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class SampleServer {
    private static Socket socket;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please Enter maximum no of threads in thread pool");
        int noOfThreads = scanner.nextInt();
        System.out.println("Please Enter maximum no of task handle by queue");
        int maxNoOfTaskInQueue = scanner.nextInt();

        ThreadPool threadPool = new ThreadPool(noOfThreads, maxNoOfTaskInQueue);

        try {

            final int port = 25000;
            System.out.println("Server Started and listening to the port" + port);
            final ServerSocket serverSocket = new ServerSocket(port);

            //Server is running always. This is done using this while(true) loop
            threadPool.execute(new Runnable() {
                // @Override
                public void run() {

                    while (true) {
                        //Reading the message from the client
                        try {
                            socket = serverSocket.accept();
                            System.out.println("Server is connected" + port);
                            InputStream is = socket.getInputStream();
                            InputStreamReader isr = new InputStreamReader(is);
                            BufferedReader br = new BufferedReader(isr);
                            String message = br.readLine();
                            System.out.println("Serveris connected : " + message);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}