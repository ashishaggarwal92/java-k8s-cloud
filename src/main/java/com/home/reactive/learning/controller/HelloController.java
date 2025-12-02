package com.home.reactive.learning.controller;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private final ElasticsearchClient client;

    public HelloController(ElasticsearchClient client) {
        this.client = client;
    }


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

    @GetMapping("/health")
    public String health() throws Exception {
        return client.cluster().health().status().jsonValue();
    }
}