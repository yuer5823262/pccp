package com.example.pccp.model.dao;

import com.example.pccp.model.pojo.PccpEvent;
import com.example.pccp.model.query.PccpEventQue;
import com.example.pccp.model.vo.PccpEventVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PccpEventMapper {
    int deleteByPrimaryKey(@Param("ids") Integer[] ids);

    int insert(PccpEvent record);

    int insertSelective(PccpEvent record);

    PccpEvent selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PccpEvent record);

    int updateByPrimaryKey(PccpEvent record);

    PccpEvent selectByPipeId(Integer pipeId);

    List<PccpEventVO> selectListQue(PccpEventQue pccpEventQue);

    List<PccpEvent> selectListByIds(@Param("ids") Integer[] ids);
}