package com.logistics.cloud.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.logistics.cloud.domain.UmsAdmin;
import com.logistics.cloud.dto.UserCompetence;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 后台用户表 Mapper 接口
 * </p>
 *
 * @author zhangwx
 * @since 2020-06-05
 */
public interface UmsAdminMapper extends BaseMapper<UmsAdmin> {

    /**
     * 通过用户名，查询角色和权限列表
     * @param username
     * @return
     */
    UserCompetence loadUserByUsername(@Param("username") String username);

}
