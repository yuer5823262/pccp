package com.example.pccp.model.dao;

import com.example.pccp.model.pojo.FlowInfo;
import com.example.pccp.model.query.FlowInfoQue;
import com.example.pccp.model.vo.FlowInfoVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlowInfoMapper {
    int deleteByPrimaryKey(@Param("ids") Integer[] ids);

    int insert(FlowInfo record);

    int insertSelective(FlowInfo record);

    FlowInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FlowInfo record);

    int updateByPrimaryKey(FlowInfo record);

    List<FlowInfoVO> selectListQue(FlowInfoQue flowInfoQue);

    List<FlowInfo> selectListByIds(@Param("ids")Integer[] ids);
}