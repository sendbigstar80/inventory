package com.logistics.cloud.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program logistics-inventory-cloud
 * @description: 测试Controller
 * @author: wenxi
 * @create: 2020/07/02 22:34
 */
@RestController
public class TestController {


    @GetMapping(value = "/test/r1")
    public String testR1(){
        return "testR1";
    }

    @GetMapping(value = "/s")
    public String testS(){
        return "success";
    }
}
