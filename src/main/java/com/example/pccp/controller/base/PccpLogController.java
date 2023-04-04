package com.example.pccp.controller.base;

import com.example.pccp.common.ApiRestResponse;
import com.example.pccp.model.query.PccpLogQue;
import com.example.pccp.service.PccpLogService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Api(description = "PccpLog")
@RequestMapping("api/PccpLog")
@Controller
public class PccpLogController {
    @Autowired
    PccpLogService pccpLogService;
    @ApiOperation("有查询参数的分页列表")
    @PostMapping("/selectByQue")
    @ResponseBody
    public ApiRestResponse listBigSegementQue(@RequestBody PccpLogQue PccpLogQue) {
        PageInfo pageInfo = pccpLogService.orUserList(PccpLogQue);
        return ApiRestResponse.success(pageInfo);
    }
}
