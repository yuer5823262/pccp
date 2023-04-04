package com.example.pccp.model.dao;

import com.example.pccp.model.pojo.CameraUrl;
import com.example.pccp.model.query.CameraUrlQue;
import com.example.pccp.model.vo.CameraUrlVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CameraUrlMapper {
    int deleteByPrimaryKey(@Param("ids") Integer[] ids);

    int insert(CameraUrl record);

    int insertSelective(CameraUrl record);

    CameraUrl selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CameraUrl record);

    int updateByPrimaryKey(CameraUrl record);

    CameraUrl selectByPipeId(Integer pipeId);

    List<CameraUrlVO> selectListQue(CameraUrlQue cameraUrlQue);

    List<CameraUrl> selectListByIds(@Param("ids") Integer[] ids);
}