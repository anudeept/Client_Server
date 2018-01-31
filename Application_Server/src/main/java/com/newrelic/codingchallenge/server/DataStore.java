package com.newrelic.codingchallenge.server;

import com.newrelic.codingchallenge.domain.ReportDTO;

import java.util.HashSet;

/**
 * @author : anudeep on 1/30/18
 * @project : Application_Server
 */
public class DataStore {
    public static boolean exit = false;
    public static HashSet<String> dataSet = new HashSet<>();
    public static ReportDTO reportDTO = new ReportDTO();
}
