package com.tu.sso.server.service;

import com.tu.sso.model.AuthClientDetails;
import com.tu.sso.model.MyClientDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static Logger logger = LoggerFactory.getLogger(MyClientDetailsService.class);

    @Autowired
    private IAuthClientDetailService authClientDetailService;


    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        logger.info("clientId is [{}]-------------thread is [{}]",clientId,Thread.currentThread().getName());
        AuthClientDetails clientDetails = authClientDetailService.selectClientDetailsByClientId(clientId);
        if (clientDetails == null) {
            throw new ClientRegistrationException("该客户端不存在");
        }
        MyClientDetails details = new MyClientDetails(clientDetails);
        return details;
    }
}
