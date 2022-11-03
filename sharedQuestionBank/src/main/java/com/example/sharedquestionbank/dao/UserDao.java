package com.example.sharedquestionbank.dao;
//@Author：ranxing
//@Time：2022/8/1718:21
//@File：UserDao

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.sharedquestionbank.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao extends BaseMapper<User> {
}
