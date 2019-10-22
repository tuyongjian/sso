package com.tu.sso.dao;

import com.tu.sso.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * Created by tuyongjian on 2019/1/6.
 */
@Mapper
@Repository
public interface UserDao {

    User selectUser(User user);

    int insert(User user);

    int update(User user);

}
