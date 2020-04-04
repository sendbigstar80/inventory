package com.logistics.cloud.rest;

import com.logistics.cloud.annotation.OperationLogDetail;
import com.logistics.cloud.eunm.OperationLogRank;
import com.logistics.cloud.eunm.OperationStatus;
import com.logistics.cloud.eunm.OperationType;
import com.logistics.cloud.feign.user.UserFeign;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/user")
@Slf4j
public class UserWebController {

    @Resource
    private UserFeign userFeign;

    @PostMapping(value = "/query/object")
    @OperationLogDetail(detail = "查询用户信息",
            operationType = OperationType.SELECT,
            operationLogRank = OperationLogRank.FATAL,
            operationStatus = OperationStatus.ADMIN
    )
    public String queryUser() {
        log.info("logistics-application: " + MDC.get("X-B3-TraceId"));
//        return userFeign.queryUser();
        return MDC.get("X-B3-TraceId");
    }
}
