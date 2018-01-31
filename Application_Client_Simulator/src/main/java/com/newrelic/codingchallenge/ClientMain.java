package com.newrelic.codingchallenge;

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
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        IntStream.range(1, 6).forEach(
                count -> executorService.execute(new Client(count))
        );

    }
}
