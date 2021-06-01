package com.www.dao;

import com.www.main.Member;
import org.apache.ibatis.annotations.Select;

public interface IMemberDao {


    @Select("select * from member where id=#{id}")
    Member findById(String id) throws Exception;
}
