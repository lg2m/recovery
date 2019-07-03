package com.lg2m.recovery.webservice;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class HomeWebService {

    public HomeWebService() {
    }

    @WebMethod
    public String welcome(String name) {
        System.out.println("Welcome " + name);
        return "Welcome " + name;
    }
}
