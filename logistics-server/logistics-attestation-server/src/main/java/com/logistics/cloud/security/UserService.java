package com.logistics.cloud.security;

import com.alibaba.fastjson.JSON;
import com.logistics.cloud.dto.UserCompetence;
import com.logistics.cloud.eunm.ResultCode;
import com.logistics.cloud.feign.ums.UmsAdminFeign;
import com.logistics.cloud.model.UserRepository;
import com.logistics.cloud.response.JsonResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @program logistics-inventory-cloud
 * @description: 重写UserDetailsService
 * @author: wenxi
 * @create: 2020/06/11 22:16
 */
@Slf4j
@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UmsAdminFeign umsAdminFeign;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (StringUtils.isBlank(username)){
            return null;
        }
        JsonResponse<UserCompetence> response = umsAdminFeign.loadUserByUsername(username);
        if (Objects.isNull(response) || Objects.equals(response.getCode(), ResultCode.FAIL.code())){
            return null;
        }
        UserCompetence userCompetence = response.getData();
        UserRepository user = new UserRepository();
        user.setUsername(userCompetence.getUsername());
        user.setPassword(userCompetence.getPassword());
        log.info(JSON.toJSONString(userCompetence.getRoleList()));
        user.setUmsRoles(userCompetence.getRoleList());
        return user;
    }
}
