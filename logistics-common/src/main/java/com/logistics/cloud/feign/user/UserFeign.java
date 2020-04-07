package com.logistics.cloud.feign.user;

import com.logistics.cloud.model.user.UserModel;
import com.logistics.cloud.response.JsonResponse;
import com.logistics.cloud.route.ServerAddress;
import com.logistics.cloud.route.ServerRoute;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = ServerAddress.USER_SERVER)
public interface UserFeign {

    @PostMapping(value = ServerRoute.USER_SELECT_USER_BY_NAME_AND_PWD)
    JsonResponse selectUserByNameAndPwd(@RequestBody @Validated UserModel userModel);

}
