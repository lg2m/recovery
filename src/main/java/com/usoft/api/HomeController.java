package com.usoft.api;

public class HomeController {
    public String home(String name) {
        System.out.println("Welcome " + name);
        return "Welcome" + name;
    }
}
