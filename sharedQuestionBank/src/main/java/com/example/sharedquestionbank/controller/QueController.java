package com.example.sharedquestionbank.controller;
//@Author：ranxing
//@Time：2022/9/10   18:43
//@File：QueController.java

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.sharedquestionbank.common.R;
import com.example.sharedquestionbank.dao.MyDao;
import com.example.sharedquestionbank.dto.QueDto;
import com.example.sharedquestionbank.entity.Answer;
import com.example.sharedquestionbank.entity.Category;
import com.example.sharedquestionbank.entity.Que;
import com.example.sharedquestionbank.service.AnswerService;
import com.example.sharedquestionbank.service.CategoryService;
import com.example.sharedquestionbank.service.QueService;
import com.example.sharedquestionbank.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/que")
public class QueController {
    @Autowired
    private QueService queService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private AnswerService answerService;
    @Autowired
    private UserService userService;
    @Autowired
    private MyDao myDao;



    /**
     * 题目分页查询
     * 按queLike排序
     * @param
     * @param name
     * @return
     */
    @GetMapping("/page")
    public R<Page> page(int page,int pageSize, String name){
        //设置分页
        Page<Que> pageInfo  = new Page<>(page,pageSize);
        Page<QueDto> queDtoPage = new Page<>(page,pageSize);
        LambdaQueryWrapper<Que> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Que::getIsDeleted,0);
        queryWrapper.like(StringUtils.isNotEmpty(name),Que::getQueName,name);

        queryWrapper.orderByDesc(Que::getQueLike);
        queService.page(pageInfo,queryWrapper);

        //对象拷贝
        BeanUtils.copyProperties(pageInfo,queDtoPage);
        List<Que> records = pageInfo.getRecords();
        List<QueDto> list = records.stream().map((item) -> {

            QueDto queDto = new QueDto();
            BeanUtils.copyProperties(item,queDto);

            queDto.setQueId(item.getQueId());
            //获取种类名字
            Integer categoryId = item.getCategoryId();
            String categoryName = myDao.getById(categoryId);
            if(categoryName != null)
                queDto.setCategoryName(categoryName);
            //获取用户名
            Integer userId = item.getUserId();
            String userName = myDao.getUserById(userId);
            if(userName != null)
                queDto.setUserName(userName);
            return queDto;
        }).collect(Collectors.toList());
        queDtoPage.setRecords(list);
        return R.success(queDtoPage);
    }

    /**
     * 我的题目分页查询
     * 按queLike排序
     * @param
     * @param
     * @return
     */
    @GetMapping("/page3")
    public R<Page> page3(int page,int pageSize, Integer userId){
        //设置分页pageInfo
        Page<Que> pageInfo  = new Page<>(page,pageSize);
        Page<QueDto> queDtoPage = new Page<>(page,pageSize);
        LambdaQueryWrapper<Que> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Que::getIsDeleted,0);
        queryWrapper.eq(Que::getUserId,userId);
        queryWrapper.orderByDesc(Que::getQueLike);
        queService.page(pageInfo,queryWrapper);

        //对象拷贝
        BeanUtils.copyProperties(pageInfo,queDtoPage);
        List<Que> records = pageInfo.getRecords();
        List<QueDto> list = records.stream().map((item) -> {
            QueDto queDto = new QueDto();
            BeanUtils.copyProperties(item,queDto);
            queDto.setQueId(item.getQueId());
            //获取种类名字
            Integer categoryId = item.getCategoryId();
            String categoryName = myDao.getById(categoryId);
            if(categoryName != null)
                queDto.setCategoryName(categoryName);
            //获取用户名
            String userName = myDao.getUserById(userId);
            if(userName != null)
                queDto.setUserName(userName);
            return queDto;
        }).collect(Collectors.toList());

        queDtoPage.setRecords(list);
        return R.success(queDtoPage);
    }


    /**
     * 发布问题
     * @param
     * @return
     */
    @PostMapping("/put")
    public R<String> saveQuestion(HttpServletRequest request, @RequestBody QueDto queDto){
        //从dto中取出属于que的变量
        String queName = queDto.getQueName();
        String queDescription = queDto.getQueDescription();
        String categoryName = queDto.getCategoryName();
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Category::getCategoryName,categoryName);
        Category c = categoryService.getOne(queryWrapper);
        Integer categoryId = c.getCategoryId();
        Que que = new Que();
        que.setQueName(queName);
        que.setQueDescription(queDescription);
        que.setCategoryId(categoryId);
        Integer id = (Integer) request.getSession().getAttribute("user");
        que.setUserId(id);
        queService.save(que);
        return R.success("题目发布成功");
    }

    /**
     * 点击查看题目
     * @param
     * @return
     */
    @GetMapping("/search")
    public R<Que> searchQue(Long queId){
        System.out.println("queId:" + queId);
        Que q = queService.getById(queId);
        System.out.println("q:" + q);
        return R.success(q);
    }
    /**
     * 喜欢题目
     * @param
     * @return
     */
    @PostMapping("/like")
    public R<String> queLike(@RequestBody Que que){
        Que q = myDao.getQueById(que.getQueId());
        q.setQueLike(q.getQueLike() + 1);
        queService.updateById(q);
        return R.success("题目点赞成功");
    }


    /**
     * 回答题目
     * @param
     * @return
     */
    @PostMapping("/answer")
    public R<String> saveAnswer(HttpServletRequest request,@RequestBody Answer answer){
        Integer userId = (Integer) request.getSession().getAttribute("user");
        answer.setUserId(userId);
        answerService.save(answer);
        return R.success("题目回答成功");
    }



    /**
     * 删除题目
     * @param que
     * @return
     */
    @PostMapping("/delete")
    public R<String> delete(@RequestBody Que que){
        que.setIsDeleted(1);
        queService.updateById(que);
        return R.success("题目删除成功");
    }


}
