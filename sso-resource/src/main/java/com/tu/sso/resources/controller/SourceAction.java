package com.tu.sso.resources.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: tuyongjian
 * @Date: 2019/10/23 16:11
 * @Description:资源服务器的资源
 */
@RestController
@RequestMapping(value = "api")
public class SourceAction {


    @RequestMapping(value = "test",method = {RequestMethod.POST,RequestMethod.GET})
    public String test(){
        return "hello world";
    }

}
