package com.www.services.impl;

import com.www.dao.IRoleDao;
import com.www.main.Permission;
import com.www.main.Role;
import com.www.services.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class RoleServiceImpl implements IRoleService{

    @Autowired
    private IRoleDao roleDao;

    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }

    @Override
    public void save(Role role) throws Exception {
         roleDao.save(role);
    }



    @Override
    public Role findRole(String id) {
        return roleDao.findRole(id);
    }

    @Override
    public void savePermissionToRole(String id, String roleId) {
        roleDao.savePermissionToRole(id,roleId);
    }

    @Override
    public List<Permission> findByOtherPermission(String id) throws Exception {
        return roleDao.findByPermission(id);
    }
}
