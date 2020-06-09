package com.logistics.cloud.vo.ums;


import lombok.Data;

import java.time.LocalDateTime;

/**
 * @program logistics-inventory-cloud
 * @description: 新增用户对象
 * @author: wenxi
 * @create: 2020/06/08 21:36
 */
@Data
public class UmsUserVo {


    private static final long serialVersionUID = 1L;

    private Long id;

    private String username;

    private String password;

    /**
     * 头像
     */
    private String icon;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 备注信息
     */
    private String note;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 最后登录时间
     */
    private LocalDateTime loginTime;

    /**
     * 帐号启用状态：0->禁用；1->启用
     */
    private Integer status;
}
