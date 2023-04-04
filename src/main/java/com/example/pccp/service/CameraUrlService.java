package com.example.pccp.service;

import com.example.pccp.model.pojo.CameraUrl;
import com.example.pccp.model.query.CameraUrlQue;
import com.example.pccp.model.request.AddCameraUrlReq;
import com.example.pccp.model.request.UpdateCameraUrlReq;
import com.example.pccp.model.vo.CameraUrlVO;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface CameraUrlService {
    void add(AddCameraUrlReq addCameraUrlReq, String username);

    void update(UpdateCameraUrlReq updateCameraUrlReq);

    void delete(Integer[] ids);

    PageInfo orUserListQue(CameraUrlQue cameraUrlQue);

    List<CameraUrlVO> exportList(CameraUrlQue cameraUrlQue);

    List<CameraUrl> listByIds(Integer[] ids);
}
