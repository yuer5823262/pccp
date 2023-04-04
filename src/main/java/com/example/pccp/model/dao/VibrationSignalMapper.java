package com.example.pccp.model.dao;

import com.example.pccp.model.pojo.VibrationSignal;
import com.example.pccp.model.query.VibrationSignalQue;
import com.example.pccp.model.vo.VibrationSignalVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VibrationSignalMapper {
    int deleteByPrimaryKey(@Param("ids") Integer[] ids);

    int insert(VibrationSignal record);

    int insertSelective(VibrationSignal record);

    VibrationSignal selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(VibrationSignal record);

    int updateByPrimaryKey(VibrationSignal record);

    VibrationSignal selectByPipeId(Integer pipeId);

    List<VibrationSignalVO> selectListQue(VibrationSignalQue vibrationSignalQue);

    List<VibrationSignal> selectListByIds(@Param("ids")Integer[] ids);
}