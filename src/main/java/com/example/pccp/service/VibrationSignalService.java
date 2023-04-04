package com.example.pccp.service;

import com.example.pccp.model.pojo.VibrationSignal;
import com.example.pccp.model.query.VibrationSignalQue;
import com.example.pccp.model.request.AddVibrationSignalReq;
import com.example.pccp.model.request.UpdateVibrationSignalReq;
import com.example.pccp.model.vo.VibrationSignalVO;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface VibrationSignalService {
    void add(AddVibrationSignalReq addVibrationSignalReq, String username);

    void update(UpdateVibrationSignalReq updateVibrationSignalReq);

    void delete(Integer[] ids);

    PageInfo orUserListQue(VibrationSignalQue vibrationSignalQue);

    List<VibrationSignal> listByIds(Integer[] ids);

    List<VibrationSignalVO> exportList(VibrationSignalQue vibrationSignalQue);
}
