package com.demo.test.dao;

import com.demo.common.datasources.DataSourceNames;
import com.demo.common.datasources.annotation.DataSource;
import com.demo.test.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
@DataSource(name = DataSourceNames.FIRST)
public interface UserDao {
    int insert(User condition);

    int update(User condition);

    int updateLastLoginTime(User condition);

    int deleteById(Long id);

    User getById(Long id);

    List<User> findAll(User condition);

    User findByUsername(String username);
}
