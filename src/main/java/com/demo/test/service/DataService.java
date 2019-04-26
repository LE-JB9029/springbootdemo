package com.demo.test.service;


import com.demo.test.domain.Data;

import java.util.List;

public interface DataService {
    int insert(Data condition);

    int update(Data condition);

    void save(Data condition);

    int deleteById(Long id);

    Data getById(Long id);

    List<Data> findAll(Data condition);
}
