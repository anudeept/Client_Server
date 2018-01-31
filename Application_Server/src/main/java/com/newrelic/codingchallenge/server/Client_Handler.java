package com.newrelic.codingchallenge.server;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.Socket;

/**
 * @author : anudeep on 1/30/18
 * @project : Application_Server
 */
public class Client_Handler implements Runnable {
    Socket socket;
    DataInputStream dataInputStream;
    final static Logger data_File = Logger.getLogger(Client_Handler.class);

    public Client_Handler() {
    }

    public Client_Handler(Socket socket, DataInputStream dataInputStream) {
        this.socket = socket;
        this.dataInputStream = dataInputStream;
    }

    /**
     * Receive client data and store de-duplicated data in log file.
     */
    public void run() {
        try {
            String client_data = "";
            while (!client_data.equals("terminate")) {
                //Read Client data
                client_data = String.valueOf(dataInputStream.readUTF());
              //  System.out.println(client_data);
                //Validate if data is number and length < =9
                if (isDataValide(client_data)) {
                    //Add leading zero'socket if length < 9
                    client_data = append_Zeros(client_data);
                    //Check if number present in Hashmap or not
                    dataUpdation(client_data);
                } else {
                    DataStore.exit = true;
                    break;
                }
            }
            close_Connections();
        } catch (EOFException e) {
            close_Connections();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * isDataValide - Validate if data from client is number are not
     *
     * @param data
     * @return
     */
    private boolean isDataValide(String data) {
        return (StringUtils.isNumeric(data) && data.length() <= 9);
    }

    /**
     * append_Zeros -Append Zero's
     *
     * @param data
     * @return
     */
    String append_Zeros(String data) {
        if (data.length() < 9) {
            data = StringUtils.rightPad(data, 9, "0");
        }
        return data;
    }

    boolean dataUpdation(String data) {
        try {
            if (!DataStore.dataSet.contains(data)) {
                DataStore.dataSet.add(data);
                data_File.info(data);
                DataStore.reportDTO.incUnique();
            } else {
                DataStore.reportDTO.incDuplice();
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * Close Connections
     */
    public void close_Connections() {
        try {
            dataInputStream.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
