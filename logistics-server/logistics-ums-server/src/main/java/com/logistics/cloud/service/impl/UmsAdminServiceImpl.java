package com.logistics.cloud.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.logistics.cloud.domain.UmsAdmin;
import com.logistics.cloud.dto.UserCompetence;
import com.logistics.cloud.eunm.ResultCode;
import com.logistics.cloud.exception.BusinessException;
import com.logistics.cloud.mapper.UmsAdminMapper;
import com.logistics.cloud.response.JsonResponse;
import com.logistics.cloud.service.IUmsAdminService;
import com.logistics.cloud.vo.ums.UmsUserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 后台用户表 服务实现类
 * </p>
 *
 * @author zhangwx
 * @since 2020-06-05
 */
@Service
public class UmsAdminServiceImpl extends ServiceImpl<UmsAdminMapper, UmsAdmin> implements IUmsAdminService {

    @Autowired
    private UmsAdminMapper umsAdminMapper;

    @Override
    public JsonResponse adminLogin(UmsAdmin umsAdmin){
        if (Objects.isNull(umsAdmin)){
            throw new BusinessException(ResultCode.PARAM_IS_INVALID);
        }
        if (StringUtils.isEmpty(umsAdmin.getUsername()) || StringUtils.isEmpty(umsAdmin.getPassword())){
            throw new BusinessException(ResultCode.USER_LOGIN_FAIL);
        }
        QueryWrapper<UmsAdmin> wrapper = new QueryWrapper<>(umsAdmin);
        UmsAdmin admin = this.getOne(wrapper);
        if (Objects.isNull(admin)){
            throw new BusinessException(ResultCode.USER_LOGIN_FAIL);
        }
        return JsonResponse.success(admin);
    }


    @Override
    public JsonResponse createUser(UmsUserVo user) {
        //参数校验
        if (Objects.isNull(user)){
            throw new BusinessException(ResultCode.PARAM_IS_INVALID);
        }
        if (StringUtils.isEmpty(user.getUsername()) || StringUtils.isEmpty(user.getPassword())){
            throw new BusinessException(ResultCode.USER_CREATE_PARAMS_NULL);
        }
        if (StringUtils.isEmpty(user.getNickName())){
            throw new BusinessException(ResultCode.USER_NICK_NAME_NOT_NULL);
        }
        //查询用户名是否存在
        JsonResponse<List<UmsAdmin>> jsonResponse = this.usmAdminByUserName(user.getUsername());
        if (Objects.isNull(jsonResponse) || Objects.equals(jsonResponse.getCode(),ResultCode.FAIL.code())){
            throw new BusinessException(ResultCode.QUERY_RESULt_EXCEPtION);
        }
        List<UmsAdmin> responseData = jsonResponse.getData();
        if (!CollectionUtils.isEmpty(responseData)){
            throw new BusinessException(ResultCode.USER_HAS_EXIST);
        }
        //创建用户实体
        UmsAdmin umsAdmin = new UmsAdmin();
        BeanUtils.copyProperties(user,umsAdmin);
        boolean save = this.save(umsAdmin);
        if (!save){
            return JsonResponse.fail(ResultCode.FAIL);
        }
        return JsonResponse.success();
    }

    @Override
    public JsonResponse<List<UmsAdmin>> usmAdminByUserName(String username) {
        if (StringUtils.isEmpty(username)){
            throw new BusinessException(ResultCode.PARAM_IS_INVALID);
        }
        UmsAdmin user = new UmsAdmin();
        user.setUsername(username);
        QueryWrapper<UmsAdmin> wrapper = new QueryWrapper<>();
        wrapper.setEntity(user);
        List<UmsAdmin> list = this.list(wrapper);
        return JsonResponse.success(list);
    }

    @Override
    public JsonResponse updateUmsAdmin(UmsUserVo user) {
        //参数校验
        if (Objects.isNull(user)){
            throw new BusinessException(ResultCode.PARAM_IS_INVALID);
        }
        if (StringUtils.isEmpty(user.getUsername()) || StringUtils.isEmpty(user.getPassword())){
            throw new BusinessException(ResultCode.PARAM_IS_INVALID);
        }
        UmsAdmin param = new UmsAdmin();
        BeanUtils.copyProperties(user,param);
        UpdateWrapper<UmsAdmin> updateWrapper = new UpdateWrapper<>();
        updateWrapper.setEntity(param);
        boolean flag = this.update(updateWrapper);
        if (!flag){
            return JsonResponse.fail(ResultCode.FAIL);
        }
        return JsonResponse.success();
    }

    @Override
    public JsonResponse deleteUserByUserName(String username) {
        if (StringUtils.isEmpty(username)){
            throw new BusinessException(ResultCode.PARAM_IS_INVALID);
        }
        UmsAdmin user = new UmsAdmin();
        user.setUsername(username);
        QueryWrapper<UmsAdmin> wrapper = new QueryWrapper<>();
        boolean remove = this.remove(wrapper);
        if (!remove){
            return JsonResponse.fail(ResultCode.FAIL);
        }
        return JsonResponse.success();
    }

    @Override
    public JsonResponse<Page<UmsAdmin>> queryAllUser(long current, long size) {
        Page<UmsAdmin> page = new Page<>();
        page.setCurrent(current);
        page.setSize(size);
        Page<UmsAdmin> umsAdminPage = umsAdminMapper.selectPage(page, null);
        return JsonResponse.success(umsAdminPage);
    }

    @Override
    public JsonResponse<UserCompetence> loadUserByUsername(String username) {
        if (StringUtils.isEmpty(username)){
            throw new BusinessException(ResultCode.PARAM_IS_INVALID);
        }
        UserCompetence userCompetence = umsAdminMapper.loadUserByUsername(username);
        return JsonResponse.success(userCompetence);
    }
}
