package com.logistics.cloud.exception;

import com.logistics.cloud.eunm.ResultCode;
import com.logistics.cloud.response.JsonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@Slf4j
public class BusinessExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<?> handlerException(HttpServletRequest request,Exception ex){
        JsonResponse<Object> jsonResponse = new JsonResponse<>();
        if (ex instanceof BusinessException){//如果是业务异常
            jsonResponse.setCode(((BusinessException) ex).getCode());
            jsonResponse.setMessage(ex.getMessage());
            log.warn("[全局业务异常]\r\n业务编码：{}\r\n异常记录：{}", jsonResponse.getCode(), jsonResponse.getMessage());
        }else {//未知错误
            jsonResponse.setMessage(ResultCode.UNKNOWN.message());
            jsonResponse.setCode(ResultCode.UNKNOWN.code());
        }
        //返回数据
        return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
    }
}
