package com.example.pccp.service;

import com.example.pccp.model.pojo.Pipe;
import com.example.pccp.model.query.PipeQue;
import com.example.pccp.model.request.AddPipeReq;
import com.example.pccp.model.request.UpdatePipeReq;
import com.example.pccp.model.vo.PipeVO;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface PipeService {
    void add(AddPipeReq addPipeReq, String username);

    void update(UpdatePipeReq updatePipeReq);

    void delete(Integer[] ids);

    PageInfo orUserListQue(PipeQue pipeQue);

    List<Pipe> listByIds(Integer[] ids);

    List<PipeVO> exportList(PipeQue pipeQue);
}
