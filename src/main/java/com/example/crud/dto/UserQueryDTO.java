package com.example.crud.dto;

import lombok.Data;

/**
 * 用户查询条件
 */
@Data
public class UserQueryDTO {

    /** 用户名（模糊查询） */
    private String username;

    /** 状态：0-禁用，1-启用 */
    private Integer status;

    /** 当前页码，从1开始 */
    private Integer page = 1;

    /** 每页条数 */
    private Integer size = 10;
}
