package com.www.dao;

import com.www.main.Permission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IPermissionDao {


    @Select("select * from permission where id in (select permissionId from role_permission where roleId=#{roleId})")
    public List<Permission> findByRoleId();


    @Select("select * from permission")
    public List<Permission> findAll();


    @Insert("insert into permission(permissionName,url) values(#{permissionName},#{url})")
    public void save(Permission permission);

}
