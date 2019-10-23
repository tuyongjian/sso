package com.tu.sso.server.service;

import com.tu.sso.model.Role;

import java.util.List;

/**
 * @Auther: tuyongjian
 * @Date: 2019/10/23 13:50
 * @Description:
 */

public interface IRoleService {

    List<Role> queryRole(Role role);
}
