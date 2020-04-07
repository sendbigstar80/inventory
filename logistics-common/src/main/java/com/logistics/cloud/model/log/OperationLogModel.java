package com.logistics.cloud.model.log;

import lombok.Data;
import java.io.Serializable;

@Data
public class OperationLogModel implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 日志id
     */
    private Long id;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 日志等级
     */
    private String logLevel;

    /**
     * 方法名
     */
    private String method;

    /**
     * 参数
     */
    private String args;

    /**
     * 操作人id
     */
    private Long userId;

    /**
     * 操作人
     */
    private String userName;

    /**
     * 日志描述
     */
    private String logDescribe;

    /**
     * 操作类型
     */
    private String operationType;

    /**
     * 方法运行时间
     */
    private Long runTime;

    /**
     * 返回值
     */
    private String returnValue;

    /**
     * 客户端请求地址
     */
    private String requestUrl;

    /**
     * 客户端请求ip
     */
    private String requestIp;

    /**
     * 客户端端口
     */
    private String requestPort;

    /**
     * 网络协议
     */
    private String requestScheme;

    /**
     * 线程id
     */
    private String traceId;

    /**
     * 操作人身份
     */
    private String operationStatus;

}
