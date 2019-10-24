package com.tu.sso.server.service;

import com.tu.sso.model.AuthClientDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;

/**
 * @Auther: tuyongjian
 * @Date: 2019/10/23 17:47
 * @Description:
 */
@Service
public class MyClientDetailsService implements ClientDetailsService {

    @Autowired
    private IAuthClientDetailService authClientDetailService;


    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        AuthClientDetails clientDetails = authClientDetailService.selectClientDetailsByClientId(clientId);
        if (clientDetails == null) {
            throw new ClientRegistrationException("该客户端不存在");
        }
        MyClientDetails details = new MyClientDetails(clientDetails);
        return details;
    }
}
