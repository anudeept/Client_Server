package com.newrelic.codingchallenge;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author : anudeep on 1/31/18
 * @project : Application_Server
 */
public class ClientMain {
    public static void main(String[] args) {
        Properties props = new Properties();
        try (InputStream resourceStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties")) {
            props.load(resourceStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String host = props.getProperty("Host");
        int port = Integer.parseInt(props.getProperty("Port"));
        //Thread Pool
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        IntStream.range(1, 6).forEach(
                count -> executorService.execute(new Client(count,host,port))
        );

    }
}
