package com.example.pccp.model.dao;

import com.example.pccp.model.pojo.Pipe;
import com.example.pccp.model.query.PipeQue;
import com.example.pccp.model.vo.PipeVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PipeMapper {
    int deleteByPrimaryKey(@Param("ids") Integer[] ids);

    int insert(Pipe record);

    int insertSelective(Pipe record);

    Pipe selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Pipe record);

    int updateByPrimaryKey(Pipe record);

    Pipe selectByName(String name);

    List<PipeVO> selectListQue(PipeQue pipeQue);

    List<Pipe> selectListByIds(@Param("ids") Integer[] ids);
}