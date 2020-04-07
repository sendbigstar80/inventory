package com.logistics.cloud.service.impl;

import com.logistics.cloud.model.user.UserModel;
import com.logistics.cloud.response.JsonResponse;
import com.logistics.cloud.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServerImpl implements UserService {
    @Override
    public JsonResponse selectUserByNameAndPwd(UserModel userModel) {
        return JsonResponse.success(userModel);
    }
}
