package com.logistics.cloud.rest;

import com.logistics.cloud.annotation.OperationLogDetail;
import com.logistics.cloud.eunm.OperationLogRank;
import com.logistics.cloud.eunm.OperationStatus;
import com.logistics.cloud.eunm.OperationType;
import com.logistics.cloud.eunm.ResultCode;
import com.logistics.cloud.exception.BusinessException;
import com.logistics.cloud.feign.user.UserFeign;
import com.logistics.cloud.model.user.UserModel;
import com.logistics.cloud.response.JsonResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    public JsonResponse selectUserByNameAndPwd(@RequestBody @Validated UserModel userModel) {
        if (userModel.getUserName().equals("admin")){
            throw new BusinessException(ResultCode.USER_LOGIN_FAIL);
        }
        return userFeign.selectUserByNameAndPwd(userModel);
    }
}
