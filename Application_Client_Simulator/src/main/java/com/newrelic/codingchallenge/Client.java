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

    public Client(int id) {
        this.id = id;
    }

    public void run() {
        try {
            inetAddress = InetAddress.getByName("localhost");
            socket = new Socket(inetAddress, 4000);
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
