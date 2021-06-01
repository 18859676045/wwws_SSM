package com.www.dao;

import com.www.main.Role;
import com.www.main.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IUserDao {

    @Select("select * from users where id=#{id}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "username",column = "username"),
            @Result(property = "email",column = "email"),
            @Result(property = "password",column = "password"),
            @Result(property = "phoneNum",column = "phoneNum"),
            @Result(property = "status",column = "status"),
            @Result(property = "roles",column = "id",javaType = List.class,many = @Many
                    (select = "com.www.dao.IRoleDao.findRoleByUserId"))})
    public UserInfo findById(String id) throws Exception;


    @Select("select * from users ")
    public List<UserInfo> findAll() throws Exception;



    @Select("select * from users where username=#{username}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(column = "username", property = "username"),
            @Result(column = "email", property = "email"),
            @Result(column = "password", property = "password"),
            @Result(column = "phoneNum", property = "phoneNum"),
            @Result(column = "status", property = "status"),
            @Result(column = "id", property = "roles", javaType = List.class, many =
            @Many(select = "com.www.dao.IRoleDao.findRoleByUserId")) })
    public UserInfo findByUsername(String username) throws Exception;


    @Insert("insert into users(email,username,password,phoneNum,status) values(#{email},#{username},#{password},#{phoneNum},#{status})")
    void saveOne(UserInfo userInfo);

    @Select("select * from role where id not in(select roleId from users_role where userId=#{id})")
    List<Role> findByOtherRole(String id);


    @Insert("insert into USERS_ROLE(userId,roleId) values(#{userId},#{id})")
    void saveRoleToUser(@Param("userId") String userId, @Param("id") String id);
}
