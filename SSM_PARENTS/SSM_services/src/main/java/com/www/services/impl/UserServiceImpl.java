package com.www.services.impl;

import com.www.BCryptPasswordEncoderUtils;
import com.www.dao.IUserDao;
import com.www.main.Role;
import com.www.main.UserInfo;
import com.www.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service(value = "userService")
@Transactional
public class UserServiceImpl implements IUserService{

    @Autowired
    private IUserDao userDao;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo= null;
        try {
            userInfo = userDao.findByUsername(username);
        } catch (Exception e) {
            e.printStackTrace();
        }


        User user=new User(userInfo.getUsername(), userInfo.getPassword(), userInfo.getStatus() == 0 ? false : true,
                true, true, true, getAuthority(userInfo.getRoles()));
//        User user=new User(userInfo.getUsername(),"{noop}" + userInfo.getPassword(),getAuthority());

        return user;
    }

    private List<SimpleGrantedAuthority> getAuthority(List<Role> roles) {

        List<SimpleGrantedAuthority> list = new ArrayList<>();

        for (Role role : roles) {
            list.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
        }

        return list;

    }

    @Override
    public List<UserInfo> findAll() throws Exception {
        return userDao.findAll();
    }

    @Override
    public void saveOne(UserInfo userInfo) {
        userInfo.setPassword(BCryptPasswordEncoderUtils.encodePassWord(userInfo.getPassword()));
        userDao.saveOne(userInfo);

        return ;
    }

    @Override
    public UserInfo findById(String id) throws Exception {
        return userDao.findById(id);
    }

    @Override
    public List<Role> findByOtherRole(String id) {
        return userDao.findByOtherRole(id);
    }

    @Override
    public void saveRoleToUser(String userId, String[] ids) {
        for (String id : ids) {
            userDao.saveRoleToUser(userId,id);
        }

    }
}
