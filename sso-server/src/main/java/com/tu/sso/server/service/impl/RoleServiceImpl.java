package com.tu.sso.server.service.impl;

import com.tu.sso.dao.RoleDao;
import com.tu.sso.model.Role;
import com.tu.sso.server.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: tuyongjian
 * @Date: 2019/10/23 13:51
 * @Description:
 */
@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public List<Role> queryRole(Role role) {
        return roleDao.selectRole(role);
    }
}
