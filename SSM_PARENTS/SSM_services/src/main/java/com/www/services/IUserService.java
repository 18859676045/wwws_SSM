package com.www.services;

import com.www.main.Role;
import com.www.main.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends UserDetailsService{
    List<UserInfo> findAll() throws Exception;

    void saveOne(UserInfo userInfo);

    UserInfo findById(String id) throws Exception;

    List<Role> findByOtherRole(String id);

    void saveRoleToUser(String userId, String[] ids);
}
