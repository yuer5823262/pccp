package com.example.pccp.service.impl;

import com.example.pccp.exception.PccpException;
import com.example.pccp.exception.PccpExceptionEnum;
import com.example.pccp.model.pojo.FlowInfo;
import com.example.pccp.model.query.FlowInfoQue;
import com.example.pccp.model.request.AddFlowInfoReq;
import com.example.pccp.model.request.UpdateFlowInfoReq;
import com.example.pccp.model.vo.FlowInfoVO;
import com.example.pccp.service.FlowInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlowInfoServiceImpl implements FlowInfoService {
    @Autowired
    com.example.pccp.model.dao.FlowInfoMapper FlowInfoMapper;
    @Override
    public void add(AddFlowInfoReq addFlowInfoReq, String username) {
        FlowInfo FlowInfo = new FlowInfo();
        BeanUtils.copyProperties(addFlowInfoReq,FlowInfo);
        int count = FlowInfoMapper.insertSelective(FlowInfo);
        if (count == 0){
            throw new PccpException(PccpExceptionEnum.ADD_FAILED);
        }
    }

    @Override
    public void update(UpdateFlowInfoReq updateFlowInfoReq) {
        FlowInfo FlowInfoCheck =
                FlowInfoMapper.selectByPrimaryKey(updateFlowInfoReq.getId());
        if (FlowInfoCheck == null) {
            throw new PccpException(PccpExceptionEnum.ADD_FAILED);
        }
        BeanUtils.copyProperties(updateFlowInfoReq,FlowInfoCheck);

        int count = FlowInfoMapper.updateByPrimaryKeySelective(FlowInfoCheck);
        if (count == 0){
            throw new PccpException(PccpExceptionEnum.UPDATE_FAILED);
        }
    }

    @Override
    public void delete(Integer[] ids) {
        int count = FlowInfoMapper.deleteByPrimaryKey(ids);
        if (count !=ids.length){
            throw new PccpException(PccpExceptionEnum.DELETE_FAILED);
        }
    }

    @Override
    public PageInfo orUserListQue(FlowInfoQue FlowInfoQue) {
        PageHelper.startPage(FlowInfoQue.getPageNum(), FlowInfoQue.getPageSize());
        List<FlowInfoVO> FlowInfoList = FlowInfoMapper.selectListQue(FlowInfoQue);
        PageInfo pageInfo = new PageInfo(FlowInfoList);
        return pageInfo;
    }

    @Override
    public List<FlowInfoVO> exportList(FlowInfoQue FlowInfoQue) {
        List<FlowInfoVO> FlowInfoList = FlowInfoMapper.selectListQue(FlowInfoQue);
        return FlowInfoList;
    }

    @Override
    public List<FlowInfo> listByIds(Integer[] ids) {
        List<FlowInfo> categoryList = FlowInfoMapper.selectListByIds(ids);
        return categoryList;
    }
}
