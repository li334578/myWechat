package com.example.mywechat.dto;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @ClassName ResultBean
 * @Description 统一返回结果
 * @Author Wenbo Li
 * @Date 7/11/2022 下午 9:36
 * @Version 1.0
 */
@Data
@Accessors(chain = true)
public class ResultBean<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final int SUCCESS = 0;

    public static final int FAIL = 1;

    public static final int NO_PERMISSION = 2;

    private String msg = "success";

    private int code = SUCCESS;

    private T data;

    public ResultBean() {
        super();
    }

    public ResultBean(T data) {
        super();
        this.data = data;
    }

    public ResultBean(Throwable e) {
        super();
        this.msg = e.toString();
        this.code = FAIL;
    }

    public static <T> ResultBean<T> success() {
        return new ResultBean<T>().setCode(SUCCESS);
    }

    public static <T> ResultBean<T> success(T data) {
        return new ResultBean<>(data).setCode(SUCCESS);
    }

    public static <T> ResultBean<T> success(T data, String message) {
        return new ResultBean<>(data).setCode(SUCCESS).setMsg(message);
    }

    public static <T> ResultBean<T> error() {
        return new ResultBean<T>().setCode(FAIL);
    }

    public static <T> ResultBean<T> error(T data) {
        return new ResultBean<T>(data).setCode(FAIL);
    }

    public static <T> ResultBean<T> error(String message) {
        return new ResultBean<T>().setMsg(message).setCode(FAIL);
    }

    public static <T> ResultBean<T> error(T data, String message) {
        return new ResultBean<T>(data).setCode(FAIL).setMsg(message);
    }
}
