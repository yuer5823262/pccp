package com.example.pccp.model.request;

import javax.validation.constraints.NotNull;

public class UserLoginReq {
    @NotNull
    String userName;
    @NotNull
    String pwd;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
