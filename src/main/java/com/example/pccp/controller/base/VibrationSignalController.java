package com.example.pccp.controller.base;

import cn.afterturn.easypoi.entity.vo.NormalExcelConstants;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import cn.afterturn.easypoi.view.PoiBaseView;
import com.example.pccp.common.ApiRestResponse;
import com.example.pccp.model.pojo.VibrationSignal;
import com.example.pccp.model.query.VibrationSignalQue;
import com.example.pccp.model.request.AddVibrationSignalReq;
import com.example.pccp.model.request.UpdateVibrationSignalReq;
import com.example.pccp.model.vo.VibrationSignalVO;
import com.example.pccp.service.VibrationSignalService;
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

@Api(tags = "震动信号")
@RequestMapping("api/VibrationSignal")
@Controller
public class VibrationSignalController {
    @Autowired
    com.example.pccp.service.VibrationSignalService VibrationSignalService;

    @PostMapping("/add")
    @ResponseBody
    public ApiRestResponse addVibrationSignal(
            @RequestBody @Valid AddVibrationSignalReq addVibrationSignalReq,
            HttpServletRequest request) {
        String token = request.getHeader("token");
        String username =  JwtUtils.GetUserName(token);
        VibrationSignalService.add(addVibrationSignalReq,username);

        return ApiRestResponse.success();
    }


    @PostMapping("/update")
    @ResponseBody
    public ApiRestResponse updateVibrationSignal(
            @Valid @RequestBody UpdateVibrationSignalReq updateVibrationSignalReq) {

        VibrationSignalService.update(updateVibrationSignalReq);
        return ApiRestResponse.success();

    }

    @ApiOperation("删除")
    @GetMapping("/delete")
    @ResponseBody
    public ApiRestResponse deleteVibrationSignal(@RequestParam("ids") Integer[] ids) {
        VibrationSignalService.delete(ids);
        return ApiRestResponse.success();
    }

    @ApiOperation("有查询参数的分页列表")
    @PostMapping("/selectByQue")
    @ResponseBody
    public ApiRestResponse listBigSegementQue(@RequestBody VibrationSignalQue VibrationSignalQue) {
        PageInfo pageInfo = VibrationSignalService.orUserListQue(VibrationSignalQue);
        return ApiRestResponse.success(pageInfo);
    }
    @ApiOperation("根据ids查询")
    @PostMapping("/selectByIds")
    @ResponseBody
    public ApiRestResponse listByIds(Integer[] ids) {
        List<VibrationSignal> pageInfo = VibrationSignalService.listByIds(ids);
        return ApiRestResponse.success(pageInfo);
    }

    @ApiOperation(value = "导出Excel")
    @GetMapping("/exportList")
    public void exportMemberList(ModelMap map,
                                 HttpServletRequest request,
                                 HttpServletResponse response,
                                 @RequestBody VibrationSignalQue VibrationSignalQue) {
        List<VibrationSignalVO> memberList = VibrationSignalService.exportList(VibrationSignalQue);
        ExportParams params = new ExportParams("震动信号", "震动信号", ExcelType.XSSF);
        map.put(NormalExcelConstants.DATA_LIST, memberList);
        map.put(NormalExcelConstants.CLASS, VibrationSignalVO.class);
        map.put(NormalExcelConstants.PARAMS, params);
        map.put(NormalExcelConstants.FILE_NAME, "震动信号");
        PoiBaseView.render(map, request, response, NormalExcelConstants.EASYPOI_EXCEL_VIEW);
    }
}
