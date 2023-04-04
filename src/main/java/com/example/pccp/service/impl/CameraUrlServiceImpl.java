package com.example.pccp.service.impl;

import com.example.pccp.exception.PccpException;
import com.example.pccp.exception.PccpExceptionEnum;
import com.example.pccp.model.dao.AudioUrlMapper;
import com.example.pccp.model.dao.CameraUrlMapper;
import com.example.pccp.model.pojo.CameraUrl;
import com.example.pccp.model.pojo.CameraUrl;
import com.example.pccp.model.query.CameraUrlQue;
import com.example.pccp.model.request.AddCameraUrlReq;
import com.example.pccp.model.request.UpdateCameraUrlReq;
import com.example.pccp.model.vo.CameraUrlVO;
import com.example.pccp.model.vo.CameraUrlVO;
import com.example.pccp.service.CameraUrlService;
import com.example.pccp.util.TimeUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CameraUrlServiceImpl implements CameraUrlService {
    @Autowired
    CameraUrlMapper CameraUrlMapper;
    @Override
    public void add(AddCameraUrlReq addCameraUrlReq, String username) {
        CameraUrl CameraUrlCheck = CameraUrlMapper.selectByPipeId(addCameraUrlReq.getPipeId());
        if (CameraUrlCheck != null) {

            throw new PccpException(PccpExceptionEnum.NAME_EXISTED);
        }
        CameraUrl CameraUrl = new CameraUrl();
        BeanUtils.copyProperties(addCameraUrlReq,CameraUrl);
        CameraUrl.setCreateOperator(username);
        CameraUrl.setCreateTime(TimeUtils.getNowTime());
        int count = CameraUrlMapper.insertSelective(CameraUrl);
        if (count == 0){
            throw new PccpException(PccpExceptionEnum.ADD_FAILED);
        }
    }

    @Override
    public void update(UpdateCameraUrlReq updateCameraUrlReq) {
        CameraUrl CameraUrl =
                CameraUrlMapper.selectByPrimaryKey(updateCameraUrlReq.getId());
        if (CameraUrl == null) {
            throw new PccpException(PccpExceptionEnum.UPDATE_FAILED);
        }
        CameraUrl CameraUrlCheck =
                CameraUrlMapper.selectByPipeId(updateCameraUrlReq.getPipeId());
        if (CameraUrlCheck != null) {
            throw new PccpException(PccpExceptionEnum.NAME_EXISTED);
        }
        BeanUtils.copyProperties(updateCameraUrlReq,CameraUrl);

        int count = CameraUrlMapper.updateByPrimaryKeySelective(CameraUrl);
        if (count == 0){
            throw new PccpException(PccpExceptionEnum.UPDATE_FAILED);
        }
    }

    @Override
    public void delete(Integer[] ids) {
        int count = CameraUrlMapper.deleteByPrimaryKey(ids);
        if (count !=ids.length){
            throw new PccpException(PccpExceptionEnum.DELETE_FAILED);
        }
    }

    @Override
    public PageInfo orUserListQue(CameraUrlQue cameraUrlQue) {
        PageHelper.startPage(cameraUrlQue.getPageNum(), cameraUrlQue.getPageSize());
        List<CameraUrlVO> CameraUrlList = CameraUrlMapper.selectListQue(cameraUrlQue);
        PageInfo pageInfo = new PageInfo(CameraUrlList);
        return pageInfo;
    }

    @Override
    public List<CameraUrlVO> exportList(CameraUrlQue cameraUrlQue) {
        List<CameraUrlVO> CameraUrlList = CameraUrlMapper.selectListQue(cameraUrlQue);
        return CameraUrlList;
    }

    @Override
    public List<CameraUrl> listByIds(Integer[] ids) {
        List<CameraUrl> categoryList = CameraUrlMapper.selectListByIds(ids);
        return categoryList;
    }
}
