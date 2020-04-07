package com.logistics.cloud.service;


import com.logistics.cloud.model.user.UserModel;
import com.logistics.cloud.response.JsonResponse;

public interface UserService {

    JsonResponse selectUserByNameAndPwd(UserModel userModel);
}
