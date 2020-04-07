package com.logistics.cloud.exception;

import com.logistics.cloud.response.JsonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@Component
@Slf4j
@RestControllerAdvice
public class ValidationException extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(getError(ex.getBindingResult().getAllErrors()), status);
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(getError(ex.getBindingResult().getAllErrors()), status);
    }

    private JsonResponse getError(List<ObjectError> errorList) {
        StringBuffer message = new StringBuffer();
        errorList.forEach(error -> {
            //设置Validation提示的错误信息
            message.append(error.getDefaultMessage()).append(",");
        });
        //去掉最后一个,号
        message.deleteCharAt(message.length() - 1);
        log.info("error message: {}", message);
        return JsonResponse.fail(message.toString());
    }
}
