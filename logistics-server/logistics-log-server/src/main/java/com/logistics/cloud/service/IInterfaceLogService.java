package com.logistics.cloud.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.logistics.cloud.aspect.model.OperationLogModel;
import com.logistics.cloud.domain.InterfaceLog;
import com.logistics.cloud.response.JsonResponse;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhangwx
 * @since 2020-04-03
 */
public interface IInterfaceLogService extends IService<InterfaceLog> {

    JsonResponse insertObject(OperationLogModel model);
}
