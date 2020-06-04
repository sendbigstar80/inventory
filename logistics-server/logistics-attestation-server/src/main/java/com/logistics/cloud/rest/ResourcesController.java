package com.logistics.cloud.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program logistics-inventory-cloud
 * @description: 测试资源接口
 * @author: wenxi
 * @create: 2020/06/02 22:39
 */
@RestController
@RequestMapping
public class ResourcesController {

    @GetMapping(value = "/LoginSuccess")
    public String LoginSuccess(){
        return "登陆成功";
    }

    @GetMapping(value = "/r/p1")
    public String testResourcesOne(){
        return "资源1";
    }

    @GetMapping(value = "/r/p2")
    public String testResourcesTwo(){
        return "资源2";
    }
}
