package com.qf.dao;

import com.qf.domain.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IUserDao {

    /**
     * 查询所有的用户记录
     * @return
     */
    @Select("select * from user")
    List<User> findAll();


}
