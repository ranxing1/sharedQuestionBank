package com.example.sharedquestionbank.entity;
//@Author：ranxing
//@Time：2022/9/11   18:08
//@File：Answer.java

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class Answer implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer answerId;

    private Integer QueId;

    private String answerDescription;

    private Integer answerLike;

    private Integer userId;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    private Integer isDeleted;
}
