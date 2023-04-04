package com.example.pccp.service.impl;

import com.example.pccp.model.dao.PccpLogMapper;
import com.example.pccp.model.pojo.PccpLog;
import com.example.pccp.model.query.PccpLogQue;
import com.example.pccp.service.PccpLogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class PccpLogServiceImpl implements PccpLogService {
    @Autowired
    PccpLogMapper PccpLogMapper;
    @Override
    public PageInfo orUserList(PccpLogQue damPourLogQue) {
        PageHelper.startPage(damPourLogQue.getPageNum(), damPourLogQue.getPageSize());
        List<PccpLog> PccpLogList = PccpLogMapper.selectList(damPourLogQue);
        PageInfo pageInfo = new PageInfo(PccpLogList);
        return pageInfo;
    }
}
