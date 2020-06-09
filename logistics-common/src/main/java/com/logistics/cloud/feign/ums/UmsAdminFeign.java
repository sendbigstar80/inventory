package com.logistics.cloud.feign.ums;

import com.logistics.cloud.domain.UmsAdmin;
import com.logistics.cloud.dto.UserCompetence;
import com.logistics.cloud.response.JsonResponse;
import com.logistics.cloud.vo.ums.UmsUserVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @program logistics-inventory-cloud
 * @description:
 * @author: wenxi
 * @create: 2020/06/08 23:10
 */
@FeignClient(name = "logistics-ums-server")
public interface UmsAdminFeign {

    @PostMapping(value = "/ums/createUser")
    public JsonResponse createUser(@RequestBody UmsUserVo user);

    @GetMapping(value = "/ums/users/{username}")
    public JsonResponse<List<UmsAdmin>> usmAdminByUserName(@PathVariable("username") String username);

    @PutMapping(value = "/usm/user")
    public JsonResponse updateUmsAdmin(@RequestBody UmsUserVo umsUserVo);

    @DeleteMapping(value = "/usm/username/user")
    public JsonResponse deleteUserByUserName(@RequestParam("username") String username);

    @GetMapping(value = "/v1/ums/roles/permissions/{username}")
    public JsonResponse<UserCompetence> loadUserByUsername(@PathVariable("username") String username);
}
