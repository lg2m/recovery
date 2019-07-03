package com.lg2m.recovery.api;

public class HomeController {
    public String home(String name) {
        System.out.println("Welcome " + name);
        return "Welcome" + name;
    }
}
