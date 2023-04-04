package com.example.pccp.service.impl;

import com.example.pccp.exception.PccpException;
import com.example.pccp.exception.PccpExceptionEnum;
import com.example.pccp.model.dao.AcousticSignalMapper;
import com.example.pccp.model.pojo.AcousticSignal;
import com.example.pccp.model.query.AcousticSignalQue;
import com.example.pccp.model.request.AddAcousticSignalReq;
import com.example.pccp.model.request.UpdateAcousticSignalReq;
import com.example.pccp.model.vo.AcousticSignalVO;
import com.example.pccp.service.AcousticSignalService;
import com.example.pccp.util.TimeUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AcousticSignalServiceImpl implements AcousticSignalService {
    @Autowired
    AcousticSignalMapper AcousticSignalMapper;
    @Override
    public void add(AddAcousticSignalReq addAcousticSignalReq, String username) {
        AcousticSignal AcousticSignalCheck = AcousticSignalMapper.selectByPipeId(addAcousticSignalReq.getPipeId());
        if (AcousticSignalCheck != null) {

            throw new PccpException(PccpExceptionEnum.NAME_EXISTED);
        }
        AcousticSignal AcousticSignal = new AcousticSignal();
        BeanUtils.copyProperties(addAcousticSignalReq,AcousticSignal);
        int count = AcousticSignalMapper.insertSelective(AcousticSignal);
        if (count == 0){
            throw new PccpException(PccpExceptionEnum.ADD_FAILED);
        }
    }

    @Override
    public void update(UpdateAcousticSignalReq updateAcousticSignalReq) {
        AcousticSignal AcousticSignalCheck =
                AcousticSignalMapper.selectByPipeId(updateAcousticSignalReq.getPipeId());
        if (AcousticSignalCheck != null) {
            throw new PccpException(PccpExceptionEnum.NAME_EXISTED);
        }
        BeanUtils.copyProperties(updateAcousticSignalReq,AcousticSignalCheck);

        int count = AcousticSignalMapper.updateByPrimaryKeySelective(AcousticSignalCheck);
        if (count == 0){
            throw new PccpException(PccpExceptionEnum.UPDATE_FAILED);
        }
    }

    @Override
    public void delete(Integer[] ids) {
        int count = AcousticSignalMapper.deleteByPrimaryKey(ids);
        if (count !=ids.length){
            throw new PccpException(PccpExceptionEnum.DELETE_FAILED);
        }
    }

    @Override
    public PageInfo orUserListQue(AcousticSignalQue AcousticSignalQue) {
        PageHelper.startPage(AcousticSignalQue.getPageNum(), AcousticSignalQue.getPageSize());
        List<AcousticSignalVO> AcousticSignalList = AcousticSignalMapper.selectListQue(AcousticSignalQue);
        PageInfo pageInfo = new PageInfo(AcousticSignalList);
        return pageInfo;
    }

    @Override
    public List<AcousticSignalVO> exportList(AcousticSignalQue AcousticSignalQue) {
        List<AcousticSignalVO> AcousticSignalList = AcousticSignalMapper.selectListQue(AcousticSignalQue);
        return AcousticSignalList;
    }

    @Override
    public List<AcousticSignal> listByIds(Integer[] ids) {
        List<AcousticSignal> categoryList = AcousticSignalMapper.selectListByIds(ids);
        return categoryList;
    }
}
