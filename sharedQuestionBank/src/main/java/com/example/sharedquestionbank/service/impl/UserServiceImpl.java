package com.example.sharedquestionbank.service.impl;
//@Author：ranxing
//@Time：2022/8/17   18:26
//@File：UserServiceImpl.java

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.sharedquestionbank.dao.UserDao;
import com.example.sharedquestionbank.entity.User;
import com.example.sharedquestionbank.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {
}
