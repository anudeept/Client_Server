package com.newrelic.codingchallenge;

import com.newrelic.codingchallenge.server.Report_Thread;
import com.newrelic.codingchallenge.server.Server_Thread;
import org.apache.log4j.Logger;
import org.apache.log4j.RollingFileAppender;

import java.io.IOException;
/**
 * @author : anudeep on 1/30/18
 * @project : Application_Server
 */
public class CodingChallengeMain {
    public static void main(String[] args) {
        try {
            //Clear previous logs on restart
            RollingFileAppender fileAppender = (RollingFileAppender) Logger.getRootLogger().getAppender("file");
            fileAppender.rollOver();
            //Initialise Server and Report Thread
            Server_Thread serverThread = new Server_Thread();
            Report_Thread reportThread = new Report_Thread();

            //Start Server and Report Threads
            serverThread.start();
            reportThread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
