package com.example.sharedquestionbank.dao;
//@Author：ranxing
//@Time：2022/8/1718:22
//@File：CategoryDao

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.sharedquestionbank.entity.Category;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CategoryDao extends BaseMapper<Category> {
}
