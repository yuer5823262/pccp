package com.example.pccp.service.impl;

import com.example.pccp.exception.PccpException;
import com.example.pccp.exception.PccpExceptionEnum;
import com.example.pccp.model.pojo.TempInfo;
import com.example.pccp.model.query.TempInfoQue;
import com.example.pccp.model.request.AddTempInfoReq;
import com.example.pccp.model.request.UpdateTempInfoReq;
import com.example.pccp.model.vo.TempInfoVO;
import com.example.pccp.service.TempInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TempInfoServiceImpl implements TempInfoService {
    @Autowired
    com.example.pccp.model.dao.TempInfoMapper TempInfoMapper;
    @Override
    public void add(AddTempInfoReq addTempInfoReq, String username) {
        TempInfo TempInfo = new TempInfo();
        BeanUtils.copyProperties(addTempInfoReq,TempInfo);
        int count = TempInfoMapper.insertSelective(TempInfo);
        if (count == 0){
            throw new PccpException(PccpExceptionEnum.ADD_FAILED);
        }
    }

    @Override
    public void update(UpdateTempInfoReq updateTempInfoReq) {
        TempInfo TempInfoCheck =
                TempInfoMapper.selectByPrimaryKey(updateTempInfoReq.getId());
        if (TempInfoCheck == null) {
            throw new PccpException(PccpExceptionEnum.ADD_FAILED);
        }
        BeanUtils.copyProperties(updateTempInfoReq,TempInfoCheck);

        int count = TempInfoMapper.updateByPrimaryKeySelective(TempInfoCheck);
        if (count == 0){
            throw new PccpException(PccpExceptionEnum.UPDATE_FAILED);
        }
    }

    @Override
    public void delete(Integer[] ids) {
        int count = TempInfoMapper.deleteByPrimaryKey(ids);
        if (count !=ids.length){
            throw new PccpException(PccpExceptionEnum.DELETE_FAILED);
        }
    }

    @Override
    public PageInfo orUserListQue(TempInfoQue TempInfoQue) {
        PageHelper.startPage(TempInfoQue.getPageNum(), TempInfoQue.getPageSize());
        List<TempInfoVO> TempInfoList = TempInfoMapper.selectListQue(TempInfoQue);
        PageInfo pageInfo = new PageInfo(TempInfoList);
        return pageInfo;
    }

    @Override
    public List<TempInfoVO> exportList(TempInfoQue TempInfoQue) {
        List<TempInfoVO> TempInfoList = TempInfoMapper.selectListQue(TempInfoQue);
        return TempInfoList;
    }

    @Override
    public List<TempInfo> listByIds(Integer[] ids) {
        List<TempInfo> categoryList = TempInfoMapper.selectListByIds(ids);
        return categoryList;
    }
}
