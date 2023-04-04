package com.example.pccp.controller.base;

import cn.afterturn.easypoi.entity.vo.NormalExcelConstants;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import cn.afterturn.easypoi.view.PoiBaseView;
import com.example.pccp.common.ApiRestResponse;
import com.example.pccp.model.pojo.AudioUrl;
import com.example.pccp.model.pojo.CameraUrl;
import com.example.pccp.model.query.CameraUrlQue;
import com.example.pccp.model.request.AddCameraUrlReq;
import com.example.pccp.model.request.UpdateCameraUrlReq;
import com.example.pccp.model.vo.CameraUrlVO;
import com.example.pccp.service.CameraUrlService;
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

@Api(description = "视频链接")
@RequestMapping("api/CameraUrl")
@Controller
public class CameraUrlController {
    @Autowired
    CameraUrlService CameraUrlService;

    @PostMapping("/add")
    @ResponseBody
    public ApiRestResponse addCameraUrl(
            @RequestBody @Valid AddCameraUrlReq addCameraUrlReq,
            HttpServletRequest request) {
        String token = request.getHeader("token");
        String username =  JwtUtils.GetUserName(token);
        CameraUrlService.add(addCameraUrlReq,username);

        return ApiRestResponse.success();
    }


    @PostMapping("/update")
    @ResponseBody
    public ApiRestResponse updateCameraUrl(
            @Valid @RequestBody UpdateCameraUrlReq updateCameraUrlReq) {

        CameraUrlService.update(updateCameraUrlReq);
        return ApiRestResponse.success();

    }

    @ApiOperation("删除")
    @GetMapping("/delete")
    @ResponseBody
    public ApiRestResponse deleteCameraUrl(@RequestParam("ids") Integer[] ids) {
        CameraUrlService.delete(ids);
        return ApiRestResponse.success();
    }

    @ApiOperation("有查询参数的分页列表")
    @PostMapping("/selectByQue")
    @ResponseBody
    public ApiRestResponse listBigSegementQue(@RequestBody CameraUrlQue CameraUrlQue) {
        PageInfo pageInfo = CameraUrlService.orUserListQue(CameraUrlQue);
        return ApiRestResponse.success(pageInfo);
    }
    @ApiOperation("根据ids查询")
    @PostMapping("/selectByIds")
    @ResponseBody
    public ApiRestResponse listByIds(Integer[] ids) {
        List<CameraUrl> pageInfo = CameraUrlService.listByIds(ids);
        return ApiRestResponse.success(pageInfo);
    }

    @ApiOperation(value = "导出Excel")
    @GetMapping("/exportList")
    public void exportMemberList(ModelMap map,
                                 HttpServletRequest request,
                                 HttpServletResponse response,
                                 @RequestBody CameraUrlQue CameraUrlQue) {
        List<CameraUrlVO> memberList = CameraUrlService.exportList(CameraUrlQue);
        ExportParams params = new ExportParams("视频链接", "视频链接", ExcelType.XSSF);
        map.put(NormalExcelConstants.DATA_LIST, memberList);
        map.put(NormalExcelConstants.CLASS, CameraUrlVO.class);
        map.put(NormalExcelConstants.PARAMS, params);
        map.put(NormalExcelConstants.FILE_NAME, "视频链接");
        PoiBaseView.render(map, request, response, NormalExcelConstants.EASYPOI_EXCEL_VIEW);
    }
}
