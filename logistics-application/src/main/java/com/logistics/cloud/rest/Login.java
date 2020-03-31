package com.logistics.cloud.rest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/login")
public class Login {

    @PostMapping(value = "/test1")
    public String test1(){
        return "admin";
    }
}
