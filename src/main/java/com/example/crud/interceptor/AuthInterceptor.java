package com.example.crud.interceptor;

import com.example.crud.context.RequestContext;
import com.example.crud.exception.UnauthorizedException;
import com.example.crud.util.IpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 认证拦截器 - Token校验与请求信息提取
 *
 * Token格式：Authorization: Bearer <token>
 * 拦截器从Header中提取Token并校验，同时将用户信息和客户端IP存入RequestContext
 */
@Slf4j
@Component
public class AuthInterceptor implements HandlerInterceptor {

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String BEARER_PREFIX = "Bearer ";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 1. 提取Token
        String authHeader = request.getHeader(AUTHORIZATION_HEADER);
        String token = extractToken(authHeader);

        // 2. 校验Token是否存在
        if (!StringUtils.hasText(token)) {
            throw new UnauthorizedException("缺少认证Token，请先登录");
        }

        // 3. 校验Token有效性（此处为示例逻辑，实际项目中应替换为JWT解析或查询数据库/缓存）
        String userId = validateToken(token);

        // 4. 提取客户端IP等请求信息
        String clientIp = IpUtil.getClientIp(request);

        // 5. 构建请求上下文并存入ThreadLocal
        RequestContext ctx = new RequestContext();
        ctx.setUserId(userId);
        ctx.setToken(token);
        ctx.setClientIp(clientIp);
        ctx.setRequestUri(request.getRequestURI());
        ctx.setRequestTime(System.currentTimeMillis());
        RequestContext.set(ctx);

        log.info("请求认证通过 - URI: {}, 用户ID: {}, IP: {}", request.getRequestURI(), userId, clientIp);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        // 请求结束后清除ThreadLocal，防止内存泄漏
        RequestContext.clear();
    }

    /**
     * 从Authorization Header中提取Token
     *
     * @param authHeader Authorization请求头的值
     * @return Token字符串，不存在则返回null
     */
    private String extractToken(String authHeader) {
        if (StringUtils.hasText(authHeader) && authHeader.startsWith(BEARER_PREFIX)) {
            return authHeader.substring(BEARER_PREFIX.length()).trim();
        }
        return null;
    }

    /**
     * 校验Token有效性并返回用户ID
     * <p>
     * 示例实现：简单解析Token格式为 "userId:timestamp"
     * 实际项目中请替换为：
     * - JWT解析（如 jjwt 库）
     * - 查询Redis/数据库中的Token记录
     * - 调用SSO/OAuth服务校验
     * </p>
     *
     * @param token 请求携带的Token
     * @return 用户ID
     */
    private String validateToken(String token) {
        // TODO: 替换为实际的Token校验逻辑（JWT / Redis / 数据库查询）
        // 以下为示例逻辑，Token格式：userId:timestamp
        if (token.contains(":")) {
            String userId = token.split(":")[0];
            if (StringUtils.hasText(userId)) {
                return userId;
            }
        }
        throw new UnauthorizedException("Token无效或已过期，请重新登录");
    }
}
