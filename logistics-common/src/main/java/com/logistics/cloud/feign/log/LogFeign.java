package com.logistics.cloud.feign.log;

import com.logistics.cloud.aspect.model.OperationLogModel;
import com.logistics.cloud.response.JsonResponse;
import com.logistics.cloud.route.ServerAddress;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = ServerAddress.LOG_SERVER)
public interface LogFeign {

    @PostMapping("/log/insert/object")
    JsonResponse insertObject(@RequestBody OperationLogModel model);
}
