package com.logistics.cloud.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.logistics.cloud.domain.UmsAdmin;
import com.logistics.cloud.dto.UserCompetence;
import com.logistics.cloud.response.JsonResponse;
import com.logistics.cloud.vo.ums.UmsUserVo;

import java.util.List;

/**
 * <p>
 * 后台用户表 服务类
 * </p>
 *
 * @author zhangwx
 * @since 2020-06-05
 */
public interface IUmsAdminService extends IService<UmsAdmin> {

    /**
     * 根据用户名密码查询用户信息
     * @param umsAdmin
     * @return 返回详细信息
     */
    JsonResponse<UmsAdmin> adminLogin(UmsAdmin umsAdmin);

    /**
     * 新增用户
     * @param user 用户信息
     * @return 返回结果
     */
    JsonResponse createUser(UmsUserVo user);

    /**
     * 通过用户名查询用户是否存在
     * @param username
     * @return
     */
    JsonResponse<List<UmsAdmin>> usmAdminByUserName(String username);

    /**
     * 更新用户信息
     * @param user 用户实体
     * @return 更新状态
     */
    JsonResponse updateUmsAdmin(UmsUserVo user);

    /**
     * 通过用户名删除用户信息
     * @param username 用户名
     * @return 删除状态
     */
    JsonResponse deleteUserByUserName(String username);


    /**
     * 分页查询所有用户信息
     * @param current 开始
     * @param size 结束
     * @return
     */
    JsonResponse<Page<UmsAdmin>> queryAllUser(long current,long size);

    /**
     * 通过用户名，查询角色和权限列表
     * @param username
     * @return
     */
    JsonResponse<UserCompetence> loadUserByUsername(String username);
}
