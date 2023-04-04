package com.example.pccp.service.impl;

import com.example.pccp.exception.PccpException;
import com.example.pccp.exception.PccpExceptionEnum;
import com.example.pccp.model.pojo.OutTempInfo;
import com.example.pccp.model.query.OutTempInfoQue;
import com.example.pccp.model.request.AddOutTempInfoReq;
import com.example.pccp.model.request.UpdateOutTempInfoReq;
import com.example.pccp.model.vo.OutTempInfoVO;
import com.example.pccp.service.OutTempInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OutTempInfoServiceImpl implements OutTempInfoService {
    @Autowired
    com.example.pccp.model.dao.OutTempInfoMapper OutTempInfoMapper;
    @Override
    public void add(AddOutTempInfoReq addOutTempInfoReq, String username) {
        OutTempInfo OutTempInfo = new OutTempInfo();
        BeanUtils.copyProperties(addOutTempInfoReq,OutTempInfo);
        int count = OutTempInfoMapper.insertSelective(OutTempInfo);
        if (count == 0){
            throw new PccpException(PccpExceptionEnum.ADD_FAILED);
        }
    }

    @Override
    public void update(UpdateOutTempInfoReq updateOutTempInfoReq) {
        OutTempInfo OutTempInfoCheck =
                OutTempInfoMapper.selectByPrimaryKey(updateOutTempInfoReq.getId());
        if (OutTempInfoCheck == null) {
            throw new PccpException(PccpExceptionEnum.ADD_FAILED);
        }
        BeanUtils.copyProperties(updateOutTempInfoReq,OutTempInfoCheck);

        int count = OutTempInfoMapper.updateByPrimaryKeySelective(OutTempInfoCheck);
        if (count == 0){
            throw new PccpException(PccpExceptionEnum.UPDATE_FAILED);
        }
    }

    @Override
    public void delete(Integer[] ids) {
        int count = OutTempInfoMapper.deleteByPrimaryKey(ids);
        if (count !=ids.length){
            throw new PccpException(PccpExceptionEnum.DELETE_FAILED);
        }
    }

    @Override
    public PageInfo orUserListQue(OutTempInfoQue OutTempInfoQue) {
        PageHelper.startPage(OutTempInfoQue.getPageNum(), OutTempInfoQue.getPageSize());
        List<OutTempInfoVO> OutTempInfoList = OutTempInfoMapper.selectListQue(OutTempInfoQue);
        PageInfo pageInfo = new PageInfo(OutTempInfoList);
        return pageInfo;
    }

    @Override
    public List<OutTempInfoVO> exportList(OutTempInfoQue OutTempInfoQue) {
        List<OutTempInfoVO> OutTempInfoList = OutTempInfoMapper.selectListQue(OutTempInfoQue);
        return OutTempInfoList;
    }

    @Override
    public List<OutTempInfo> listByIds(Integer[] ids) {
        List<OutTempInfo> categoryList = OutTempInfoMapper.selectListByIds(ids);
        return categoryList;
    }
}
