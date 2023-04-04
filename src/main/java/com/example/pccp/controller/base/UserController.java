package com.example.pccp.controller.base;

import com.example.pccp.common.ApiRestResponse;
import com.example.pccp.model.pojo.PccpUser;
import com.example.pccp.model.request.RegisterUserReq;
import com.example.pccp.model.request.UpdateUserReq;
import com.example.pccp.model.request.UserLoginReq;
import com.example.pccp.model.vo.UserVO;
import com.example.pccp.service.UserService;
import com.example.pccp.util.JwtUtils;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;

@Controller
@RequestMapping("/api")
@Api(description = "用户")
public class UserController {
    @Autowired
    UserService userService;


    @GetMapping("/test")
    @ResponseBody
    public PccpUser personalPage(){
//        try {
//            return MD5Utils.getMD5Str("111");
//        } catch (NoSuchAlgorithmException e) {
//            Constant.logger.error("错误:",e);
//        }
        return userService.getUser(111);
    }

    @PostMapping("/add")
    @ResponseBody
    public ApiRestResponse register(@Valid @RequestBody RegisterUserReq registerUserReq) throws NoSuchAlgorithmException {

        userService.register(registerUserReq);
        return ApiRestResponse.success();

    }


    @PostMapping("/login")
    @ResponseBody
    public ApiRestResponse login(@RequestBody @Valid UserLoginReq userLoginReq, HttpServletRequest request){

        String ip = request.getRemoteAddr();
        PccpUser user = userService.login(userLoginReq.getUserName(),userLoginReq.getPwd(),ip);
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user,userVO);
//        if(user.getGender()==1) userVO.setGender("男");
//        else userVO.setGender("女");
        userVO.setToken(JwtUtils.getToken(user));
        return ApiRestResponse.success(userVO);
    }

    @PostMapping("/user/update")
    @ResponseBody
    public ApiRestResponse update(@RequestBody UpdateUserReq updateUserReq) {
        userService.update(updateUserReq);
        return ApiRestResponse.success();
    }

    @PostMapping("/user/updateUser")
    @ResponseBody
    public ApiRestResponse updateUserInfo(@RequestBody UpdateUserReq updateUserReq,
                                          HttpServletRequest request) {
        String token = request.getHeader("token");
        PccpUser currentUser = userService.getInfo(JwtUtils.GetId(token));
        userService.updateInfomation(currentUser,updateUserReq);
        return ApiRestResponse.success();
    }


    @GetMapping("/user/info")
    @ResponseBody
    public ApiRestResponse getUserInfo(HttpServletRequest request) {
        String token = request.getHeader("token");
        Integer id = JwtUtils.GetId(token);
        PccpUser userTable = userService.getInfo(id);
        return ApiRestResponse.success(userTable);
    }

    @ApiOperation("分页列表")
    @GetMapping("/list")
    @ResponseBody
    public ApiRestResponse listBigSegement(@RequestParam Integer pageNum,
                                           @RequestParam Integer pageSize) {
        PageInfo pageInfo = userService.orUserList(pageNum, pageSize);
        return ApiRestResponse.success(pageInfo);
    }

    @GetMapping("/user/logout")
    @ResponseBody
    public ApiRestResponse logout(HttpServletRequest request){
        String token = request.getHeader("token");
        String userName = JwtUtils.GetUserName(token);
        String ip = request.getRemoteAddr();
        userService.logout(userName,ip);
        return ApiRestResponse.success();
    }

    @ApiOperation("删除")
    @GetMapping("/delete")
    @ResponseBody
    public ApiRestResponse deleteSmallStorageBin(@RequestParam("ids") Integer[] ids) {
        userService.delete(ids);
        return ApiRestResponse.success();
    }


}
