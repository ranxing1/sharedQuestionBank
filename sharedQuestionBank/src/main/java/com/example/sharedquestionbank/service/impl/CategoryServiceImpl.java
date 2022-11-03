package com.example.sharedquestionbank.service.impl;
//@Author：ranxing
//@Time：2022/8/17   18:28
//@File：CategoryServiceImpl.java

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.sharedquestionbank.dao.CategoryDao;
import com.example.sharedquestionbank.entity.Category;
import com.example.sharedquestionbank.service.CategoryService;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, Category> implements CategoryService {
}
