package com.example.pccp.service;

import com.example.pccp.model.pojo.OutTempInfo;
import com.example.pccp.model.query.OutTempInfoQue;
import com.example.pccp.model.request.AddOutTempInfoReq;
import com.example.pccp.model.request.UpdateOutTempInfoReq;
import com.example.pccp.model.vo.OutTempInfoVO;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface OutTempInfoService {
    void add(AddOutTempInfoReq addOutTempInfoReq, String username);

    void update(UpdateOutTempInfoReq updateOutTempInfoReq);

    void delete(Integer[] ids);

    PageInfo orUserListQue(OutTempInfoQue outTempInfoQue);

    List<OutTempInfo> listByIds(Integer[] ids);

    List<OutTempInfoVO> exportList(OutTempInfoQue outTempInfoQue);
}
