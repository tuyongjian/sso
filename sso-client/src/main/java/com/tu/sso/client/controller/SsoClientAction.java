package com.tu.sso.client.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * @Auther: tuyongjian
 * @Date: 2019/10/22 14:33
 * @Description:
 */
@Controller
@RequestMapping(value = "ssoClient")
public class SsoClientAction {

    private Logger logger = LoggerFactory.getLogger(SsoClientAction.class);

    @Value("${oauthHost}")
    private String oauthHost;

    @Value("${clientId}")
    private String clientId;

    @Value("${myHost}")
    private String myHost;


    @GetMapping("/login")
    public String clientLogin(HttpServletRequest request){
        //获取当前sesion
        HttpSession sessoin=request.getSession();
        //随机产生字符串
        String state= String.valueOf(System.currentTimeMillis());
        logger.info("进入/login接口，产生随机字符串："+state);
        sessoin.setAttribute("state",state);
        //重定向
        return "redirect:"+oauthHost+"/oauth/authorize?response_type=code&client_id="
                + clientId + "&redirect_uri=" + myHost+ "&state=" + state;
    }


    @RequestMapping(value = "authCodeCallback",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Object authCodeCallback(@RequestParam(value = "code") String code,
                        @RequestParam(value = "state")String state) throws IOException {
        logger.info("授权编号[{}],state[{}]", code, state);
        //获取token
        RestTemplate restTemplate = new RestTemplate();
        StringBuffer sb =new StringBuffer();
        sb.append(oauthHost+"/oauth/token");
        sb.append("?client_id=");
        sb.append(clientId);
        sb.append("&code=");
        sb.append(code);
        sb.append("&grant_type=authorization_code");
        sb.append("&redirect_uri="+myHost);
        sb.append("&client_secret=123456");
        logger.info("获取token的url---"+sb.toString());
        String auth_Str = clientId + ":" + "123456";
        byte[] encodedAuth = Base64.encodeBase64(auth_Str.getBytes(Charset.forName("US-ASCII")));
        String authHeader = "Basic " + new String(encodedAuth);
        HttpHeaders headers = new HttpHeaders();
        headers.set("authorization", authHeader);
        ResponseEntity<String> response = restTemplate.exchange(sb.toString(), HttpMethod.POST, new HttpEntity<>(null, headers), String.class);
        logger.info("authorization response-----"+response.getBody());
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(response.getBody());
        String accessToken = node.path("access_token").asText();
        String tokenType = node.path("token_type").asText();
        String refreshToken = node.path("refresh_token").asText();
        String expires_in = node.path("expires_in").asText();
        String scope = node.path("scope").asText();
        String jti = node.path("jti").asText();

        //检查token
        StringBuffer checkTokenUrl =new StringBuffer();
        checkTokenUrl.append(oauthHost+"/oauth/check_token");
        checkTokenUrl.append("?token=");
        checkTokenUrl.append(accessToken);

        ResponseEntity<String> checkTokenResponse  = restTemplate.exchange(checkTokenUrl.toString(), HttpMethod.GET, new HttpEntity<>(null, headers), String.class);
        logger.info("checkToken response-----"+checkTokenResponse.getBody());

        //刷新token
        StringBuffer refreshTokenUrl =new StringBuffer();
        refreshTokenUrl.append(oauthHost+"/oauth/token");
        refreshTokenUrl.append("?grant_type=refresh_token");
        refreshTokenUrl.append("&refresh_token=");
        refreshTokenUrl.append(refreshToken);

        ResponseEntity<String> refreshTokenResponse  = restTemplate.exchange(refreshTokenUrl.toString(), HttpMethod.POST, new HttpEntity<>(null, headers), String.class);
        logger.info("refreshToken response-----"+refreshTokenResponse.getBody());
        return response;
    }


    @GetMapping("/test")
    public String test(HttpServletRequest request) {
        RestTemplate restTemplate = new RestTemplate();
        String url ="http://localhost:1236/api/test";
        ResponseEntity<String> refreshTokenResponse  = restTemplate.exchange(url, HttpMethod.GET,null, String.class);
        return refreshTokenResponse.getBody();
    }

}
