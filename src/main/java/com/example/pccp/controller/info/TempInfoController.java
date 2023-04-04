package com.example.pccp.controller.info;

import cn.afterturn.easypoi.entity.vo.NormalExcelConstants;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import cn.afterturn.easypoi.view.PoiBaseView;
import com.example.pccp.common.ApiRestResponse;
import com.example.pccp.model.pojo.TempInfo;
import com.example.pccp.model.query.TempInfoQue;
import com.example.pccp.model.request.AddTempInfoReq;
import com.example.pccp.model.request.UpdateTempInfoReq;
import com.example.pccp.model.vo.TempInfoVO;
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

@Api(tags = "水温")
@RequestMapping("api/TempInfo")
@Controller
public class TempInfoController {
    @Autowired
    com.example.pccp.service.TempInfoService TempInfoService;

    @PostMapping("/add")
    @ResponseBody
    public ApiRestResponse addTempInfo(
            @RequestBody @Valid AddTempInfoReq addTempInfoReq,
            HttpServletRequest request) {
        String token = request.getHeader("token");
        String username =  JwtUtils.GetUserName(token);
        TempInfoService.add(addTempInfoReq,username);

        return ApiRestResponse.success();
    }


    @PostMapping("/update")
    @ResponseBody
    public ApiRestResponse updateTempInfo(
            @Valid @RequestBody UpdateTempInfoReq updateTempInfoReq) {

        TempInfoService.update(updateTempInfoReq);
        return ApiRestResponse.success();

    }

    @ApiOperation("删除")
    @GetMapping("/delete")
    @ResponseBody
    public ApiRestResponse deleteTempInfo(@RequestParam("ids") Integer[] ids) {
        TempInfoService.delete(ids);
        return ApiRestResponse.success();
    }

    @ApiOperation("有查询参数的分页列表")
    @PostMapping("/selectByQue")
    @ResponseBody
    public ApiRestResponse listBigSegementQue(@RequestBody TempInfoQue TempInfoQue) {
        PageInfo pageInfo = TempInfoService.orUserListQue(TempInfoQue);
        return ApiRestResponse.success(pageInfo);
    }
    @ApiOperation("根据ids查询")
    @PostMapping("/selectByIds")
    @ResponseBody
    public ApiRestResponse listByIds(Integer[] ids) {
        List<TempInfo> pageInfo = TempInfoService.listByIds(ids);
        return ApiRestResponse.success(pageInfo);
    }

    @ApiOperation(value = "导出Excel")
    @GetMapping("/exportList")
    public void exportMemberList(ModelMap map,
                                 HttpServletRequest request,
                                 HttpServletResponse response,
                                 @RequestBody TempInfoQue TempInfoQue) {
        List<TempInfoVO> memberList = TempInfoService.exportList(TempInfoQue);
        ExportParams params = new ExportParams("水温", "水温", ExcelType.XSSF);
        map.put(NormalExcelConstants.DATA_LIST, memberList);
        map.put(NormalExcelConstants.CLASS, TempInfoVO.class);
        map.put(NormalExcelConstants.PARAMS, params);
        map.put(NormalExcelConstants.FILE_NAME, "水温");
        PoiBaseView.render(map, request, response, NormalExcelConstants.EASYPOI_EXCEL_VIEW);
    }
}
