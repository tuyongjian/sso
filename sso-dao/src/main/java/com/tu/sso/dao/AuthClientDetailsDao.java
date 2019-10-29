package com.tu.sso.dao;

import com.tu.sso.model.AuthClientDetails;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @Auther: tuyongjian
 * @Date: 2019/10/24 10:12
 * @Description:
 */
@Mapper
@Repository
public interface AuthClientDetailsDao {

    AuthClientDetails selectClientDetailsByClientId(String clientId);
}
