package com.example.pccp.service;

import com.example.pccp.model.pojo.AcousticSignal;
import com.example.pccp.model.query.AcousticSignalQue;
import com.example.pccp.model.request.AddAcousticSignalReq;
import com.example.pccp.model.request.UpdateAcousticSignalReq;
import com.example.pccp.model.vo.AcousticSignalVO;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface AcousticSignalService {
    void add(AddAcousticSignalReq addAcousticSignalReq, String username);

    void update(UpdateAcousticSignalReq updateAcousticSignalReq);

    void delete(Integer[] ids);

    PageInfo orUserListQue(AcousticSignalQue acousticSignalQue);

    List<AcousticSignal> listByIds(Integer[] ids);

    List<AcousticSignalVO> exportList(AcousticSignalQue acousticSignalQue);
}
