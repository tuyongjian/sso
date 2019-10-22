package com.tu.sso.controller;

import com.tu.sso.model.User;
import com.tu.sso.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: tuyongjian
 * @Date: 2019/10/21 15:18
 * @Description:
 */
@RestController
@RequestMapping(value = "sso")
public class SsoAction {


    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @RequestMapping(value = "oauth",method = RequestMethod.POST)
    public Object oauth(@RequestParam(value = "userName")String userName){

        UserDetails user= myUserDetailsService.loadUserByUsername(userName);
        return user;
    }
}
