package com.example.sharedquestionbank.dao;
//@Author：ranxing
//@Time：2022/9/130:16
//@File：MyDao

import com.example.sharedquestionbank.entity.Category;
import com.example.sharedquestionbank.entity.Que;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MyDao {
    @Select("select category_name from category where category_id = #{category_id}")
    String getById(Integer category_id);

    @Select("select user_name from user where user_id = #{user_id}")
    String getUserById(Integer user_id);

    @Select("select * from que where que_id = #{que_id}")
    Que getQueById(Integer que_id);
}
