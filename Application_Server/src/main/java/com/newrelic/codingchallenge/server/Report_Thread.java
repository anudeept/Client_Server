package com.newrelic.codingchallenge.server;

/**
 * @author : anudeep on 1/30/18
 * @project : Application_Server
 */
public class Report_Thread extends Thread {

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(10000);
                System.out.println(DataStore.reportDTO);
                DataStore.reportDTO.flush();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
