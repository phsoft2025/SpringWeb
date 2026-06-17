package com.example.crud.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * 用户实体
 */
@Data
@Entity
@Table(name = "sys_user")
public class User {

    /** 主键ID */
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(length = 36)
    private String id;

    /** 用户名 */
    @Column(nullable = false, length = 50, unique = true)
    private String username;

    /** 邮箱 */
    @Column(length = 100)
    private String email;

    /** 手机号 */
    @Column(length = 20)
    private String phone;

    /** 状态：0-禁用，1-启用 */
    @Column(nullable = false)
    private Integer status = 1;

    /** 创建时间 */
    @Column(nullable = false, updatable = false)
    private LocalDateTime createTime;

    /** 更新时间 */
    @Column(nullable = false)
    private LocalDateTime updateTime;

    /**
     * 插入前自动填充时间
     */
    @javax.persistence.PrePersist
    public void prePersist() {
        LocalDateTime now = LocalDateTime.now();
        this.createTime = now;
        this.updateTime = now;
    }

    /**
     * 更新前自动填充时间
     */
    @javax.persistence.PreUpdate
    public void preUpdate() {
        this.updateTime = LocalDateTime.now();
    }
}
