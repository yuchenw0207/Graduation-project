package com.floatpoint.kyt.controller.api;


import com.floatpoint.kyt.common.result.Result;
import com.floatpoint.kyt.entity.dataobject.Materialbrowse;
import com.floatpoint.kyt.entity.params.add.MaterialbrowseAddPA;
import com.floatpoint.kyt.service.MaterialbrowseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.floatpoint.kyt.common.utils.*;
import java.util.List;

@Api(description = "浏览")
@RestController
@RequestMapping("/api/Materialbrowse")
public class MaterialbrowseController {
    @Autowired
    private MaterialbrowseService materialbrowseService;
    @ApiOperation(value = "添加浏览")
    @PostMapping("/add")
    public Result addMine(@RequestBody MaterialbrowseAddPA materialbrowseAddPA){

        boolean add =materialbrowseService.add(materialbrowseAddPA);
        return ControllerSuccessUtil.result(add);
    }



    @ApiOperation(value = "删除指定资料浏览记录")
    @GetMapping("/deletedebro")
    @ResponseBody
    public Result  deletebro (@RequestParam("materialId")String materialId){
        boolean deletedebro = materialbrowseService.deletebro(kytUtil.getUserId(),materialId);
        return ControllerSuccessUtil.result(deletedebro);

    }

    @ApiOperation(value = "删除所有资料浏览记录")
    @GetMapping("/deletedeall")
    @ResponseBody
    public Result  deleteall (){
        boolean deleteall = materialbrowseService.deleteall(kytUtil.getUserId());
        return ControllerSuccessUtil.result(deleteall);

    }

}
