package com.www.services;

import com.www.main.Permission;

import java.util.List;


public interface IPermissionService {

    public List<Permission> findAll();

    public void save(Permission permission);


}
