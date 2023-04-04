package com.example.pccp.controller.base;

import cn.afterturn.easypoi.entity.vo.NormalExcelConstants;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import cn.afterturn.easypoi.view.PoiBaseView;
import com.example.pccp.common.ApiRestResponse;
import com.example.pccp.model.pojo.PccpEvent;
import com.example.pccp.model.query.PccpEventQue;
import com.example.pccp.model.request.AddPccpEventReq;
import com.example.pccp.model.request.UpdatePccpEventReq;
import com.example.pccp.model.vo.PccpEventVO;
import com.example.pccp.service.PccpEventService;
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

@Api(description = "时间事件")
@RequestMapping("api/PccpEvent")
@Controller
class PccpEventController {
    @Autowired
    PccpEventService PccpEventService;

    @PostMapping("/add")
    @ResponseBody
    public ApiRestResponse addPccpEvent(
            @RequestBody @Valid AddPccpEventReq addPccpEventReq,
            HttpServletRequest request) {
        String token = request.getHeader("token");
        String username =  JwtUtils.GetUserName(token);
        PccpEventService.add(addPccpEventReq,username);

        return ApiRestResponse.success();
    }


    @PostMapping("/update")
    @ResponseBody
    public ApiRestResponse updatePccpEvent(
            @Valid @RequestBody UpdatePccpEventReq updatePccpEventReq) {

        PccpEventService.update(updatePccpEventReq);
        return ApiRestResponse.success();

    }

    @ApiOperation("删除")
    @GetMapping("/delete")
    @ResponseBody
    public ApiRestResponse deletePccpEvent(@RequestParam("ids") Integer[] ids) {
        PccpEventService.delete(ids);
        return ApiRestResponse.success();
    }

    @ApiOperation("有查询参数的分页列表")
    @PostMapping("/selectByQue")
    @ResponseBody
    public ApiRestResponse listBigSegementQue(@RequestBody PccpEventQue PccpEventQue) {
        PageInfo pageInfo = PccpEventService.orUserListQue(PccpEventQue);
        return ApiRestResponse.success(pageInfo);
    }
    @ApiOperation("根据ids查询")
    @PostMapping("/selectByIds")
    @ResponseBody
    public ApiRestResponse listByIds(Integer[] ids) {
        List<PccpEvent> pageInfo = PccpEventService.listByIds(ids);
        return ApiRestResponse.success(pageInfo);
    }

    @ApiOperation(value = "导出Excel")
    @GetMapping("/exportList")
    public void exportMemberList(ModelMap map,
                                 HttpServletRequest request,
                                 HttpServletResponse response,
                                 @RequestBody PccpEventQue PccpEventQue) {
        List<PccpEventVO> memberList = PccpEventService.exportList(PccpEventQue);
        ExportParams params = new ExportParams("事件", "事件", ExcelType.XSSF);
        map.put(NormalExcelConstants.DATA_LIST, memberList);
        map.put(NormalExcelConstants.CLASS, PccpEventVO.class);
        map.put(NormalExcelConstants.PARAMS, params);
        map.put(NormalExcelConstants.FILE_NAME, "事件");
        PoiBaseView.render(map, request, response, NormalExcelConstants.EASYPOI_EXCEL_VIEW);
    }
}
