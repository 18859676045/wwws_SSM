package com.www.dao;

import com.www.main.Product;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface IProductDao {


    /**
     * 查找方法
     * @return
     */
    @Select("select * from PRODUCT")
    List<Product> findAll();

    @Select("select * from product where id=#{id}")
    Product findById(String id) throws Exception;


    @Insert("insert into product(productNum,productName,cityName,departureTime,productPrice,productDesc,productStatus) values(#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    void save(Product product) throws Exception;


    @Delete("delete from product where id=#{id}")
    void deleteOne(String id);
}
