package com.newrelic.codingchallenge;

import com.newrelic.codingchallenge.server.Client_Handler;
import com.newrelic.codingchallenge.server.DataStore;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author : anudeep on 1/30/18
 * @project : Application_Server
 */
public class ClientHandler_Test {

    private Client_Handler clientHandler;
    Method isDataValideMethod = null;
    Method append_ZerosMethod = null;
    Method dataUpdationMethod = null;

    @Before
    public void setup() {
        clientHandler = new Client_Handler();
        try {
            isDataValideMethod = Client_Handler.class.getDeclaredMethod("isDataValide", String.class);
            isDataValideMethod.setAccessible(true);
            append_ZerosMethod = Client_Handler.class.getDeclaredMethod("append_Zeros", String.class);
            append_ZerosMethod.setAccessible(true);
            dataUpdationMethod = Client_Handler.class.getDeclaredMethod("dataUpdation", String.class);
            dataUpdationMethod.setAccessible(true);

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void isValide_Test() {
        boolean result = false;
        try {
            result = (boolean) isDataValideMethod.invoke(clientHandler, "123");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(true, result);

    }

    @Test
    public void isValide_Test_1() {
        boolean result_Negative = false;
        try {
            result_Negative = (boolean) isDataValideMethod.invoke(clientHandler, "abc");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(false, result_Negative);

    }

    @Test
    public void isValide_Test_2() {
        boolean result_Negative = false;
        try {
            result_Negative = (boolean) isDataValideMethod.invoke(clientHandler, "1231231231231213");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(false, result_Negative);
    }

    @Test
    public void append_Test() {
        String result = "";
        try {
            result = (String) append_ZerosMethod.invoke(clientHandler, "123");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        Assert.assertEquals("123000000", result);
    }

    @Test
    public void append_Test_1() {
        String result = "";
        try {
            result = (String) append_ZerosMethod.invoke(clientHandler, "123456789");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        Assert.assertEquals("123456789", result);
    }

    @Test
    public void dataUpdation_Test() {
        DataStore.dataSet.clear();
        DataStore.dataSet.add("123000000");
        DataStore.dataSet.add("987654321");
        try {
            dataUpdationMethod.invoke(clientHandler, "560000000");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(3,DataStore.dataSet.size());
    }
    @Test
    public void dataUpdation_Test_1() {
        DataStore.dataSet.clear();
        DataStore.dataSet.add("123000000");
        DataStore.dataSet.add("987654321");
        try {
            dataUpdationMethod.invoke(clientHandler, "123000000");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(2,DataStore.dataSet.size());
    }
}
