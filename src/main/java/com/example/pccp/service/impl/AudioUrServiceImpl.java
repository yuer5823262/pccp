package com.example.pccp.service.impl;

import com.example.pccp.exception.PccpException;
import com.example.pccp.exception.PccpExceptionEnum;
import com.example.pccp.model.dao.AudioUrlMapper;
import com.example.pccp.model.pojo.AudioUrl;
import com.example.pccp.model.query.AudioUrQue;
import com.example.pccp.model.request.AddAudioUrReq;
import com.example.pccp.model.request.UpdateAudioUrReq;
import com.example.pccp.model.vo.AudioUrlVO;
import com.example.pccp.service.AudioUrService;
import com.example.pccp.util.TimeUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AudioUrServiceImpl implements AudioUrService {
    @Autowired
    AudioUrlMapper audioUrlMapper;
    @Override
    public void add(AddAudioUrReq addAudioUrReq, String username) {
        AudioUrl AudioUrlCheck = audioUrlMapper.selectByPipeId(addAudioUrReq.getPipeId());
        if (AudioUrlCheck != null) {

            throw new PccpException(PccpExceptionEnum.NAME_EXISTED);
        }
        AudioUrl AudioUrl = new AudioUrl();
        BeanUtils.copyProperties(addAudioUrReq,AudioUrl);
        AudioUrl.setCreateOperator(username);
        AudioUrl.setCreateTime(TimeUtils.getNowTime());
        int count = audioUrlMapper.insertSelective(AudioUrl);
        if (count == 0){
            throw new PccpException(PccpExceptionEnum.ADD_FAILED);
        }
    }

    @Override
    public void update(UpdateAudioUrReq updateAudioUrReq) {
        AudioUrl AudioUrl =
                audioUrlMapper.selectByPrimaryKey(updateAudioUrReq.getId());
        if (AudioUrl == null) {
            throw new PccpException(PccpExceptionEnum.UPDATE_FAILED);
        }
        AudioUrl AudioUrlCheck =
                audioUrlMapper.selectByPipeId(updateAudioUrReq.getPipeId());
        if (AudioUrlCheck != null) {
            throw new PccpException(PccpExceptionEnum.NAME_EXISTED);
        }
        BeanUtils.copyProperties(updateAudioUrReq,AudioUrl);

        int count = audioUrlMapper.updateByPrimaryKeySelective(AudioUrl);
        if (count == 0){
            throw new PccpException(PccpExceptionEnum.UPDATE_FAILED);
        }
    }

    @Override
    public void delete(Integer[] ids) {
        int count = audioUrlMapper.deleteByPrimaryKey(ids);
        if (count !=ids.length){
            throw new PccpException(PccpExceptionEnum.DELETE_FAILED);
        }
    }
    @Override
    public List<AudioUrl> listByIds(Integer[] ids) {
        List<AudioUrl> categoryList = audioUrlMapper.selectListByIds(ids);
        return categoryList;
    }
    @Override
    public PageInfo orUserListQue(AudioUrQue audioUrlQue) {
        PageHelper.startPage(audioUrlQue.getPageNum(), audioUrlQue.getPageSize());
        List<AudioUrlVO> AudioUrlList = audioUrlMapper.selectListQue(audioUrlQue);
        PageInfo pageInfo = new PageInfo(AudioUrlList);
        return pageInfo;
    }

    @Override
    public List<AudioUrlVO> exportList(AudioUrQue audioUrlQue) {
        List<AudioUrlVO> AudioUrlList = audioUrlMapper.selectListQue(audioUrlQue);
        return AudioUrlList;
    }
}
