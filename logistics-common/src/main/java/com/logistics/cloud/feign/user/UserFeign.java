package com.logistics.cloud.feign.user;

import com.logistics.cloud.route.ServerAddress;
import com.logistics.cloud.route.UserServerRoute;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = ServerAddress.USER_SERVER)
public interface UserFeign {

    @PostMapping(value = "/user/query/object")
    String queryUser();

}
