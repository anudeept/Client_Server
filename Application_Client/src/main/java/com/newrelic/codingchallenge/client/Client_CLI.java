package com.newrelic.codingchallenge.client;

import java.io.*;
import java.net.*;
import java.util.Properties;
import java.util.Scanner;

/**
 * @author : anudeep on 1/29/18
 * @project : Application_Server
 */
public class Client_CLI implements Runnable {
    InetAddress inetAddress;
    Socket socket;
    int id;
    Properties props;
    DataOutputStream dataOutputStream;
    String host;
    int port;

    public Client_CLI(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void run() {
        try {
            inetAddress = InetAddress.getByName(host);
            socket = new Socket(inetAddress, port);
            Scanner sc = new Scanner(System.in);
            System.out.println(Thread.currentThread().getName() + id + " : " + socket);
            dataOutputStream = new DataOutputStream((socket.getOutputStream()));

            while (true) {
                String send = sc.nextLine();
                dataOutputStream.writeUTF(send);
                if (send.equals("terminate") || send.equals("exit")) {
                    close();
                    break;
                }
            }
            close();
        } catch (UnknownHostException e) {
            close();
        } catch (ConnectException e) {
            close();
        } catch (SocketException e) {
            close();
        } catch (EOFException e) {
            close();
        } catch (IOException e) {
            close();
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            dataOutputStream.flush();
            socket.close();
            dataOutputStream.close();
        } catch (IOException e) {
            System.out.println("Lost Connection");
        }
    }

}
