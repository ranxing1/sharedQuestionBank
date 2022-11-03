package com.example.sharedquestionbank.controller;
//@Author：ranxing
//@Time：2022/9/10   18:43
//@File：CategoryController.java

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.sharedquestionbank.common.R;
import com.example.sharedquestionbank.entity.Category;
import com.example.sharedquestionbank.entity.Que;
import com.example.sharedquestionbank.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * 根据id得到种类名称
     * @param que
     * @return
     */
    @GetMapping("/name")
    public R<String> getName(@RequestBody Que que){
        Integer categoryId = que.getCategoryId();
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Category::getCategoryId,categoryId);
        Category category = categoryService.getOne(queryWrapper);
        return R.success(category.getCategoryName());
    }
}
