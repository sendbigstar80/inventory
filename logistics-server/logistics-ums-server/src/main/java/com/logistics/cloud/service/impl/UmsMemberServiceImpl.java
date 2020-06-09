package com.logistics.cloud.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.logistics.cloud.domain.UmsMember;
import com.logistics.cloud.mapper.UmsMemberMapper;
import com.logistics.cloud.service.IUmsMemberService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author zhangwx
 * @since 2020-06-05
 */
@Service
public class UmsMemberServiceImpl extends ServiceImpl<UmsMemberMapper, UmsMember> implements IUmsMemberService {

}
