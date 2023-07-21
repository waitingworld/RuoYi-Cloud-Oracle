package com.ruoyi.activiti.security.config;

import com.ruoyi.activiti.security.LoginFailureHandler;
import com.ruoyi.activiti.security.LoginSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class ActivitiSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private LoginSuccessHandler loginSuccessHandler;

    @Autowired
    private LoginFailureHandler loginFailureHandler;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
//--------------------------最精简的配置 对所有的页面都允许-----------------------------
        http
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login")//对应ActivitiSecurityController.requireAuthentication 未登录用户进行提示 提示内容编写
                .successHandler(loginSuccessHandler)
                .failureHandler(loginFailureHandler)
                .and()
                .authorizeRequests()
                .anyRequest()
                .permitAll().and().logout().permitAll().and().csrf().disable().headers().frameOptions().disable();//全部页面不验证

    }


}
