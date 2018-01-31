package com.newrelic.codingchallenge;

import org.apache.commons.lang3.RandomStringUtils;

import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author : anudeep on 1/29/18
 * @project : Application_Server
 */
public class Client implements Runnable {
    InetAddress inetAddress;
    Socket socket;
    int id;
    int count = 0;
    DataOutputStream dataOutputStream;

    String host;
    int port;

    public Client(int id, String host, int port) {
        this.id = id;
        this.host = host;
        this.port = port;
    }

    public void run() {
        try {
            inetAddress = InetAddress.getByName(host);
            socket = new Socket(inetAddress, port);
            System.out.println(id + " : " + socket);
            dataOutputStream = new DataOutputStream((socket.getOutputStream()));

            while (true) {
                dataOutputStream.writeUTF(RandomStringUtils.randomNumeric(9));
                //   dataOutputStream.writeUTF(String.format("%n"));
                if (count == 100000) {
                    count = 0;
                    Thread.sleep(1);
                }
                count++;
            }
        } catch (UnknownHostException e) {
            close_Connection();
            e.printStackTrace();
        } catch (IOException e) {
            close_Connection();
            e.printStackTrace();
        } catch (InterruptedException e) {
            close_Connection();
            e.printStackTrace();
        }
    }

    public void close_Connection() {
        try {
            dataOutputStream.flush();
            socket.close();
            dataOutputStream.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
