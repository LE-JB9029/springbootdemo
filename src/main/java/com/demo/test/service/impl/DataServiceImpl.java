package com.demo.test.service.impl;

import com.demo.test.dao.DataDao;
import com.demo.test.domain.Data;
import com.demo.test.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataServiceImpl implements DataService {

    @Autowired
    private DataDao dataDao;

    @Override
    public int insert(Data condition) {
        return dataDao.insert(condition);
    }

    @Override
    public int update(Data condition) {
        return dataDao.update(condition);
    }

    @Override
    public void save(Data condition) {
        if (condition.getId() != null) {
            update(condition);
        } else {
            insert(condition);
        }
    }

    @Override
    public int deleteById(Long id) {
        return dataDao.deleteById(id);
    }

    @Override
    public Data getById(Long id) {
        return dataDao.getById(id);
    }

    @Override
    public List<Data> findAll(Data condition) {
        return dataDao.findAll(condition);
    }
}
