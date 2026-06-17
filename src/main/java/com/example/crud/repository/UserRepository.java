package com.example.crud.repository;

import com.example.crud.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * 用户 Repository
 */
@Repository
public interface UserRepository extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {

    /**
     * 根据用户名查询
     *
     * @param username 用户名
     * @return 用户实体
     */
    User findByUsername(String username);
}
