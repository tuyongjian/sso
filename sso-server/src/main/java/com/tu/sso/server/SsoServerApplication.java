package com.tu.sso.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@MapperScan(value = {"com.tu.sso.dao"})
class SsoServerApplication {
    public static void main( String[] args )
    {
        SpringApplication.run(SsoServerApplication.class);
    }
}
