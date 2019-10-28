package com.tu.sso.server.config;

import com.tu.sso.server.service.MyClientDetailsService;
import com.tu.sso.server.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * @Auther: tuyongjian
 * @Date: 2019/10/23 13:54
 * @Description: 认证服务
 */
@Configuration
@EnableAuthorizationServer
public class OAuth2Config extends AuthorizationServerConfigurerAdapter {

    //引入 webSecurityConfig 中配置的 AuthenticationManager
    @Autowired
    private AuthenticationManager authenticationManager;

    //引入 UserServiceDetail 服务
    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private MyClientDetailsService myClientDetailsService;

    //配置客户端信息
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //这里直接把配置信息保存在内存中
       /* clients.inMemory()
                .withClient("ssoClient")
                //这里必须使用加密
                .secret(new BCryptPasswordEncoder().encode("123456"))
                //配置 GrantTypes
                //支持 刷新token
                // 使用密码模式
                .authorizedGrantTypes("authorization_code","refresh_token","password")
                //这个随便配了一个，暂时没用到
                .scopes("read","write","del","userinfo")
                .redirectUris("http://localhost:1235/ssoClient/authCodeCallback")
                .accessTokenValiditySeconds(30*60);//30*60s过期*/
       //基于数据库
        clients.withClientDetails(myClientDetailsService);
    }

    //配置 Token 的节点 和 Token 服务
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

        endpoints.tokenStore(tokenStore())
                .authenticationManager(authenticationManager)
                .userDetailsService(myUserDetailsService)
                .accessTokenConverter(jwtTokenEnhancer());
    }

    // 配置 Token 节点的安全策略
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()");
    }

    //使用 jwt
    @Bean
    public TokenStore tokenStore(){
        return new JwtTokenStore(jwtTokenEnhancer());
    }

    // 配置 jwt 生成 策略
    @Bean
    public JwtAccessTokenConverter jwtTokenEnhancer(){
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey("123456");   //密钥
        return converter;
    }


}
