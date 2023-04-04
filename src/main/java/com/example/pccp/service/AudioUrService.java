package com.example.pccp.service;

import com.example.pccp.model.pojo.AudioUrl;
import com.example.pccp.model.query.AudioUrQue;
import com.example.pccp.model.request.AddAudioUrReq;
import com.example.pccp.model.request.UpdateAudioUrReq;
import com.example.pccp.model.vo.AudioUrlVO;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface AudioUrService {
    void add(AddAudioUrReq addAudioUrReq, String username);

    void update(UpdateAudioUrReq updateAudioUrReq);

    void delete(Integer[] ids);

    List<AudioUrl> listByIds(Integer[] ids);

    PageInfo orUserListQue(AudioUrQue audioUrlQue);

    List<AudioUrlVO> exportList(AudioUrQue audioUrlQue);
}
