package com.tu.sso.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * @Auther: tuyongjian
 * @Date: 2019/10/22 09:14
 * @Description:
 */

public class User implements  UserDetails {

    private Integer id;
    private String password;
    private String userName;
    private String realName;
    private List<Role> authorities;

    //权限列表
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    //获取密码
    @Override
    public String getPassword() {
        return password;
    }

    //获取用户名
    @Override
    public String getUsername() {
        return userName;
    }

    //账户是否生效
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //账户是否锁定
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //凭证是否生效
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //是否激活
    @Override
    public boolean isEnabled() {
        return true;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public void setAuthorities(List<Role> authorities) {
        this.authorities = authorities;
    }
}
