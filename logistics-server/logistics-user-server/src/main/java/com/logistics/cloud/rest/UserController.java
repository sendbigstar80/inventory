package com.logistics.cloud.rest;

import com.logistics.cloud.model.user.UserModel;
import com.logistics.cloud.response.JsonResponse;
import com.logistics.cloud.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RequestMapping
@RestController
@Slf4j
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping(value = "/user/selectUserByNameAndPwd")
    public JsonResponse selectUserByNameAndPwd(@RequestBody @Validated UserModel userModel){
        return userService.selectUserByNameAndPwd(userModel);
    }
}
