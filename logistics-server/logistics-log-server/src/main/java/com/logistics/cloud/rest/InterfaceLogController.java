package com.logistics.cloud.rest;


import com.logistics.cloud.domain.InterfaceLog;
import com.logistics.cloud.service.IInterfaceLogService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
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
@RequestMapping("/log")
@Slf4j
public class InterfaceLogController {

    @Resource
    private IInterfaceLogService logService;

    @PostMapping("/insert/object")
    public boolean insertObject(@RequestBody InterfaceLog interfaceLog){
        log.info("logistics-log-server: " + MDC.get("X-B3-TraceId"));
//        return logService.save(interfaceLog);
        return false;
    }
}
