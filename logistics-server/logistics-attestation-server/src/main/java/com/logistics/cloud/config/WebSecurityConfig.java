package com.logistics.cloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * @program logistics-inventory-cloud
 * @description: 安全配置
 * @author: wenxi
 * @create: 2020/06/02 22:44
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    /**
     * 1.初始化一个自定义对象—> 通过 InMemoryUserDetailsManager 加载一个自定义对象
     * @return
     */
    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager();
        inMemoryUserDetailsManager.createUser(User.withUsername("admin").password("123").authorities("p1").build());
        inMemoryUserDetailsManager.createUser(User.withUsername("user").password("123").authorities("p2").build());
        return inMemoryUserDetailsManager;
    }


    /**
     * 2.初始化一个密码解密器
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

    //3.配置资源保护
    /**
     * 配置资源保护
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/r/p1").hasAuthority("p1")
                .antMatchers("/r/p2").hasAuthority("p2")
                .antMatchers("/r/**")
                .authenticated().anyRequest().permitAll()
                .and().formLogin().successForwardUrl("/LoginSuccess");
    }
}
