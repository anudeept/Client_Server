package com.newrelic.codingchallenge;

import com.anarsoft.vmlens.concurrent.junit.ConcurrentTestRunner;
import com.newrelic.codingchallenge.server.DataStore;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * @author : anudeep on 1/30/18
 * @project : Application_Server
 */
@RunWith(ConcurrentTestRunner.class)
public class Data_Store_Test {


    @Before
    public void setup(){
        DataStore.reportDTO.getUnique().set(0);
        DataStore.reportDTO.getDuplicate().set(0);
        DataStore.reportDTO.getTotalUnique().set(0);
        DataStore.dataSet.clear();
    }
    @Test
    public void addUniqueValue() {
        DataStore.reportDTO.getUnique().addAndGet(20);
    }

    @Test
    public void addDuplicateValue() {
        DataStore.reportDTO.getDuplicate().addAndGet(30);
    }

    @Test
    public void addTotalUniqueValue() {
        DataStore.reportDTO.getTotalUnique().addAndGet(40);
    }

    @Test
    public void addDataToHash() {
        DataStore.dataSet.add("data");

    }

    @After
    public void testData() {
        Assert.assertEquals(DataStore.reportDTO.getUnique().get(), 80);
        Assert.assertEquals(DataStore.reportDTO.getDuplicate().get(), 120);
        Assert.assertEquals(DataStore.reportDTO.getTotalUnique().get(), 160);
        //System.out.println(DataStore.dataSet);
        Assert.assertEquals(DataStore.dataSet.size(), 1);
    }
}
