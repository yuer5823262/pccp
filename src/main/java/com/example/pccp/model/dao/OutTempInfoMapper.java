package com.example.pccp.model.dao;

import com.example.pccp.model.pojo.OutTempInfo;
import com.example.pccp.model.query.OutTempInfoQue;
import com.example.pccp.model.vo.OutTempInfoVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OutTempInfoMapper {
    int deleteByPrimaryKey(@Param("ids") Integer[] id);

    int insert(OutTempInfo record);

    int insertSelective(OutTempInfo record);

    OutTempInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OutTempInfo record);

    int updateByPrimaryKey(OutTempInfo record);

    List<OutTempInfoVO> selectListQue(OutTempInfoQue outTempInfoQue);

    List<OutTempInfo> selectListByIds(@Param("ids")Integer[] ids);
}