package com.example.sharedquestionbank.service.impl;
//@Author：ranxing
//@Time：2022/8/17   18:24
//@File：QueServiceImpl.java

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.sharedquestionbank.dao.QueDao;
import com.example.sharedquestionbank.entity.Que;
import com.example.sharedquestionbank.service.QueService;
import org.springframework.stereotype.Service;

@Service
public class QueServiceImpl extends ServiceImpl<QueDao, Que> implements QueService {
}
