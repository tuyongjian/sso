package com.tu.sso.service.impl;

import com.tu.sso.dao.UserDao;
import com.tu.sso.model.User;
import com.tu.sso.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: tuyongjian
 * @Date: 2019/10/22 09:52
 * @Description:
 */
@Service
public class UserImpl implements IUserService {


    @Autowired
    private UserDao userDao;

    @Override
    public User queryUser(User user) {
        return userDao.selectUser(user);
    }
}
