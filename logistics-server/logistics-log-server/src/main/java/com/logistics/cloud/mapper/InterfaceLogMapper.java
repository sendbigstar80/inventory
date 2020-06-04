package com.logistics.cloud.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.logistics.cloud.domain.InterfaceLog;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhangwx
 * @since 2020-04-03
 */
public interface InterfaceLogMapper extends BaseMapper<InterfaceLog> {

    List<InterfaceLog> selectAll();
}
