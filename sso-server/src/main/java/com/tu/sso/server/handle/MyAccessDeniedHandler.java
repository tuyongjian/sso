package com.tu.sso.server.handle;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tu.sso.common.model.Response;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Auther: tuyongjian
 * @Date: 2019/10/30 09:08
 * @Description:自定义访问拒绝处理器
 */
@Component
public class MyAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {

        Response response = new Response();
        response.setMessage("无权访问");
        response.setCode("403");
        httpServletResponse.setContentType("application/json,charset=utf-8");
        httpServletResponse.setStatus(403);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(httpServletResponse.getOutputStream(), response);
    }
}
