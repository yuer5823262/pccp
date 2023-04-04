package com.example.pccp.model.dao;

import com.example.pccp.model.pojo.PccpUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PccpUserMapper {
    int deleteByPrimaryKey(@Param("ids") Integer[] ids);

    int insert(PccpUser record);

    int insertSelective(PccpUser record);

    PccpUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PccpUser record);

    int updateByPrimaryKey(PccpUser record);

    PccpUser selectByUserName(String username);

    PccpUser selectLogin(String userName, String password);

    List<PccpUser> selectList();
}