package com.example.pccp.model.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RegisterUserReq {


    @NotNull(message = "名称不能为空")
    @Size(min=1,max = 10,message = "名称长度在1到10位")
    private String name;
    @NotNull
    @Size(min=8,max = 20,message = "用户名长度在8到20位")
    private String username;
    @NotNull
    @Size(min=8,max = 20,message = "密码长度在8到20位")
    private String pwd;

    private Integer gender;

    private String organization;

    private String department;

    private String position;

    private String phone;

    private Integer role;
    private Integer type;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
