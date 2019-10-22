package com.tu.sso.configuration;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

/**
 * @Auther: tuyongjian
 * @Date: 2019/10/22 10:41
 * @Description:自定义决策管理器
 */

@Component
public class CustomAccessDecisionManager implements AccessDecisionManager {
    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> collection) throws AccessDeniedException, InsufficientAuthenticationException {
        HttpServletRequest request = ((FilterInvocation) o).getHttpRequest();

        for (GrantedAuthority ga : authentication.getAuthorities()) {
            String authority = ga.getAuthority();
            String url = StringUtils.substringAfter(authority," ");
            String method = StringUtils.substringBefore(authority," ");

            AntPathRequestMatcher matcher = new AntPathRequestMatcher(url,method);
            if (matcher.matches(request)) {
                return;
            }
        }
        throw new AccessDeniedException("Access Denied");

    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
