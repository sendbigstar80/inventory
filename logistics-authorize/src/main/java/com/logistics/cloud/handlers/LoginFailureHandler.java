package com.logistics.cloud.handlers;

import com.alibaba.fastjson.JSON;
import com.logistics.cloud.eunm.ResultCode;
import com.logistics.cloud.response.JsonResponse;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @program logistics-inventory-cloud
 * @description: 登陆失败处理器
 * @author: wenxi
 * @create: 2020/07/02 23:30
 */
@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {


    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        JsonResponse jsonResponse = new JsonResponse();
        jsonResponse.setCode(ResultCode.FAIL.code());
        if (exception instanceof LockedException) {
            jsonResponse.setMessage("账户被锁定，请联系管理员!");
        } else if (exception instanceof CredentialsExpiredException) {
            jsonResponse.setMessage("密码过期，请联系管理员!");
        } else if (exception instanceof AccountExpiredException) {
            jsonResponse.setMessage("账户过期，请联系管理员!");
        } else if (exception instanceof DisabledException) {
            jsonResponse.setMessage("账户被禁用，请联系管理员!");
        } else if (exception instanceof BadCredentialsException) {
            jsonResponse.setMessage("用户名或者密码输入错误，请重新输入!");
        }else {
            jsonResponse.setMessage(exception.getMessage());
        }
        response.setContentType("application/json; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.write(JSON.toJSONString(jsonResponse));
        out.flush();
        out.close();
    }
}
