package com.tu.sso.server.config;

import com.tu.sso.model.User;
import com.tu.sso.server.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Auther: tuyongjian
 * @Date: 2019/10/22 10:15
 * @Description: 认证服务管理 身份授权认证服务配置
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    LettuceConnectionFactory redisConnectionFactory;

    @Autowired
    MyUserDetailsService myUserDetailsService;


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();

    }

    @Bean
    public TokenStore getTokenStore() {
        return new RedisTokenStore(redisConnectionFactory);

    }

    @Bean
    public TokenEnhancer tokenEnhancer() {
        return (accessToken, authentication) -> {
            if (accessToken instanceof DefaultOAuth2AccessToken) {
                DefaultOAuth2AccessToken token = (DefaultOAuth2AccessToken) accessToken;
                Map<String, Object> additionalInformation = new LinkedHashMap<>();
                additionalInformation.put("expiration", accessToken.getExpiration().getTime());

                User user = (User) authentication.getPrincipal();
                additionalInformation.put("userName", user.getUsername());
                token.setAdditionalInformation(additionalInformation);
            }
            return accessToken;
        };
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints
                .tokenEnhancer(tokenEnhancer())
                .authenticationManager(authenticationManager)
                .userDetailsService(myUserDetailsService)
                .tokenStore(getTokenStore());
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        security
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()")
                .allowFormAuthenticationForClients();
    }

    //访问http://localhost:1234/oauth/token
    //grant_type=password
    //scope=all
    //client_id=client_2
    //client_secret=123456
    //username=xxx
    //password=xxx
    //就可以获得token等参数

   /* @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //密码模式
        clients.inMemory()
                .withClient("client_2")
                .authorizedGrantTypes("password", "refresh_token")
                .scopes("all")
                .authorities("ROLE_APP")
                .secret(passwordEncoder().encode("123456"))
                .accessTokenValiditySeconds(60 * 30)
                .refreshTokenValiditySeconds(60 * 60);
    }*/


   //授权码模式
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //密码模式
        clients.inMemory()
                .withClient("client_2")
                .authorizedGrantTypes("authorization_code", "refresh_token")
                .scopes("all")
                .authorities("ROLE_APP")
                .secret(passwordEncoder().encode("123456"))
                .redirectUris("http://localhost:1235/ssoClient/index/redirect")
                .accessTokenValiditySeconds(60 * 30)
                .refreshTokenValiditySeconds(60 * 60);
    }

 }