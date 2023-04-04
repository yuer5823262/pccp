package com.example.pccp.service;

import com.example.pccp.model.pojo.PccpEvent;
import com.example.pccp.model.query.PccpEventQue;
import com.example.pccp.model.request.AddPccpEventReq;
import com.example.pccp.model.request.UpdatePccpEventReq;
import com.example.pccp.model.vo.PccpEventVO;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface PccpEventService {
    void add(AddPccpEventReq addPccpEventReq, String username);

    void update(UpdatePccpEventReq updatePccpEventReq);

    void delete(Integer[] ids);

    PageInfo orUserListQue(PccpEventQue pccpEventQue);

    List<PccpEvent> listByIds(Integer[] ids);

    List<PccpEventVO> exportList(PccpEventQue pccpEventQue);
}
