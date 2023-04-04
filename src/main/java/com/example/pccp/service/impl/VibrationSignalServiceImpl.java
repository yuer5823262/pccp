package com.example.pccp.service.impl;

import com.example.pccp.exception.PccpException;
import com.example.pccp.exception.PccpExceptionEnum;
import com.example.pccp.model.pojo.VibrationSignal;
import com.example.pccp.model.query.VibrationSignalQue;
import com.example.pccp.model.request.AddVibrationSignalReq;
import com.example.pccp.model.request.UpdateVibrationSignalReq;
import com.example.pccp.model.vo.VibrationSignalVO;
import com.example.pccp.service.VibrationSignalService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VibrationSignalServiceImpl implements VibrationSignalService {
    @Autowired
    com.example.pccp.model.dao.VibrationSignalMapper VibrationSignalMapper;
    @Override
    public void add(AddVibrationSignalReq addVibrationSignalReq, String username) {
        VibrationSignal VibrationSignalCheck = VibrationSignalMapper.selectByPipeId(addVibrationSignalReq.getPipeId());
        if (VibrationSignalCheck != null) {

            throw new PccpException(PccpExceptionEnum.NAME_EXISTED);
        }
        VibrationSignal VibrationSignal = new VibrationSignal();
        BeanUtils.copyProperties(addVibrationSignalReq,VibrationSignal);
        int count = VibrationSignalMapper.insertSelective(VibrationSignal);
        if (count == 0){
            throw new PccpException(PccpExceptionEnum.ADD_FAILED);
        }
    }

    @Override
    public void update(UpdateVibrationSignalReq updateVibrationSignalReq) {
        VibrationSignal VibrationSignalCheck =
                VibrationSignalMapper.selectByPipeId(updateVibrationSignalReq.getPipeId());
        if (VibrationSignalCheck != null) {
            throw new PccpException(PccpExceptionEnum.NAME_EXISTED);
        }
        BeanUtils.copyProperties(updateVibrationSignalReq,VibrationSignalCheck);

        int count = VibrationSignalMapper.updateByPrimaryKeySelective(VibrationSignalCheck);
        if (count == 0){
            throw new PccpException(PccpExceptionEnum.UPDATE_FAILED);
        }
    }

    @Override
    public void delete(Integer[] ids) {
        int count = VibrationSignalMapper.deleteByPrimaryKey(ids);
        if (count !=ids.length){
            throw new PccpException(PccpExceptionEnum.DELETE_FAILED);
        }
    }

    @Override
    public PageInfo orUserListQue(VibrationSignalQue VibrationSignalQue) {
        PageHelper.startPage(VibrationSignalQue.getPageNum(), VibrationSignalQue.getPageSize());
        List<VibrationSignalVO> VibrationSignalList = VibrationSignalMapper.selectListQue(VibrationSignalQue);
        PageInfo pageInfo = new PageInfo(VibrationSignalList);
        return pageInfo;
    }

    @Override
    public List<VibrationSignalVO> exportList(VibrationSignalQue VibrationSignalQue) {
        List<VibrationSignalVO> VibrationSignalList = VibrationSignalMapper.selectListQue(VibrationSignalQue);
        return VibrationSignalList;
    }

    @Override
    public List<VibrationSignal> listByIds(Integer[] ids) {
        List<VibrationSignal> categoryList = VibrationSignalMapper.selectListByIds(ids);
        return categoryList;
    }
}
