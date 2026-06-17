package com.example.crud.dto;

import lombok.Data;

import javax.validation.constraints.Size;

/**
 * 更新用户请求体
 */
@Data
public class UserUpdateDTO {

    /** 邮箱 */
    @Size(max = 100, message = "邮箱最长100个字符")
    private String email;

    /** 手机号 */
    @Size(max = 20, message = "手机号最长20个字符")
    private String phone;

    /** 状态：0-禁用，1-启用 */
    private Integer status;
}
