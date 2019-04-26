package com.demo.test.mapper;

import com.demo.test.domain.Data;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DataMapper {
    int insert(Data condition);

    int update(Data condition);

    int deleteById(Long id);

    Data getById(Long id);

    List<Data> findAll(Data condition);
}
