package com.example.pccp.controller.base;

import cn.afterturn.easypoi.entity.vo.NormalExcelConstants;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import cn.afterturn.easypoi.view.PoiBaseView;
import com.example.pccp.common.ApiRestResponse;
import com.example.pccp.model.pojo.AudioUrl;
import com.example.pccp.model.query.AudioUrQue;
import com.example.pccp.model.request.AddAudioUrReq;
import com.example.pccp.model.request.UpdateAudioUrReq;
import com.example.pccp.model.vo.AudioUrlVO;
import com.example.pccp.service.AudioUrService;
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
@Api(description = "音频链接")
@RequestMapping("api/audioUrl")
@Controller
public class AudioUrlController {
    @Autowired
    AudioUrService audioUrlService;

    @PostMapping("/add")
    @ResponseBody
    public ApiRestResponse addAudioUr(
            @RequestBody @Valid AddAudioUrReq addAudioUrReq,
            HttpServletRequest request) {
        String token = request.getHeader("token");
        String username =  JwtUtils.GetUserName(token);
        audioUrlService.add(addAudioUrReq,username);

        return ApiRestResponse.success();
    }


    @PostMapping("/update")
    @ResponseBody
    public ApiRestResponse updateAudioUr(
            @Valid @RequestBody UpdateAudioUrReq updateAudioUrReq) {

        audioUrlService.update(updateAudioUrReq);
        return ApiRestResponse.success();

    }

    @ApiOperation("删除")
    @GetMapping("/delete")
    @ResponseBody
    public ApiRestResponse deleteAudioUr(@RequestParam("ids") Integer[] ids) {
        audioUrlService.delete(ids);
        return ApiRestResponse.success();
    }

    @ApiOperation("有查询参数的分页列表")
    @PostMapping("/selectByQue")
    @ResponseBody
    public ApiRestResponse listBigSegementQue(@RequestBody AudioUrQue audioUrlQue) {
        PageInfo pageInfo = audioUrlService.orUserListQue(audioUrlQue);
        return ApiRestResponse.success(pageInfo);
    }
    @ApiOperation("根据ids查询")
    @PostMapping("/selectByIds")
    @ResponseBody
    public ApiRestResponse listByIds(Integer[] ids) {
        List<AudioUrl> pageInfo = audioUrlService.listByIds(ids);
        return ApiRestResponse.success(pageInfo);
    }

    @ApiOperation(value = "导出Excel")
    @GetMapping("/exportList")
    public void exportMemberList(ModelMap map,
                                 HttpServletRequest request,
                                 HttpServletResponse response,
                                 @RequestBody AudioUrQue audioUrlQue) {
        List<AudioUrlVO> memberList = audioUrlService.exportList(audioUrlQue);
        ExportParams params = new ExportParams("音频链接", "音频链接", ExcelType.XSSF);
        map.put(NormalExcelConstants.DATA_LIST, memberList);
        map.put(NormalExcelConstants.CLASS, AudioUrlVO.class);
        map.put(NormalExcelConstants.PARAMS, params);
        map.put(NormalExcelConstants.FILE_NAME, "音频链接");
        PoiBaseView.render(map, request, response, NormalExcelConstants.EASYPOI_EXCEL_VIEW);
    }
}
