package com.logistics.cloud.rest;

import com.logistics.cloud.route.UserServerRoute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "user")
@RestController
public class UserController {

    @PostMapping(value = UserServerRoute.QUERY_USER)
    public String queryUser(){
        return "admin";
    }
}
