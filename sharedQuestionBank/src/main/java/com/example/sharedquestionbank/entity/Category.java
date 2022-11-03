package com.example.sharedquestionbank.entity;
//@Author：ranxing
//@Time：2022/8/17   18:07
//@File：Category.java

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

@Data
public class Category implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer categoryId;

    private String categoryName;

}
