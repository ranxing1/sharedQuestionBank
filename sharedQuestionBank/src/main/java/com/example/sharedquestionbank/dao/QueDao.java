package com.example.sharedquestionbank.dao;
//@Author：ranxing
//@Time：2022/8/1717:36
//@File：queDao

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.sharedquestionbank.entity.Que;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QueDao extends BaseMapper<Que> {
}
