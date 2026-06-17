package com.example.crud.controller;

import com.example.crud.dto.UserCreateDTO;
import com.example.crud.dto.UserQueryDTO;
import com.example.crud.dto.UserUpdateDTO;
import com.example.crud.entity.User;
import com.example.crud.service.UserService;
import com.example.crud.vo.PageResult;
import com.example.crud.vo.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户控制器
 */
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * 创建用户
     *
     * @param dto 创建请求体
     * @return 创建后的用户
     */
    @PostMapping
    public Result<User> create(@Validated @RequestBody UserCreateDTO dto) {
        return Result.ok(userService.create(dto));
    }

    /**
     * 根据ID查询用户
     *
     * @param id 用户ID
     * @return 用户信息
     */
    @GetMapping("/{id}")
    public Result<User> getById(@PathVariable String id) {
        return Result.ok(userService.getById(id));
    }

    /**
     * 分页查询用户列表
     *
     * @param query 查询条件
     * @return 分页结果
     */
    @GetMapping
    public Result<PageResult<User>> page(UserQueryDTO query) {
        return Result.ok(userService.page(query));
    }

    /**
     * 更新用户
     *
     * @param id  用户ID
     * @param dto 更新请求体
     * @return 更新后的用户
     */
    @PutMapping("/{id}")
    public Result<User> update(@PathVariable String id, @Validated @RequestBody UserUpdateDTO dto) {
        return Result.ok(userService.update(id, dto));
    }

    /**
     * 删除用户
     *
     * @param id 用户ID
     */
    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable String id) {
        userService.delete(id);
        return Result.ok();
    }
}
