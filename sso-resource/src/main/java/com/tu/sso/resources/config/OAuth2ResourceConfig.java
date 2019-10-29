package com.tu.sso.resources.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * @Auther: tuyongjian
 * @Date: 2019/10/23 16:30
 * @Description:
 */
@Configuration
@EnableResourceServer
public class OAuth2ResourceConfig extends ResourceServerConfigurerAdapter {


    @Primary
    @Bean
    public RemoteTokenServices tokenServices() {
        final RemoteTokenServices tokenService = new RemoteTokenServices();
        tokenService.setCheckTokenEndpointUrl("http://localhost:1234/oauth/check_token");
        tokenService.setClientId("ssoClient");
        tokenService.setClientSecret("123456");
        return tokenService;
    }


    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    //与授权服务器使用共同的密钥进行解析
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey("123456");
        return converter;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
             .and()
             //请求权限配置
             .authorizeRequests()
             //下边的路径放行,不需要经过认证
             .antMatchers("/oauth/*", "/auth/user/login").permitAll()
             //OPTIONS请求不需要鉴权
             .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
              //这里限制接口的scope对应的就是server端里设置的scope参数
             .antMatchers(HttpMethod.GET, "/api/*").access("#oauth2.hasScope('write')")
                //这个接口是允许角色为ROLE_APP的访问，相对应的是Role表的role_name
             .antMatchers(HttpMethod.GET,"/test/*").hasRole("APP")
             //其余接口没有角色限制，但需要经过认证，只要携带token就可以放行
             .anyRequest()
             .authenticated();

    }

}
