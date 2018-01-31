package com.newrelic.codingchallenge;

import com.newrelic.codingchallenge.client.Client_CLI;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author : anudeep on 1/29/18
 * @project : Application_Server
 */
public class ClientMain {

    public static void main(String[] args) {
        Thread clientThread= new Thread(new Client_CLI());
        clientThread.start();
    }
}
