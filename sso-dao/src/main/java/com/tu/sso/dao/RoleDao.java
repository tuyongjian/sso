package com.tu.sso.dao;

import com.tu.sso.model.Role;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Auther: tuyongjian
 * @Date: 2019/10/23 13:39
 * @Description:
 */
@Mapper
@Repository
public interface RoleDao {

    List<Role> selectRole(Role role);
}
