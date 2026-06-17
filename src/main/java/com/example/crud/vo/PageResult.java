package com.example.crud.vo;

import lombok.Data;

import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 分页结果
 *
 * @param <T> 数据类型
 */
@Data
public class PageResult<T> {

    /** 数据列表 */
    private List<T> list;

    /** 总条数 */
    private Long total;

    /** 当前页码 */
    private Integer page;

    /** 每页条数 */
    private Integer size;

    /**
     * 从 Spring Data Page 构建分页结果
     *
     * @param page Spring Data 分页对象
     * @param <T>  数据类型
     * @return 分页结果
     */
    public static <T> PageResult<T> of(Page<T> page) {
        PageResult<T> result = new PageResult<>();
        result.setList(page.getContent());
        result.setTotal(page.getTotalElements());
        result.setPage(page.getNumber() + 1);
        result.setSize(page.getSize());
        return result;
    }
}
