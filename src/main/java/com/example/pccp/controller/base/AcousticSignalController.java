package com.example.pccp.controller.base;

import cn.afterturn.easypoi.entity.vo.NormalExcelConstants;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import cn.afterturn.easypoi.view.PoiBaseView;
import com.example.pccp.common.ApiRestResponse;
import com.example.pccp.model.pojo.AcousticSignal;
import com.example.pccp.model.query.AcousticSignalQue;
import com.example.pccp.model.request.AddAcousticSignalReq;
import com.example.pccp.model.request.UpdateAcousticSignalReq;
import com.example.pccp.model.vo.AcousticSignalVO;
import com.example.pccp.service.AcousticSignalService;
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

@Api(tags = "声发射信号")
@RequestMapping("api/AcousticSignal")
@Controller
public class AcousticSignalController {
    @Autowired
    AcousticSignalService AcousticSignalService;

    @PostMapping("/add")
    @ResponseBody
    public ApiRestResponse addAcousticSignal(
            @RequestBody @Valid AddAcousticSignalReq addAcousticSignalReq,
            HttpServletRequest request) {
        String token = request.getHeader("token");
        String username =  JwtUtils.GetUserName(token);
        AcousticSignalService.add(addAcousticSignalReq,username);

        return ApiRestResponse.success();
    }


    @PostMapping("/update")
    @ResponseBody
    public ApiRestResponse updateAcousticSignal(
            @Valid @RequestBody UpdateAcousticSignalReq updateAcousticSignalReq) {

        AcousticSignalService.update(updateAcousticSignalReq);
        return ApiRestResponse.success();

    }

    @ApiOperation("删除")
    @GetMapping("/delete")
    @ResponseBody
    public ApiRestResponse deleteAcousticSignal(@RequestParam("ids") Integer[] ids) {
        AcousticSignalService.delete(ids);
        return ApiRestResponse.success();
    }

    @ApiOperation("有查询参数的分页列表")
    @PostMapping("/selectByQue")
    @ResponseBody
    public ApiRestResponse listBigSegementQue(@RequestBody AcousticSignalQue AcousticSignalQue) {
        PageInfo pageInfo = AcousticSignalService.orUserListQue(AcousticSignalQue);
        return ApiRestResponse.success(pageInfo);
    }
    @ApiOperation("根据ids查询")
    @PostMapping("/selectByIds")
    @ResponseBody
    public ApiRestResponse listByIds(Integer[] ids) {
        List<AcousticSignal> pageInfo = AcousticSignalService.listByIds(ids);
        return ApiRestResponse.success(pageInfo);
    }

    @ApiOperation(value = "导出Excel")
    @GetMapping("/exportList")
    public void exportMemberList(ModelMap map,
                                 HttpServletRequest request,
                                 HttpServletResponse response,
                                 @RequestBody AcousticSignalQue AcousticSignalQue) {
        List<AcousticSignalVO> memberList = AcousticSignalService.exportList(AcousticSignalQue);
        ExportParams params = new ExportParams("声发射信号", "声发射信号", ExcelType.XSSF);
        map.put(NormalExcelConstants.DATA_LIST, memberList);
        map.put(NormalExcelConstants.CLASS, AcousticSignalVO.class);
        map.put(NormalExcelConstants.PARAMS, params);
        map.put(NormalExcelConstants.FILE_NAME, "声发射信号");
        PoiBaseView.render(map, request, response, NormalExcelConstants.EASYPOI_EXCEL_VIEW);
    }
}
