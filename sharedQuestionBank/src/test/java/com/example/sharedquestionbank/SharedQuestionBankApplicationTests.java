package com.example.sharedquestionbank;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.sharedquestionbank.entity.User;
import com.example.sharedquestionbank.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SharedQuestionBankApplicationTests {

    @Autowired
    UserService userService;
    @Test
    void contextLoads() {
        User user = new User();
        user.setUserPwd("张三");
        user.setUserPwd("123");
        //新增用户
        userService.save(user);
        //修改id为1的用户的信息
        user.setUserId(1);
        userService.updateById(user);

        userService.getById(2);

        userService.removeById(2);
    }

    @Test
    void test(){
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName,"张三");
        User u = new User();
        u = userService.getOne(queryWrapper);
    }

}
