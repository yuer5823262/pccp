package com.example.pccp.model.dao;

import com.example.pccp.model.pojo.PccpLog;
import com.example.pccp.model.query.PccpLogQue;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PccpLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PccpLog record);

    int insertSelective(PccpLog record);

    PccpLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PccpLog record);

    int updateByPrimaryKey(PccpLog record);

    List<PccpLog> selectList(PccpLogQue damPourLogQue);
}