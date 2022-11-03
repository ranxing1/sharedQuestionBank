package com.example.sharedquestionbank.service.impl;
//@Author：ranxing
//@Time：2022/9/11   19:06
//@File：AnswerServiceImpl.java

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.sharedquestionbank.dao.AnswerDao;
import com.example.sharedquestionbank.entity.Answer;
import com.example.sharedquestionbank.service.AnswerService;
import org.springframework.stereotype.Service;

@Service
public class AnswerServiceImpl extends ServiceImpl<AnswerDao, Answer> implements AnswerService {
}
