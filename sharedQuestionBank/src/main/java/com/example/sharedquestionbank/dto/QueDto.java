package com.example.sharedquestionbank.dto;
//@Author：ranxing
//@Time：2022/9/12   16:06
//@File：queDto.java

import lombok.Data;

@Data
public class QueDto {

    private Integer queId;

    private String queName;

    private String queDescription;

    private String categoryName;

    private Integer queLike;

    private String userName;

}
