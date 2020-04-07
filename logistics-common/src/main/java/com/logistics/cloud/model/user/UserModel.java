package com.logistics.cloud.model.user;

import com.logistics.cloud.Validator.PhoneValidator;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserModel {

    @NotBlank(message = "用户名不能为空！")
    private String userName;

    @NotBlank(message = "密码不能为空！")
    private String password;


    @PhoneValidator
    private String phone;
}
