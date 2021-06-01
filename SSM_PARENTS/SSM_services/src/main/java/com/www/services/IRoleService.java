package com.www.services;

import com.www.main.Permission;
import com.www.main.Role;

import java.util.List;


public interface IRoleService {

    public List<Role> findAll();

    void save(Role role) throws Exception;

    List<Permission> findByOtherPermission(String id) throws Exception;

    Role findRole(String id);

    void savePermissionToRole(String id, String roleId);
}
