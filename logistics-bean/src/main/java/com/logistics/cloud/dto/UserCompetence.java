package com.logistics.cloud.dto;

import com.logistics.cloud.domain.UmsPermission;
import com.logistics.cloud.domain.UmsRole;
import lombok.Data;

import java.util.List;

/**
 * @program logistics-inventory-cloud
 * @description:
 * @author: wenxi
 * @create: 2020/06/08 23:45
 */
@Data
public class UserCompetence {

    private long id;
    private String username;
    private String password;
    private List<UmsRole> roleList;
    private List<UmsPermission> permissionList;
}
