package com.www.dao;

import com.www.main.Permission;
import com.www.main.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IRoleDao {



    @Select("select * from role where id in(select roleId from users_role where userId=#{userId})")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "roleName",property = "roleName"),
            @Result(column = "roleDesc",property = "roleDesc"),
            @Result(column = "id",property = "permissions",javaType = List.class,many = @Many
                    (select = "com.www.dao.IPermissionDao.findByRoleId"))
    })
    public List<Role> findRoleByUserId(String userId);


    @Select("select * from role")
    public List<Role> findAll();

    @Insert("insert into role(roleName,roleDesc) values(#{roleName},#{roleDesc})")
    public void save(Role role) throws Exception;

    @Select("select * from permission where id not in(select permissionId from role_permission where roleId=#{id})")
    List<Permission> findByPermission(String id) throws Exception;


    @Select("select * from role where id=#{id}")
    Role findRole(String id);


    @Insert("insert into ROLE_PERMISSION(permissionId,roleId) values(#{id},#{roleId})")
    void savePermissionToRole(@Param("id") String id,@Param("roleId") String roleId);
}
