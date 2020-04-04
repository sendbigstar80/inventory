package com.logistics.cloud.annotation;


import com.logistics.cloud.eunm.OperationLogRank;
import com.logistics.cloud.eunm.OperationStatus;
import com.logistics.cloud.eunm.OperationType;

import java.lang.annotation.*;

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface OperationLogDetail {

    /**
     * 方法描述
     */
    String detail() default "";


    /**
     * 操作类型
     */
    OperationType operationType() default OperationType.UNKNOWN;

    /**
     * 日志等级
     */
    OperationLogRank operationLogRank() default OperationLogRank.INFO;

    /**
     * 操作人身份
     */
    OperationStatus operationStatus() default OperationStatus.USER;
}
