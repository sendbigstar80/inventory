package com.logistics.cloud.rest;

import com.logistics.cloud.feign.ums.UmsAdminFeign;
import com.logistics.cloud.response.JsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program logistics-inventory-cloud
 * @description:
 * @author: wenxi
 * @create: 2020/06/09 22:34
 */
@RequestMapping
@RestController
public class TestController {

    @Autowired
    private UmsAdminFeign umsAdminFeign;

    @GetMapping(value = "/v1/user/{username}")
    public JsonResponse testController(@PathVariable("username") String username){
        return umsAdminFeign.usmAdminByUserName(username);
    }
}
