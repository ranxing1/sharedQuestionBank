package com.example.sharedquestionbank.controller;
//@Author：ranxing
//@Time：2022/9/11   23:09
//@File：AnswerController.java

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.sharedquestionbank.common.R;
import com.example.sharedquestionbank.dao.MyDao;
import com.example.sharedquestionbank.dto.AnswerDto;
import com.example.sharedquestionbank.dto.QueDto;
import com.example.sharedquestionbank.entity.Answer;
import com.example.sharedquestionbank.entity.Que;
import com.example.sharedquestionbank.entity.User;
import com.example.sharedquestionbank.service.AnswerService;
import com.example.sharedquestionbank.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/answer")
public class AnswerController {
    @Autowired
    private AnswerService answerService;
    @Autowired
    private MyDao myDao;
    @Autowired
    private UserService userService;

    /**
     * 分页查询所选问题答案
     * @param page
     * @param pageSize
     * @param
     * @return
     */
    @GetMapping("/page")
    public R<Page> page(int page,int pageSize,Integer queId){

        //建立分页   设置查询条件   将数据库的对应字段封装入对象中
        Page<Answer> pageInfo = new Page<>(page,pageSize);
        Page<AnswerDto> answerDtoPage = new Page<>();
        LambdaQueryWrapper<Answer> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Answer::getQueId,queId);
        queryWrapper.orderByDesc(Answer::getAnswerLike);
        queryWrapper.eq(Answer::getIsDeleted,0);
        answerService.page(pageInfo,queryWrapper);
        //对象拷贝
        BeanUtils.copyProperties(pageInfo,answerDtoPage);
        List<Answer> records = pageInfo.getRecords();
        List<AnswerDto> list = records.stream().map((item) -> {

            AnswerDto answerDto = new AnswerDto();
            BeanUtils.copyProperties(item,answerDto);

            answerDto.setAnswerId(item.getAnswerId());
            //获取用户名 为分页信息插入用户名
            Integer userId = item.getUserId();
            User user = userService.getById(userId);
            if(user != null)
                answerDto.setUserName(user.getUserName());

            return answerDto;
        }).collect(Collectors.toList());

        answerDtoPage.setRecords(list);
        return R.success(answerDtoPage);
    }

    /**
     * 分页查询我的回答
     * @param page
     * @param pageSize
     * @param
     * @return
     */
    @GetMapping("/page3")
    public R<Page> page3(int page,int pageSize,Integer userId){
        //建立分页   设置查询条件   将数据库的对应字段封装入对象中
        Page<Answer> pageInfo = new Page<>(page,pageSize);
        Page<AnswerDto> answerDtoPage = new Page<>();
        LambdaQueryWrapper<Answer> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Answer::getUserId,userId);
        queryWrapper.orderByDesc(Answer::getAnswerLike);
        queryWrapper.eq(Answer::getIsDeleted,0);
        answerService.page(pageInfo,queryWrapper);
        //对象拷贝
        BeanUtils.copyProperties(pageInfo,answerDtoPage);
        List<Answer> records = pageInfo.getRecords();
        List<AnswerDto> list = records.stream().map((item) -> {

            AnswerDto answerDto = new AnswerDto();
            BeanUtils.copyProperties(item,answerDto);

            answerDto.setAnswerId(item.getAnswerId());
            //获取用户名 为分页信息插入用户名
            User user = userService.getById(userId);
//            String userName = myDao.getUserById(userId);
            if(user != null)
                answerDto.setUserName(user.getUserName());

            return answerDto;
        }).collect(Collectors.toList());

        answerDtoPage.setRecords(list);
        return R.success(answerDtoPage);
    }

    /**
     * 修改回答
     * @param answer
     * @return
     */
    @PostMapping("/update")
    public R<String> update(@RequestBody Answer answer){
        answerService.updateById(answer);
        return R.success("修改成功");
    }

    /**
     * 喜欢回答
     * @param answer
     * @return
     */
    @PostMapping("/like")
    public R<String> queLike(@RequestBody Answer answer){
//        System.out.println(answer.getAnswerLike());
        Answer a = answerService.getById(answer.getAnswerId());
//        System.out.println(a);
        a.setAnswerLike(a.getAnswerLike() + 1);
        answerService.updateById(a);
        return R.success("回答点赞成功");
    }

    /**
     * 删除回答
     * @param answer
     * @return
     */
    @PostMapping("/delete")
    public R<String> delete(@RequestBody Answer answer){
        answer.setIsDeleted(1);
        answerService.updateById(answer);
        return R.success("回答删除成功");
    }


}
