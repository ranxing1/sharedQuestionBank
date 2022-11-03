package com.example.sharedquestionbank.common;
//@Author：ranxing
//@Time：2022/7/11   10:32
//@File：MyMetaObjectHandler.java

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    /**
     * 插入操作，自动填充
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("公共字段自动填充[insert]...");
        metaObject.setValue("createTime",LocalDateTime.now());
        metaObject.setValue("updateTime",LocalDateTime.now());
//        metaObject.setValue("userId",BaseContext.getCurrentId());
//        metaObject.setValue("answerUserId",BaseContext.getCurrentId());
    }

    /**
     * 添加操作，自动填充
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("公共字段自动填充[update]...");
        metaObject.setValue("updateTime",LocalDateTime.now());
//        metaObject.setValue("answerUser",BaseContext.getCurrentId());
    }
}
