package com.tu.sso.server.service;

import com.tu.sso.model.AuthClientDetails;

/**
 * @Auther: tuyongjian
 * @Date: 2019/10/24 09:55
 * @Description:
 */

public interface IAuthClientDetailService {

    AuthClientDetails selectClientDetailsByClientId(String clientId);
}
