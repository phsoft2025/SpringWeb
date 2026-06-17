package com.example.crud.context;

import lombok.Data;

/**
 * 请求上下文 - 基于ThreadLocal存储当前请求的用户信息
 */
@Data
public class RequestContext {

    private static final ThreadLocal<RequestContext> CONTEXT = new ThreadLocal<>();

    /** 当前用户ID */
    private String userId;

    /** 当前用户名 */
    private String username;

    /** 请求Token */
    private String token;

    /** 客户端IP地址 */
    private String clientIp;

    /** 请求URI */
    private String requestUri;

    /** 请求时间戳 */
    private long requestTime;

    /**
     * 设置当前请求上下文
     */
    public static void set(RequestContext ctx) {
        CONTEXT.set(ctx);
    }

    /**
     * 获取当前请求上下文
     */
    public static RequestContext get() {
        return CONTEXT.get();
    }

    /**
     * 清除当前请求上下文（请求结束后必须调用，防止内存泄漏）
     */
    public static void clear() {
        CONTEXT.remove();
    }
}
