package com.example.pccp.model.dao;

import com.example.pccp.model.pojo.TempInfo;
import com.example.pccp.model.query.TempInfoQue;
import com.example.pccp.model.vo.TempInfoVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TempInfoMapper {
    int deleteByPrimaryKey(@Param("ids") Integer[] ids);

    int insert(TempInfo record);

    int insertSelective(TempInfo record);

    TempInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TempInfo record);

    int updateByPrimaryKey(TempInfo record);

    List<TempInfoVO> selectListQue(TempInfoQue tempInfoQue);

    List<TempInfo> selectListByIds(@Param("ids") Integer[] ids);
}