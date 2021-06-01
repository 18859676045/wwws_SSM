package com.www.dao;

import com.www.main.Orders;
import org.apache.ibatis.annotations.*;
import org.springframework.core.annotation.Order;

import java.util.List;

public interface IOrdersDao {

    @Select("select * from orders")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "orderNum",property = "orderNum"),
            @Result(column = "orderTime",property = "orderTime"),
            @Result(column = "orderStatus",property = "orderStatus"),
            @Result(column = "peopleCount",property = "peopleCount"),
            @Result(column = "payType",property = "payType"),
            @Result(column = "orderDesc",property = "orderDesc"),
            @Result(column = "productId",property = "product",one = @One(select = "com.www.dao.IProductDao.findById"))
    })
    List<Orders> findAll() throws Exception;


    @Select("select * from orders where id=#{id}")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "orderNum",property = "orderNum"),
            @Result(column = "orderTime",property = "orderTime"),
            @Result(column = "orderStatus",property = "orderStatus"),
            @Result(column = "peopleCount",property = "peopleCount"),
            @Result(column = "payType",property = "payType"),
            @Result(column = "orderDesc",property = "orderDesc"),
            @Result(column = "productId",property = "product",one = @One(select = "com.www.dao.IProductDao.findById")),
            @Result(column = "memberId",property = "member",one = @One(select = "com.www.dao.IMemberDao.findById")),
            @Result(column = "id",property = "travellers",many = @Many(select = "com.www.dao.ITravellerDao.findByOrdersId"))
    })
    Orders findById(String id) throws  Exception;
}
