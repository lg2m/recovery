package com.lg2m.recovery.webservice;

import org.springframework.stereotype.Service;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
@Service
public class HomeWebService {

    public HomeWebService() {
    }

    @WebMethod
    public String welcome(String name) {
        System.out.println("Welcome " + name);
        return "Welcome " + name;
    }
}
