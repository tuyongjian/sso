package com.tu.sso.model;

import org.springframework.security.core.GrantedAuthority;

/**
 * @Auther: tuyongjian
 * @Date: 2019/10/23 13:36
 * @Description:
 */
public class Role  implements GrantedAuthority {


    private Integer id;

    private String roleName;

    private Integer userId;

    @Override
    public String getAuthority() {
        return roleName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
