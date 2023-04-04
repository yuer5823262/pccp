package com.example.pccp.service;

import com.example.pccp.model.pojo.TempInfo;
import com.example.pccp.model.query.TempInfoQue;
import com.example.pccp.model.request.AddTempInfoReq;
import com.example.pccp.model.request.UpdateTempInfoReq;
import com.example.pccp.model.vo.TempInfoVO;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface TempInfoService {
    void add(AddTempInfoReq addTempInfoReq, String username);

    void update(UpdateTempInfoReq updateTempInfoReq);

    void delete(Integer[] ids);

    PageInfo orUserListQue(TempInfoQue tempInfoQue);

    List<TempInfo> listByIds(Integer[] ids);

    List<TempInfoVO> exportList(TempInfoQue tempInfoQue);
}
