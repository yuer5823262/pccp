package com.example.pccp.model.dao;

import com.example.pccp.model.pojo.AudioUrl;
import com.example.pccp.model.query.AudioUrQue;
import com.example.pccp.model.vo.AudioUrlVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AudioUrlMapper {
    int deleteByPrimaryKey(@Param("ids") Integer[] ids);

    int insert(AudioUrl record);

    int insertSelective(AudioUrl record);

    AudioUrl selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AudioUrl record);

    int updateByPrimaryKey(AudioUrl record);

    AudioUrl selectByPipeId(Integer pipeId);

    List<AudioUrl> selectListByIds(@Param("ids") Integer[] ids);

    List<AudioUrlVO> selectListQue(AudioUrQue audioUrlQue);
}