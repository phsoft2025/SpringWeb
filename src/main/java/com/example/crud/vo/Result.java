package com.example.crud.vo;

import lombok.Data;

/**
 * 统一响应结果
 *
 * @param <T> 数据类型
 */
@Data
public class Result<T> {

    /** 状态码 */
    private int code;

    /** 消息 */
    private String message;

    /** 数据 */
    private T data;

    private Result() {}

    /**
     * 成功响应
     *
     * @param data 数据
     * @param <T>  数据类型
     * @return 成功结果
     */
    public static <T> Result<T> ok(T data) {
        Result<T> r = new Result<>();
        r.setCode(200);
        r.setMessage("success");
        r.setData(data);
        return r;
    }

    /**
     * 成功响应（无数据）
     *
     * @return 成功结果
     */
    public static <T> Result<T> ok() {
        return ok(null);
    }

    /**
     * 失败响应
     *
     * @param code    状态码
     * @param message 消息
     * @return 失败结果
     */
    public static <T> Result<T> fail(int code, String message) {
        Result<T> r = new Result<>();
        r.setCode(code);
        r.setMessage(message);
        return r;
    }

    /**
     * 失败响应（默认400）
     *
     * @param message 消息
     * @return 失败结果
     */
    public static <T> Result<T> fail(String message) {
        return fail(400, message);
    }
}
