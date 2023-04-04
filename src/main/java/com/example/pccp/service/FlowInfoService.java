package com.example.pccp.service;

import com.example.pccp.model.pojo.FlowInfo;
import com.example.pccp.model.query.FlowInfoQue;
import com.example.pccp.model.request.AddFlowInfoReq;
import com.example.pccp.model.request.UpdateFlowInfoReq;
import com.example.pccp.model.vo.FlowInfoVO;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface FlowInfoService {
    void add(AddFlowInfoReq addFlowInfoReq, String username);

    void update(UpdateFlowInfoReq updateFlowInfoReq);

    void delete(Integer[] ids);

    PageInfo orUserListQue(FlowInfoQue flowInfoQue);

    List<FlowInfo> listByIds(Integer[] ids);

    List<FlowInfoVO> exportList(FlowInfoQue flowInfoQue);
}
