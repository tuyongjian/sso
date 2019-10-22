package com.tu.sso.client.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
        return "redirect:"+oauthHost+"/sso/oauth2.0/authorize?response_type=code&client_id="
                + clientId + "&redirect_uri=" + myHost+"/redirect" + "&state=" + state;
    }


    @RequestMapping(value = "index",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String index(){
        return "123";
    }
}
