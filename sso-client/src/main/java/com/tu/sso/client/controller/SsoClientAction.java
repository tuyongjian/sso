package com.tu.sso.client.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: tuyongjian
 * @Date: 2019/10/22 14:33
 * @Description:
 */
@RestController
@RequestMapping(value = "ssoClient")
public class SsoClientAction {

    @RequestMapping(value = "index",method = {RequestMethod.GET,RequestMethod.POST})
    public String index(){
        return null;
    }
}
