package com.demo.test.service.impl;

import com.demo.test.dao.UserDao;
import com.demo.test.domain.User;
import com.demo.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public int insert(User condition) {
        return userDao.insert(condition);
    }

    @Override
    public int update(User condition) {
        return userDao.update(condition);
    }

    @Override
    public int updateLastLoginTime(User condition) {
        return userDao.updateLastLoginTime(condition);
    }

    @Override
    public void save(User condition) {
        if (condition.getId() != null) {
            update(condition);
        } else {
            insert(condition);
        }
    }

    @Override
    public int deleteById(Long id) {
        return userDao.deleteById(id);
    }

    @Override
    public User getById(Long id) {
        return userDao.getById(id);
    }

    @Override
    public List<User> findAll(User condition) {
        return userDao.findAll(condition);
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public boolean checkPassword(User condition) {
        User tmp = findByUsername(condition.getUsername());
        if (tmp != null && condition.getPassword().equals(tmp.getPassword())) return true;
        return false;
    }
}
