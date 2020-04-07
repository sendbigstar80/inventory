package com.logistics.cloud.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.logistics.cloud.model.log.OperationLogModel;
import com.logistics.cloud.eunm.ResultCode;
import com.logistics.cloud.mapper.InterfaceLogMapper;
import com.logistics.cloud.response.JsonResponse;
import com.logistics.cloud.service.IInterfaceLogService;
import com.logistics.cloud.domain.InterfaceLog;
import org.springframework.beans.BeanUtils;
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

    @Override
    public JsonResponse insertObject(OperationLogModel model) {
        InterfaceLog bean = new InterfaceLog();
        BeanUtils.copyProperties(model,bean);
        boolean flag = this.save(bean);
        if (!flag){
            return JsonResponse.fail(ResultCode.FAIL);
        }
        return JsonResponse.success(flag);
    }
}
