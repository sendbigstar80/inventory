package com.logistics.cloud.configuration;

import com.logistics.cloud.handlers.LoginFailureHandler;
import com.logistics.cloud.handlers.LoginSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @program logistics-inventory-cloud
 * @description: Security配置类
 * @author: wenxi
 * @create: 2020/07/02 22:29
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private LoginSuccessHandler loginSuccessHandler;

    @Autowired
    private LoginFailureHandler loginFailureHandler;

    /**
     * 初始化密码编码器
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

    /**
     * 使用内存模式初始化一个张三用户
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("zhangsan").password("123").roles("user");
    }

    /**
     * 放行静态资源
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/js/**","/css/**","/images/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //所有请求都需要经过认证
                .anyRequest().authenticated()
                .and()
                //表单登陆
                .formLogin()
                //指定登陆页面
                .loginPage("/login.html")
                //指定登陆接口
                .loginProcessingUrl("/doLogin")
                //服务器转发，登陆完会直接跳转到指定页面
//                .successForwardUrl("/s")
                //重定向跳转，会记录登陆前的一个页面，登陆完后就跳转到原本的页面
//                .defaultSuccessUrl("/s")
                //登陆成功返回json处理器
                .successHandler(loginSuccessHandler)
                //登陆失败返回json处理器
                .failureHandler(loginFailureHandler)
                .permitAll()
                .and()
                //允许注销登陆
                .logout()
                //注销成功跳转的页面
                .logoutSuccessUrl("/login.html")
                .permitAll()
                .and().csrf().disable();
    }
}
