package com.example.sharedquestionbank.entity;
//@Author：ranxing
//@Time：2022/8/17   17:37
//@File：Que.java

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class Que implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer queId;

    private String queName;

    private String queDescription;

    private Integer categoryId;

    private Integer queLike;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    private Integer userId;


    //是否删除
    private Integer isDeleted;
}
