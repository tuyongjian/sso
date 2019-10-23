package com.tu.sso.server.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * @Auther: tuyongjian
 * @Date: 2019/10/22 10:38
 * @Description: 资源服务器
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
    private static final String RESOURCE_ID = "*";



    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId(RESOURCE_ID);
    }


    @Override
    public void configure(HttpSecurity http) throws Exception{
        http
                .csrf().disable()
                .exceptionHandling()
                .and()
                .authorizeRequests()
                .antMatchers("/captcha/getCaptcha/*", "/captcha/checkCaptcha/*", "/druid/*")
                .permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic()
                .disable();
    }

}
