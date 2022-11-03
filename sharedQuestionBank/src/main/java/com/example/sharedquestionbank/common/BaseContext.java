package com.example.sharedquestionbank.common;
//@Author：ranxing
//@Time：2022/7/11   10:47
//@File：BaseContext.java

/**
 * 基于ThreadLocal封装工具类，用于获取当前用户id
 */
public class BaseContext {
    private static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    public static void setCurrentId(Long id){
        threadLocal.set(id);
    }

    public static Long getCurrentId(){
        return threadLocal.get();
    }
}
