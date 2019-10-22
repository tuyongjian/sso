package com.tu.sso.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * @Auther: tuyongjian
 * @Date: 2019/10/21 15:18
 * @Description:
 */
@Controller
@RequestMapping(value = "sso")
public class SsoAction {
    private Logger logger = LoggerFactory.getLogger(SsoAction.class);

    @RequestMapping(value = "oauth2.0/authorize",method = RequestMethod.GET)
    public String oauth(@RequestParam(value = "response_type")String response_type,
                        @RequestParam(value = "client_id")String client_id,
                        @RequestParam(value = "redirect_uri")String redirect_uri,
                        @RequestParam(value = "state")String state,
                        @CookieValue("JSESSIONID") String sessionId,
                        ModelAndView modelAndView,
                        HttpSession session){

        try{
            if(!response_type.equals("code"))
                throw new Exception(new Throwable("非法的response_type"));
            logger.info("进入认证登录页面，接收到的state值"+state);
            modelAndView.addObject("state",state);
            modelAndView.addObject("session",session);
            //将cookie和url保存到session中
            session.setAttribute("state",state);
            session.setAttribute("appID",client_id);
            session.setAttribute("sessionId",sessionId);
            session.setAttribute("redirect_uri",redirect_uri);
        }
        catch (Exception e){
            logger.warn("进入登录页面失败："+e.getCause().getMessage());
            modelAndView.addObject("error",e.getCause().getMessage()+"，请重新操作");
            return "error";
        }
        return "login";

    }
}
