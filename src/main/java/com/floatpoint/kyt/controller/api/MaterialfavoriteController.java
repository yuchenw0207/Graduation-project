package com.floatpoint.kyt.controller.api;

import com.floatpoint.kyt.common.result.Result;
import com.floatpoint.kyt.entity.dataobject.Materialfavorite;
import com.floatpoint.kyt.entity.params.add.MaterialfavoriteAddPA;
import com.floatpoint.kyt.service.MaterialfavoriteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.floatpoint.kyt.common.utils.*;
import java.util.List;
/*实现步骤为首先调用jude判断是否已经存在喜欢，如过存在返回false
* 不存在则1.帖子的喜欢书加一
* 2.将喜欢记录添加到materialfavorite表
*
* 取消喜欢1.帖子喜欢减一
* 2.删除表中喜欢记录*/
@Api(description = "喜欢")
@RestController
@RequestMapping("/api/Materialfavorite")
public class MaterialfavoriteController {
    @Autowired
    private MaterialfavoriteService materialfavoriteService;


    /*输入materialId  结果使favoritecount+1
    * 返回boolean值*/
    @ApiOperation(value = "喜欢数量+1")
    @GetMapping("/favorite")
    @ResponseBody
    public Result favorite(@RequestParam("materialId")String materialId)
    {
        //userId=TodoListUtil.getUserId();
        boolean favorite =materialfavoriteService.favoritecount(materialId);

        return ControllerSuccessUtil.result(favorite);
    }

    /*输入materialId  结果使favoritecount-1
     * 返回boolean值*/
    @ApiOperation(value = "喜欢数量-1")
    @GetMapping("/decfavorite")
    @ResponseBody
    public Result decfavorite( @RequestParam("materialId")String materialId)
    {
        //userId=TodoListUtil.getUserId();
        boolean favorite =materialfavoriteService.decfavoritecount(materialId);

        return ControllerSuccessUtil.result(favorite);
    }


    /*输入materiId，，自动获取userId，添加到喜欢表中*/
    @ApiOperation(value = "添加喜欢")
    @PostMapping("/add")
    public Result addMine(@RequestBody MaterialfavoriteAddPA materialfavoriteAddPA){

        boolean add =materialfavoriteService.add(materialfavoriteAddPA);
        return ControllerSuccessUtil.result(add);
    }


    /*输入materialId，，判断是否已经存在喜欢*/
    @ApiOperation(value = "判断是否已经喜欢")
    @GetMapping("/jude")
    @ResponseBody
    public Result jude(@RequestParam("materialId")String materialId)
    {
        List<Materialfavorite> materialfavorites=materialfavoriteService.jude(kytUtil.getUserId(),materialId);
        return Result.success().data("row",materialfavorites);
    }


    /*取消喜欢，，输入materialId*/
    @ApiOperation(value = "取消喜欢")
    @GetMapping("/deletefav")
    @ResponseBody
    public Result deletefav(@RequestParam("materialId")String materialId)
    {
        boolean deletefav=materialfavoriteService.deletefav(kytUtil.getUserId(),materialId);
        return ControllerSuccessUtil.result(deletefav);
    }


}
