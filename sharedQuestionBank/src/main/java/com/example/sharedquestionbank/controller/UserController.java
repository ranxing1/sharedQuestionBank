package com.example.sharedquestionbank.controller;
//@Author：ranxing
//@Time：2022/9/10   18:43
//@File：UserController.java

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.sharedquestionbank.common.R;
import com.example.sharedquestionbank.entity.User;
import com.example.sharedquestionbank.service.AnswerService;
import com.example.sharedquestionbank.service.QueService;
import com.example.sharedquestionbank.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private QueService queService;

    @Autowired
    private AnswerService answerService;
    /**
     * 用户登录
     *
     * @param request
     * @param user
     * @return
     */
    @PostMapping("/login")
    public R<User> login(HttpServletRequest request, @RequestBody User user) {
        System.out.println(user);
        String password = user.getUserPwd();

        //根据页面提交的用户名查询数据库
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName,user.getUserName());
        User u = userService.getOne(queryWrapper);

        //如果没有查到返回登录失败
        if (u == null) {
            return R.error("登录失败");
        }
        //如果找到后比对密码
        if (!password.equals(u.getUserPwd())) {
            return R.error("密码错误");
        }
        //查看员工状态
        if (u.getIsDeleted() == 1) {
            return R.error("用户已注销");
        }
        //把id放入session
        request.getSession().setAttribute("user", u.getUserId());

        return R.success(u);
    }

    /**
     * 注册
     * @param request
     * @param user
     * @return
     */
    @PostMapping("/register")
    public R<User> register(HttpServletRequest request, @RequestBody User user) {
        userService.save(user);
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName, user.getUserName());
        User u = userService.getOne(queryWrapper);
        //把id放入session
        request.getSession().setAttribute("user", u.getUserId());
        return R.success(u);
    }

    /**
     * 分页查询用户
     * @param page
     * @param pageSize
     * @param userName
     * @return
     */
    @GetMapping("/page")
    public R<Page> page(int page,int pageSize,String userName){
        Page<User> pageInfo = new Page<>(page,pageSize);
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName,userName);
        queryWrapper.eq(User::getIsAdministrator,0);
        userService.page(pageInfo,queryWrapper);


        return R.success(pageInfo);
    }

    /**
     * 注销用户
     * @param user
     * @return
     */
    @PostMapping("/delete")
    public R<String> delete(@RequestBody User user){
        user.setIsDeleted(1);
        userService.updateById(user);
        return R.success("注销成功");
    }

    /**
     * 恢复用户
     * @param user
     * @return
     */
    @PostMapping("/recive")
    public R<String> recive(@RequestBody User user){
        user.setIsDeleted(0);
        userService.updateById(user);
        return R.success("恢复成功");
    }



}
