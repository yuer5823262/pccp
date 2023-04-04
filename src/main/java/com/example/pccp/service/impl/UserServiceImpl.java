package com.example.pccp.service.impl;

import com.example.pccp.common.Constant;
import com.example.pccp.exception.PccpException;
import com.example.pccp.exception.PccpExceptionEnum;
import com.example.pccp.model.dao.PccpLogMapper;
import com.example.pccp.model.dao.PccpUserMapper;
import com.example.pccp.model.pojo.PccpLog;
import com.example.pccp.model.pojo.PccpUser;
import com.example.pccp.model.request.RegisterUserReq;
import com.example.pccp.model.request.UpdateUserReq;
import com.example.pccp.service.UserService;
import com.example.pccp.util.CopyUtils;
import com.example.pccp.util.MD5Utils;
import com.example.pccp.util.TimeUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    PccpUserMapper userMapper;

    @Autowired
    PccpLogMapper pccpLogMapper;
    @Override
    public PccpUser getUser(int userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public void register(RegisterUserReq registerUserReq) {
        PccpUser result= userMapper.selectByUserName(registerUserReq.getUsername());
        if(result != null){
            throw new PccpException(PccpExceptionEnum.NAME_EXISTED);
        }
        PccpUser user = new PccpUser();
        BeanUtils.copyProperties(registerUserReq,user);
        try {
            user.setPwd(MD5Utils.getMD5Str(user.getPwd()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        int conut = userMapper.insertSelective(user);
        if(conut==0){
            throw new PccpException(PccpExceptionEnum.INSERT_FAILED);
        }

    }
    @Override
    public PccpUser login(String userName, String password, String ip) throws PccpException {
        String md5Pwd = null;
        try {
            md5Pwd = MD5Utils.getMD5Str(password);
        } catch (NoSuchAlgorithmException e) {
            Constant.logger.error("错误:",e);
        }

        PccpUser user = userMapper.selectLogin(userName,md5Pwd);

        if (user ==null)
            throw new PccpException(PccpExceptionEnum.PASSWORD_WRONG);
        PccpLog damPourLog = new PccpLog();
        damPourLog.setIpAddr(ip);
        damPourLog.setTime(TimeUtils.getNowTime());
        damPourLog.setOperator(user.getUsername());
        damPourLog.setType("login");
        pccpLogMapper.insertSelective(damPourLog);
        return user;
    }



    @Override
    public boolean checkAdminRole(PccpUser currentUser) {
        return currentUser.getRole()==1?true:false;
    }

    @Override
    public void updateInfomation(PccpUser currentUser, UpdateUserReq updateUserReq) {
        try {
            String oldPwd = MD5Utils.getMD5Str(updateUserReq.getOldPwd());
            if(!oldPwd.equals(currentUser.getPwd())){
                throw new PccpException(PccpExceptionEnum.PASSWORD_WRONG);
            }
        } catch (NoSuchAlgorithmException e) {
            Constant.logger.error("错误:",e);
        }
        CopyUtils.copyPropertiesIgnoreNull(updateUserReq,currentUser);
        try {
            currentUser.setPwd(MD5Utils.getMD5Str(currentUser.getPwd()));
        } catch (NoSuchAlgorithmException e) {
            Constant.logger.error("错误:",e);
        }
        int updateCount = userMapper.updateByPrimaryKeySelective(currentUser);
        if (updateCount > 1) {
            throw new PccpException(PccpExceptionEnum.UPDATE_FAILED);
        }
    }

    @Override
    public PccpUser getInfo(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageInfo orUserList(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<PccpUser> subControlStationList = userMapper.selectList();
        PageInfo pageInfo = new PageInfo(subControlStationList);
        return pageInfo;
    }

    @Override
    public void delete(Integer[] ids) {
        int count = userMapper.deleteByPrimaryKey(ids);
        if (count == 0){
            throw new PccpException(PccpExceptionEnum.DELETE_FAILED);
        }
    }

    @Override
    public void logout(String currentUser, String ip) {
        PccpLog damPourLog = new PccpLog();
        damPourLog.setIpAddr(ip);
        damPourLog.setTime(TimeUtils.getNowTime());
        damPourLog.setOperator(currentUser);
        damPourLog.setType("logout");
        pccpLogMapper.insertSelective(damPourLog);
    }

    @Override
    public void update(UpdateUserReq updateUserReq) {
        PccpUser currentUser = new PccpUser();
        CopyUtils.copyPropertiesIgnoreNull(updateUserReq,currentUser);
        if(!currentUser.getPwd().isEmpty()) {
            try {
                currentUser.setPwd(MD5Utils.getMD5Str(currentUser.getPwd()));
            } catch (NoSuchAlgorithmException e) {
                Constant.logger.error("错误:",e);
            }
        }
        int updateCount = userMapper.updateByPrimaryKeySelective(currentUser);
        if (updateCount > 1) {
            throw new PccpException(PccpExceptionEnum.UPDATE_FAILED);
        }
    }
}
