package com.home.reactive.learning.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {


    @GetMapping("/hello")
    public String hello() {
        return "Hello from Spring Boot 3.4.3!";
    }


    @GetMapping("/hello/{name}")
    public String helloName(@PathVariable String name) {
        return "Hello, " + name + "!";
    }


    @GetMapping("/status")
    public String status() {
        return "Application is running fine!";
    }
}