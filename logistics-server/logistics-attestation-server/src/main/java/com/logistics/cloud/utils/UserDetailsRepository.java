package com.logistics.cloud.utils;

import com.logistics.cloud.domain.UmsAdmin;
import com.logistics.cloud.domain.UmsPermission;
import com.logistics.cloud.domain.UmsRole;
import com.logistics.cloud.dto.UserCompetence;
import com.logistics.cloud.eunm.ResultCode;
import com.logistics.cloud.exception.BusinessException;
import com.logistics.cloud.feign.ums.UmsAdminFeign;
import com.logistics.cloud.response.JsonResponse;
import com.logistics.cloud.vo.ums.UmsUserVo;
import org.apache.catalina.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @program logistics-inventory-cloud
 * @description: 自定义UserDatsilsMapper
 * @author: wenxi
 * @create: 2020/06/08 21:24
 */
public class UserDetailsRepository {

    @Autowired
    private UmsAdminFeign umsAdminFeign;


    public void createUser(UserDetails user) {
        UmsUserVo param = new UmsUserVo();
        param.setUsername(user.getUsername());
        param.setPassword(user.getPassword());
        JsonResponse response = umsAdminFeign.createUser(param);
        if (Objects.isNull(response) || Objects.equals(response.getCode(), ResultCode.FAIL.code())){
            throw new BusinessException(ResultCode.SYSTEM_ERROR);
        }
    }


    public void updateUser(UserDetails user) {
        UmsUserVo param = new UmsUserVo();
        param.setUsername(user.getUsername());
        param.setPassword(user.getPassword());
        JsonResponse response = umsAdminFeign.createUser(param);
        if (Objects.isNull(response) || Objects.equals(response.getCode(), ResultCode.FAIL.code())){
            throw new BusinessException(ResultCode.SYSTEM_ERROR);
        }
    }


    public void deleteUser(String username) {
        JsonResponse response = umsAdminFeign.deleteUserByUserName(username);
        if (Objects.isNull(response) || Objects.equals(response.getCode(), ResultCode.FAIL.code())){
            throw new BusinessException(ResultCode.SYSTEM_ERROR);
        }
    }


    public void changePassword(String oldPassword, String newPassword) {
        Authentication currentUser = SecurityContextHolder.getContext()
                .getAuthentication();

        if (currentUser == null) {
            // This would indicate bad coding somewhere
            throw new AccessDeniedException(
                    "Can't change password as no Authentication object found in context "
                            + "for current user.");
        }

        String username = currentUser.getName();

//        UserDetails user = users.get(username);
//
//
//        if (user == null) {
//            throw new IllegalStateException("Current user doesn't exist in database.");
//        }

        // todo copy InMemoryUserDetailsManager  自行实现具体的更新密码逻辑
    }


    public boolean userExists(String username) {
        JsonResponse<List<UmsAdmin>> response = umsAdminFeign.usmAdminByUserName(username);
        if (Objects.isNull(response) || Objects.equals(response.getCode(), ResultCode.FAIL.code())){
            throw new BusinessException(ResultCode.SYSTEM_ERROR);
        }
        if (CollectionUtils.isEmpty(response.getData())){
            return false;
        }
        return true;
    }


    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        JsonResponse<UserCompetence> response = umsAdminFeign.loadUserByUsername(username);
        if (Objects.isNull(response) || Objects.equals(response.getCode(),ResultCode.FAIL.code())){
            return null;
        }
        UserCompetence userCompetence = response.getData();
        if (Objects.isNull(userCompetence)){
            return null;
        }
        //角色
        List<UmsRole> roleList = userCompetence.getRoleList();
        String[] roles = new String[roleList.size()];
        if (!CollectionUtils.isEmpty(roleList)){
            roleList.stream().map(UmsRole::getName).collect(Collectors.toList()).toArray(roles);
        }
        //权限
        List<UmsPermission> permissionList = userCompetence.getPermissionList();
        String[] permissions = new String[permissionList.size()];
        if (!CollectionUtils.isEmpty(permissionList)){
            permissionList.stream().map(UmsPermission::getName).collect(Collectors.toList()).toArray(permissions);
        }
        UserDetails build = User.withUsername(userCompetence.getUsername())
                .password(userCompetence.getPassword())
                .roles(roles)
                .authorities(permissions)
                .build();
        return build;
    }

}
