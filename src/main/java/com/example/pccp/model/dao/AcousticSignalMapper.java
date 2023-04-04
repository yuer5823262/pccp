package com.example.pccp.model.dao;

import com.example.pccp.model.pojo.AcousticSignal;
import com.example.pccp.model.query.AcousticSignalQue;
import com.example.pccp.model.vo.AcousticSignalVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface  AcousticSignalMapper {
    int deleteByPrimaryKey(@Param("ids") Integer[] ids);

    int insert(AcousticSignal record);

    int insertSelective(AcousticSignal record);

    AcousticSignal selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AcousticSignal record);

    int updateByPrimaryKey(AcousticSignal record);

    AcousticSignal selectByPipeId(Integer pipeId);

    List<AcousticSignalVO> selectListQue(AcousticSignalQue acousticSignalQue);

    List<AcousticSignal> selectListByIds(@Param("ids")Integer[] ids);
}