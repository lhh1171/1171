package com.example.demo.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    //    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @GetMapping(value = "/hello")
    public String index() {
        return "Hello World";
    }
}