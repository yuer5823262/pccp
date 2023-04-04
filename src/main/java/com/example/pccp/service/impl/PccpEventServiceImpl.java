package com.example.pccp.service.impl;

import com.example.pccp.exception.PccpException;
import com.example.pccp.exception.PccpExceptionEnum;
import com.example.pccp.model.dao.PccpEventMapper;
import com.example.pccp.model.pojo.PccpEvent;
import com.example.pccp.model.query.PccpEventQue;
import com.example.pccp.model.request.AddPccpEventReq;
import com.example.pccp.model.request.UpdatePccpEventReq;
import com.example.pccp.model.vo.PccpEventVO;
import com.example.pccp.service.PccpEventService;
import com.example.pccp.util.TimeUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PccpEventServiceImpl implements PccpEventService {
    @Autowired
    PccpEventMapper PccpEventMapper;
    @Override
    public void add(AddPccpEventReq addPccpEventReq, String username) {
        PccpEvent PccpEventCheck = PccpEventMapper.selectByPipeId(addPccpEventReq.getPipeId());
        if (PccpEventCheck != null) {

            throw new PccpException(PccpExceptionEnum.NAME_EXISTED);
        }
        PccpEvent PccpEvent = new PccpEvent();
        BeanUtils.copyProperties(addPccpEventReq,PccpEvent);
        int count = PccpEventMapper.insertSelective(PccpEvent);
        if (count == 0){
            throw new PccpException(PccpExceptionEnum.ADD_FAILED);
        }
    }

    @Override
    public void update(UpdatePccpEventReq updatePccpEventReq) {
        PccpEvent PccpEvent =
                PccpEventMapper.selectByPrimaryKey(updatePccpEventReq.getId());
        if (PccpEvent == null) {
            throw new PccpException(PccpExceptionEnum.UPDATE_FAILED);
        }
        PccpEvent PccpEventCheck =
                PccpEventMapper.selectByPipeId(updatePccpEventReq.getPipeId());
        if (PccpEventCheck != null) {
            throw new PccpException(PccpExceptionEnum.NAME_EXISTED);
        }
        BeanUtils.copyProperties(updatePccpEventReq,PccpEvent);

        int count = PccpEventMapper.updateByPrimaryKeySelective(PccpEvent);
        if (count == 0){
            throw new PccpException(PccpExceptionEnum.UPDATE_FAILED);
        }
    }

    @Override
    public void delete(Integer[] ids) {
        int count = PccpEventMapper.deleteByPrimaryKey(ids);
        if (count !=ids.length){
            throw new PccpException(PccpExceptionEnum.DELETE_FAILED);
        }
    }

    @Override
    public PageInfo orUserListQue(PccpEventQue PccpEventQue) {
        PageHelper.startPage(PccpEventQue.getPageNum(), PccpEventQue.getPageSize());
        List<PccpEventVO> PccpEventList = PccpEventMapper.selectListQue(PccpEventQue);
        PageInfo pageInfo = new PageInfo(PccpEventList);
        return pageInfo;
    }

    @Override
    public List<PccpEventVO> exportList(PccpEventQue PccpEventQue) {
        List<PccpEventVO> PccpEventList = PccpEventMapper.selectListQue(PccpEventQue);
        return PccpEventList;
    }

    @Override
    public List<PccpEvent> listByIds(Integer[] ids) {
        List<PccpEvent> categoryList = PccpEventMapper.selectListByIds(ids);
        return categoryList;
    }
}
