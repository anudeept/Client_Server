package com.newrelic.codingchallenge.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author : anudeep on 1/30/18
 * @project : Application_Server
 */
public class Server_Thread extends Thread {

    private ServerSocket serverSocket;
    private ExecutorService pool;
    private boolean accept_Client = true;
    private Socket socket = null;

    public Server_Thread() throws IOException {
        this.serverSocket = new ServerSocket(4000);
        pool = Executors.newFixedThreadPool(5);

    }

    @Override
    public void run() {
        while (true) {
            //Check for terminate signal.
            if (DataStore.exit) {
                accept_Client = false;
                shutdown();
            }
            //accept client if there is no termination signal
            if (accept_Client) {
                try {
                    socket = serverSocket.accept();
                    DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
                    //Create a new Client Thread
                    Client_Handler clientHandler = new Client_Handler(socket, dataInputStream);
                    pool.execute(clientHandler);
                } catch (Exception e) {
                    close_Connnection();
                    e.printStackTrace();
                }
            } else {
                break;
            }
        }
    }

    /**
     * Close Socket.
     */
    public void close_Connnection() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shutdown Thread executor on terminate signal
     */
    public void shutdown() {
        pool.shutdown();
    }
}
