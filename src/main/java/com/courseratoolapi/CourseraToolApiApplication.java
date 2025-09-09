package com.courseratoolapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.web.bind.annotation.GetMapping;
@SpringBootApplication
public class CourseraToolApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(CourseraToolApiApplication.class, args);
    }
    @GetMapping("/hello")
    public String hello() {
        return "Hello World from Spring + MySQL!";
    }
}
