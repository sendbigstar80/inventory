package com.logistics.cloud.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.logistics.cloud.mapper.InterfaceLogMapper;
import com.logistics.cloud.service.IInterfaceLogService;
import com.logistics.cloud.domain.InterfaceLog;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhangwx
 * @since 2020-04-03
 */
@Service
public class InterfaceLogServiceImpl extends ServiceImpl<InterfaceLogMapper, InterfaceLog> implements IInterfaceLogService {

}
