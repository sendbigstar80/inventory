package com.logistics.cloud.rest;

import com.logistics.cloud.route.UserServerRoute;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "user")
@RestController
@Slf4j
public class UserController {

    @PostMapping(value = "/query/object")
    public String queryUser(){
        log.info("logistics-user-server: " + MDC.get("X-B3-TraceId"));
        return "admin";
    }
}
