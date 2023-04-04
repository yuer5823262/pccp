package com.example.pccp.service.impl;

import com.example.pccp.exception.PccpException;
import com.example.pccp.exception.PccpExceptionEnum;
import com.example.pccp.model.dao.PipeMapper;
import com.example.pccp.model.pojo.Pipe;
import com.example.pccp.model.query.PipeQue;
import com.example.pccp.model.request.AddPipeReq;
import com.example.pccp.model.request.UpdatePipeReq;
import com.example.pccp.model.vo.PipeVO;
import com.example.pccp.service.PipeService;
import com.example.pccp.util.TimeUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PipeServiceImpl implements PipeService {
    @Autowired
    PipeMapper PipeMapper;
    @Override
    public void add(AddPipeReq addPipeReq, String username) {
        Pipe PipeCheck = PipeMapper.selectByName(addPipeReq.getName());
        if (PipeCheck != null) {

            throw new PccpException(PccpExceptionEnum.NAME_EXISTED);
        }
        Pipe Pipe = new Pipe();
        BeanUtils.copyProperties(addPipeReq,Pipe);
        Pipe.setCreateOperator(username);
        Pipe.setCreateTime(TimeUtils.getNowTime());
        int count = PipeMapper.insertSelective(Pipe);
        if (count == 0){
            throw new PccpException(PccpExceptionEnum.ADD_FAILED);
        }
    }

    @Override
    public void update(UpdatePipeReq updatePipeReq) {
        Pipe Pipe =
                PipeMapper.selectByPrimaryKey(updatePipeReq.getId());
        if (Pipe == null) {
            throw new PccpException(PccpExceptionEnum.UPDATE_FAILED);
        }
        Pipe PipeCheck =
                PipeMapper.selectByName(updatePipeReq.getName());
        if (PipeCheck != null) {
            throw new PccpException(PccpExceptionEnum.NAME_EXISTED);
        }
        BeanUtils.copyProperties(updatePipeReq,Pipe);

        int count = PipeMapper.updateByPrimaryKeySelective(Pipe);
        if (count == 0){
            throw new PccpException(PccpExceptionEnum.UPDATE_FAILED);
        }
    }

    @Override
    public void delete(Integer[] ids) {
        int count = PipeMapper.deleteByPrimaryKey(ids);
        if (count !=ids.length){
            throw new PccpException(PccpExceptionEnum.DELETE_FAILED);
        }
    }

    @Override
    public PageInfo orUserListQue(PipeQue PipeQue) {
        PageHelper.startPage(PipeQue.getPageNum(), PipeQue.getPageSize());
        List<PipeVO> PipeList = PipeMapper.selectListQue(PipeQue);
        PageInfo pageInfo = new PageInfo(PipeList);
        return pageInfo;
    }

    @Override
    public List<PipeVO> exportList(PipeQue PipeQue) {
        List<PipeVO> PipeList = PipeMapper.selectListQue(PipeQue);
        return PipeList;
    }

    @Override
    public List<Pipe> listByIds(Integer[] ids) {
        List<Pipe> categoryList = PipeMapper.selectListByIds(ids);
        return categoryList;
    }
}
