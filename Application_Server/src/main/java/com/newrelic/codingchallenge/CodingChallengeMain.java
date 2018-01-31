package com.newrelic.codingchallenge;

import com.newrelic.codingchallenge.server.Report_Thread;
import com.newrelic.codingchallenge.server.Server_Thread;
import org.apache.log4j.Logger;
import org.apache.log4j.RollingFileAppender;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author : anudeep on 1/30/18
 * @project : Application_Server
 */
public class CodingChallengeMain {
    public static void main(String[] args) {
        try {
            Properties props = new Properties();
            try (InputStream resourceStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties")) {
                props.load(resourceStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
            int port = Integer.parseInt(props.getProperty("Port"));
            //Clear previous logs on restart
            RollingFileAppender fileAppender = (RollingFileAppender) Logger.getRootLogger().getAppender("file");
            fileAppender.rollOver();
            //Initialise Server and Report Thread
            Server_Thread serverThread = new Server_Thread(port);
            Report_Thread reportThread = new Report_Thread();

            //Start Server and Report Threads
            serverThread.start();
            reportThread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
