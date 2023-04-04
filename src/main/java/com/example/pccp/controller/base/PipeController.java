package com.example.pccp.controller.base;

import cn.afterturn.easypoi.entity.vo.NormalExcelConstants;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import cn.afterturn.easypoi.view.PoiBaseView;
import com.example.pccp.common.ApiRestResponse;
import com.example.pccp.model.pojo.Pipe;
import com.example.pccp.model.query.PipeQue;
import com.example.pccp.model.request.AddPipeReq;
import com.example.pccp.model.request.UpdatePipeReq;
import com.example.pccp.model.vo.PipeVO;
import com.example.pccp.service.PipeService;
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
@Api(description = "pccp管")
@RequestMapping("api/pipe")
@Controller
public class PipeController {
    @Autowired
    PipeService PipeService;

    @PostMapping("/add")
    @ResponseBody
    public ApiRestResponse addPipe(
            @RequestBody @Valid AddPipeReq addPipeReq,
            HttpServletRequest request) {
        String token = request.getHeader("token");
        String username =  JwtUtils.GetUserName(token);
        PipeService.add(addPipeReq,username);

        return ApiRestResponse.success();
    }


    @PostMapping("/update")
    @ResponseBody
    public ApiRestResponse updatePipe(
            @Valid @RequestBody UpdatePipeReq updatePipeReq) {

        PipeService.update(updatePipeReq);
        return ApiRestResponse.success();

    }

    @ApiOperation("删除")
    @GetMapping("/delete")
    @ResponseBody
    public ApiRestResponse deletePipe(@RequestParam("ids") Integer[] ids) {
        PipeService.delete(ids);
        return ApiRestResponse.success();
    }

    @ApiOperation("有查询参数的分页列表")
    @PostMapping("/selectByQue")
    @ResponseBody
    public ApiRestResponse listBigSegementQue(@RequestBody PipeQue PipeQue) {
        PageInfo pageInfo = PipeService.orUserListQue(PipeQue);
        return ApiRestResponse.success(pageInfo);
    }
    @ApiOperation("根据ids查询")
    @PostMapping("/selectByIds")
    @ResponseBody
    public ApiRestResponse listByIds(Integer[] ids) {
        List<Pipe> pageInfo = PipeService.listByIds(ids);
        return ApiRestResponse.success(pageInfo);
    }

    @ApiOperation(value = "导出Excel")
    @GetMapping("/exportList")
    public void exportMemberList(ModelMap map,
                                 HttpServletRequest request,
                                 HttpServletResponse response,
                                 @RequestBody PipeQue PipeQue) {
        List<PipeVO> memberList = PipeService.exportList(PipeQue);
        ExportParams params = new ExportParams("pccp管", "pccp管", ExcelType.XSSF);
        map.put(NormalExcelConstants.DATA_LIST, memberList);
        map.put(NormalExcelConstants.CLASS, PipeVO.class);
        map.put(NormalExcelConstants.PARAMS, params);
        map.put(NormalExcelConstants.FILE_NAME, "pccp管");
        PoiBaseView.render(map, request, response, NormalExcelConstants.EASYPOI_EXCEL_VIEW);
    }
}
