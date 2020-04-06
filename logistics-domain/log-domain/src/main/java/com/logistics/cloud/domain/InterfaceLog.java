package com.logistics.cloud.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhangwx
 * @since 2020-04-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("interface_log")
public class InterfaceLog implements Serializable {

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
