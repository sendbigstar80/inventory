package com.logistics.cloud;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
@MapperScan("com.logistics.cloud.mapper")
public class LogApplication {

    public static void main(String[] args) {
        SpringApplication.run(LogApplication.class,args);
    }
}
