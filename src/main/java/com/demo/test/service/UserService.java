package com.demo.test.service;

import com.demo.test.domain.User;

import java.util.List;

public interface UserService {

    int insert(User condition);

    int update(User condition);

    int updateLastLoginTime(User condition);

    void save(User condition);

    int deleteById(Long id);

    User getById(Long id);

    List<User> findAll(User condition);

    User findByUsername(String username);

    boolean checkPassword(User condition);
}
