package com.tu.sso.server.service.impl;

import com.tu.sso.dao.AuthClientDetailsDao;
import com.tu.sso.model.AuthClientDetails;
import com.tu.sso.server.service.IAuthClientDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: tuyongjian
 * @Date: 2019/10/24 09:56
 * @Description:
 */
@Service
public class AuthClientDetailServiceImpl implements IAuthClientDetailService {

    @Autowired
    private AuthClientDetailsDao authClientDetailsDao;

    @Override
    public AuthClientDetails selectClientDetailsByClientId(String clientId) {
        return authClientDetailsDao.selectClientDetailsByClientId(clientId);
    }
}
