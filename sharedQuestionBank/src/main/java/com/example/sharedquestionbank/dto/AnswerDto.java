package com.example.sharedquestionbank.dto;
//@Author：ranxing
//@Time：2022/9/13   14:54
//@File：AnswerDto.java

import lombok.Data;

@Data
public class AnswerDto {
    private Integer answerId;

    private Integer QueId;

    private String answerDescription;

    private Integer answerLike;

    private String userName;
}
