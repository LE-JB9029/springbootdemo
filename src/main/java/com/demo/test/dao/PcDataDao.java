package com.demo.test.dao;

import com.demo.common.datasources.DataSourceNames;
import com.demo.common.datasources.annotation.DataSource;
import com.demo.test.domain.PcData;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
@DataSource(name = DataSourceNames.SECOND)
public interface PcDataDao {
    int insert(PcData condition);

    int update(PcData condition);

    int deleteById(Long id);

    PcData getById(Long id);

    List<PcData> findAll(PcData condition);
}
