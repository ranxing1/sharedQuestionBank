package com.example.sharedquestionbank.dao;
//@Author：ranxing
//@Time：2022/9/1119:05
//@File：AnswerDao

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.sharedquestionbank.entity.Answer;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AnswerDao extends BaseMapper<Answer> {
}
