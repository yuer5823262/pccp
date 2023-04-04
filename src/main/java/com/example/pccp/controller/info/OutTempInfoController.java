package com.example.pccp.controller.info;

import cn.afterturn.easypoi.entity.vo.NormalExcelConstants;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import cn.afterturn.easypoi.view.PoiBaseView;
import com.example.pccp.common.ApiRestResponse;
import com.example.pccp.model.pojo.OutTempInfo;
import com.example.pccp.model.query.OutTempInfoQue;
import com.example.pccp.model.request.AddOutTempInfoReq;
import com.example.pccp.model.request.UpdateOutTempInfoReq;
import com.example.pccp.model.vo.OutTempInfoVO;
import com.example.pccp.util.JwtUtils;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@Api(tags = "管道外温度")
@RequestMapping("api/OutTempInfo")
@Controller
public class OutTempInfoController {
    @Autowired
    com.example.pccp.service.OutTempInfoService OutTempInfoService;

    @PostMapping("/add")
    @ResponseBody
    public ApiRestResponse addOutTempInfo(
            @RequestBody @Valid AddOutTempInfoReq addOutTempInfoReq,
            HttpServletRequest request) {
        String token = request.getHeader("token");
        String username =  JwtUtils.GetUserName(token);
        OutTempInfoService.add(addOutTempInfoReq,username);

        return ApiRestResponse.success();
    }


    @PostMapping("/update")
    @ResponseBody
    public ApiRestResponse updateOutTempInfo(
            @Valid @RequestBody UpdateOutTempInfoReq updateOutTempInfoReq) {

        OutTempInfoService.update(updateOutTempInfoReq);
        return ApiRestResponse.success();

    }

    @ApiOperation("删除")
    @GetMapping("/delete")
    @ResponseBody
    public ApiRestResponse deleteOutTempInfo(@RequestParam("ids") Integer[] ids) {
        OutTempInfoService.delete(ids);
        return ApiRestResponse.success();
    }

    @ApiOperation("有查询参数的分页列表")
    @PostMapping("/selectByQue")
    @ResponseBody
    public ApiRestResponse listBigSegementQue(@RequestBody OutTempInfoQue OutTempInfoQue) {
        PageInfo pageInfo = OutTempInfoService.orUserListQue(OutTempInfoQue);
        return ApiRestResponse.success(pageInfo);
    }
    @ApiOperation("根据ids查询")
    @PostMapping("/selectByIds")
    @ResponseBody
    public ApiRestResponse listByIds(Integer[] ids) {
        List<OutTempInfo> pageInfo = OutTempInfoService.listByIds(ids);
        return ApiRestResponse.success(pageInfo);
    }

    @ApiOperation(value = "导出Excel")
    @GetMapping("/exportList")
    public void exportMemberList(ModelMap map,
                                 HttpServletRequest request,
                                 HttpServletResponse response,
                                 @RequestBody OutTempInfoQue OutTempInfoQue) {
        List<OutTempInfoVO> memberList = OutTempInfoService.exportList(OutTempInfoQue);
        ExportParams params = new ExportParams("管道外温度", "管道外温度", ExcelType.XSSF);
        map.put(NormalExcelConstants.DATA_LIST, memberList);
        map.put(NormalExcelConstants.CLASS, OutTempInfoVO.class);
        map.put(NormalExcelConstants.PARAMS, params);
        map.put(NormalExcelConstants.FILE_NAME, "管道外温度");
        PoiBaseView.render(map, request, response, NormalExcelConstants.EASYPOI_EXCEL_VIEW);
    }
}
