package com.lg2m.recovery.webservice;

import org.apache.axis.AxisFault;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.junit.Before;
import org.junit.Test;

import java.net.URL;

public class HomeWebServiceTest {

    private Call client;

    @Before
    public void setUp() throws Exception {
        String endpoint = "http://localhost:8080/services";
        Service service = new Service();
        client = (Call) service.createCall();
        client.setTargetEndpointAddress(new URL(endpoint));
    }

    @Test
    public void welcome() throws AxisFault {
        Object result = client.invoke("home", "welcome", new Object[]{"tom"});
        System.out.println(result);
    }
}