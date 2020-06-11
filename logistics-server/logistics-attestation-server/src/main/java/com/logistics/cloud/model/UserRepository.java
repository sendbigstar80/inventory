package com.logistics.cloud.model;

import com.alibaba.fastjson.JSON;
import com.logistics.cloud.domain.UmsRole;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @program logistics-inventory-cloud
 * @description: 重写Security 的UserDetais
 * @author: wenxi
 * @create: 2020/06/11 22:20
 */
@Data
@Slf4j
public class UserRepository implements UserDetails {
    private String username;
    private String password;
    private List<UmsRole> umsRoles;
    private String accountStatus;
    private final String prefix = "ROLE_";
    private final String status = "1";

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        List<String> authoritiesNames = umsRoles.stream().map(UmsRole::getName).collect(Collectors.toList());
        log.info(JSON.toJSONString(authoritiesNames));
        authoritiesNames.forEach(name -> {
            if (!StringUtils.isEmpty(name)){
                GrantedAuthority authority = new SimpleGrantedAuthority(prefix+name.toUpperCase());
                authorities.add(authority);
            }
        });
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
