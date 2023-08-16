package com.logistics.cloud.rest;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.logistics.cloud.domain.UmsAdmin;
import com.logistics.cloud.dto.UserCompetence;
import com.logistics.cloud.response.JsonResponse;
import com.logistics.cloud.service.IUmsAdminService;
import com.logistics.cloud.vo.ums.UmsUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 后台用户表 前端控制器
 * </p>
 *
 * @author zhangwx
 * @since 2020-06-05
 */
@RestController
@RequestMapping
public class UmsAdminController {

//    @Autowired
    @Resource
    @Qualifier
    private IUmsAdminService iUmsAdminService;

    @PostMapping(value = "/ums/adminLogin")
    public JsonResponse adminLogin(@RequestBody UmsAdmin umsAdmin) {
        return iUmsAdminService.adminLogin(umsAdmin);
    }

    @PostMapping(value = "/ums/createUser")
    public JsonResponse createUser(@RequestBody UmsUserVo user) {
        return iUmsAdminService.createUser(user);
    }

    @GetMapping(value = "/ums/users/{username}")
    public JsonResponse<List<UmsAdmin>> usmAdminByUserName(@PathVariable("username") String username) {
        return iUmsAdminService.usmAdminByUserName(username);
    }

    @PutMapping(value = "/usm/user")
    public JsonResponse updateUmsAdmin(@RequestBody UmsUserVo umsUserVo) {
        return iUmsAdminService.updateUmsAdmin(umsUserVo);
    }

    @DeleteMapping(value = "/usm/username/user")
    public JsonResponse deleteUserByUserName(@RequestParam("username") String username) {
        return iUmsAdminService.deleteUserByUserName(username);
    }

    @GetMapping(value = "/ums/users")
    public JsonResponse<Page<UmsAdmin>> queryAllUser(@RequestParam("current") long current, @RequestParam("size") long size) {
        return iUmsAdminService.queryAllUser(current, size);
    }

    @GetMapping(value = "/v1/ums/roles/permissions/{username}")
    public JsonResponse<UserCompetence> loadUserByUsername(@PathVariable("username") String username){
        return iUmsAdminService.loadUserByUsername(username);
    }


}
