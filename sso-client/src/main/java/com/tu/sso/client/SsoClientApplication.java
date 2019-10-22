package com.tu.sso.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

/**
 * @Auther: tuyongjian
 * @Date: 2019/10/22 14:31
 * @Description: exclude = {SecurityAutoConfiguration.class }可以禁用security安全认证
 */
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class SsoClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(SsoClientApplication.class,args);
    }
}
