package com.example.crud.service;

import com.example.crud.dto.UserCreateDTO;
import com.example.crud.dto.UserQueryDTO;
import com.example.crud.dto.UserUpdateDTO;
import com.example.crud.entity.User;
import com.example.crud.repository.UserRepository;
import com.example.crud.vo.PageResult;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户服务
 */
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    /**
     * 创建用户
     *
     * @param dto 创建请求体
     * @return 创建后的用户
     */
    @Transactional(rollbackFor = Exception.class)
    public User create(UserCreateDTO dto) {
        // 校验用户名唯一
        User existing = userRepository.findByUsername(dto.getUsername());
        if (existing != null) {
            throw new IllegalArgumentException("用户名已存在: " + dto.getUsername());
        }
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPhone(dto.getPhone());
        user.setStatus(dto.getStatus());
        return userRepository.save(user);
    }

    /**
     * 根据ID查询用户
     *
     * @param id 用户ID
     * @return 用户实体
     */
    public User getById(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("用户不存在: " + id));
    }

    /**
     * 分页查询用户
     *
     * @param query 查询条件
     * @return 分页结果
     */
    public PageResult<User> page(UserQueryDTO query) {
        Specification<User> spec = (root, cq, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (query.getUsername() != null && !query.getUsername().isEmpty()) {
                predicates.add(cb.like(root.get("username"), "%" + query.getUsername() + "%"));
            }
            if (query.getStatus() != null) {
                predicates.add(cb.equal(root.get("status"), query.getStatus()));
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };

        Sort sort = Sort.by(Sort.Direction.DESC, "createTime");
        PageRequest pageRequest = PageRequest.of(query.getPage() - 1, query.getSize(), sort);
        Page<User> page = userRepository.findAll(spec, pageRequest);
        return PageResult.of(page);
    }

    /**
     * 更新用户
     *
     * @param id  用户ID
     * @param dto 更新请求体
     * @return 更新后的用户
     */
    @Transactional(rollbackFor = Exception.class)
    public User update(String id, UserUpdateDTO dto) {
        User user = getById(id);
        if (dto.getEmail() != null) {
            user.setEmail(dto.getEmail());
        }
        if (dto.getPhone() != null) {
            user.setPhone(dto.getPhone());
        }
        if (dto.getStatus() != null) {
            user.setStatus(dto.getStatus());
        }
        return userRepository.save(user);
    }

    /**
     * 删除用户
     *
     * @param id 用户ID
     */
    @Transactional(rollbackFor = Exception.class)
    public void delete(String id) {
        if (!userRepository.existsById(id)) {
            throw new IllegalArgumentException("用户不存在: " + id);
        }
        userRepository.deleteById(id);
    }
}
