package com.example.pccp.service;

import com.example.pccp.model.pojo.PccpUser;
import com.example.pccp.model.request.RegisterUserReq;
import com.example.pccp.model.request.UpdateUserReq;
import com.github.pagehelper.PageInfo;

public interface UserService {

    void register(RegisterUserReq registerUserReq);

    PccpUser login(String userName, String pwd, String ip);

    void update(UpdateUserReq updateUserReq);

    PccpUser getInfo(Integer getId);

    boolean checkAdminRole(PccpUser currentUser);

    void updateInfomation(PccpUser currentUser, UpdateUserReq updateUserReq);

    PageInfo orUserList(Integer pageNum, Integer pageSize);

    void logout(String userName, String ip);

    void delete(Integer[] ids);

    PccpUser getUser(int i);
}
