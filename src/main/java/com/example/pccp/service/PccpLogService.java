package com.example.pccp.service;

import com.example.pccp.model.query.PccpLogQue;
import com.github.pagehelper.PageInfo;

public interface PccpLogService {
    PageInfo orUserList(PccpLogQue damPourLogQue);
}
