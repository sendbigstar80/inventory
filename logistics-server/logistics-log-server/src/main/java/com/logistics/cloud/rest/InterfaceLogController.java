package com.logistics.cloud.rest;


import com.logistics.cloud.domain.InterfaceLog;
import com.logistics.cloud.model.log.OperationLogModel;
import com.logistics.cloud.response.JsonResponse;
import com.logistics.cloud.service.IInterfaceLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhangwx
 * @since 2020-04-03
 */
@RestController
@RequestMapping
@Slf4j
public class InterfaceLogController {

    @Resource
    private IInterfaceLogService logService;

    @PostMapping("/insert/object")
    public JsonResponse insertObject(@RequestBody OperationLogModel model){
        return logService.insertObject(model);
    }

    @GetMapping(value = "/selectAll")
    public JsonResponse<InterfaceLog> selectAll(){
        return logService.selectAll();
    }
}
