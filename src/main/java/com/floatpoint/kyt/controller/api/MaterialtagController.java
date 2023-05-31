package com.floatpoint.kyt.controller.api;

import com.floatpoint.kyt.common.result.Result;
import com.floatpoint.kyt.entity.dataobject.Materialtag;

import com.floatpoint.kyt.entity.params.add.MaterialtagAddPA;
import com.floatpoint.kyt.service.MaterialtagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.floatpoint.kyt.common.utils.*;
import java.util.List;

@Api(description = "标签")
@RestController
@RequestMapping("/api/Materialtag")

/*主要实现搜索界面历史标签的展现*/
public class MaterialtagController {
    @Autowired
    private MaterialtagService materialtagService;

    /*不用输入任何值，直接通过userId访问其浏览记录，再搜索浏览记录的标签*/
    @ApiOperation(value = "历史搜索标签")
    @GetMapping("/history")
    @ResponseBody
    public Result history(){
        List<Materialtag> history = materialtagService.history(kytUtil.getUserId());
        return Result.success().data("row",history);
    }

    @ApiOperation(value = "增加标签")
    @PostMapping("/addtag")
    public Result addtag(@RequestBody MaterialtagAddPA materialtagAddPA){
        boolean addtag =materialtagService.addtag(materialtagAddPA);
        return ControllerSuccessUtil.result(addtag);
    }

    @ApiOperation(value = "删除标签")
    @DeleteMapping("/deletetag")
    @ResponseBody
    private Result deteletag(@RequestParam("materialId") String materialId,@RequestParam("tagName") String tagName)
    {
        boolean deletedetag = materialtagService.deletetag(materialId,tagName);
        return ControllerSuccessUtil.result(deletedetag);

    }

    @ApiOperation(value = "删除资料标签")
    @GetMapping("/deletemt")
    @ResponseBody
    private Result detelemt(@RequestParam("materialId") String materialId)
    {
        boolean deletemt = materialtagService.deletemt(materialId);
        return ControllerSuccessUtil.result(deletemt);

    }


}
