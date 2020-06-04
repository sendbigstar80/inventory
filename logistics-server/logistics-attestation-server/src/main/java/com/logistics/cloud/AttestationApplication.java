package com.logistics.cloud;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


/**
 * 认证服务启动类
 * @author wenxi
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class AttestationApplication {
    public static void main(String[] args) {
        SpringApplication.run(AttestationApplication.class,args);
    }
}
