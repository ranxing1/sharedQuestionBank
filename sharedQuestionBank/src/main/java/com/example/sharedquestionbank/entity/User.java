package com.example.sharedquestionbank.entity;
//@Author：ranxing
//@Time：2022/8/17   18:10
//@File：User.java

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer userId;

    private String userName;

    private String userPwd;

    private Integer isAdministrator;

    private Integer isDeleted;
}
