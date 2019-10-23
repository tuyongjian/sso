package com.tu.sso.server.service;

import com.tu.sso.model.Role;
import com.tu.sso.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: tuyongjian
 * @Date: 2019/10/22 09:54
 * @Description:
 */
@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        User u = new User();
        u.setUserName(s);
        User user = userService.queryUser(u);
        if(user==null){
            throw new UsernameNotFoundException("user not found");
        }

        Role role = new Role();
        role.setUserId(user.getId());
        List<Role> roles =  roleService.queryRole(role);
        user.setAuthorities(roles);
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        return user;
    }
}
