package com.floatpoint.kyt.controller.api;

import com.floatpoint.kyt.common.result.Result;
import com.floatpoint.kyt.common.utils.ControllerSuccessUtil;
import com.floatpoint.kyt.common.utils.kytUtil;
import com.floatpoint.kyt.entity.dataobject.Material;

import com.floatpoint.kyt.entity.params.add.MaterialAddPA;
import com.floatpoint.kyt.service.MaterialService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/*主要实现内容有  新增帖子，删除帖子，主页资料推荐，资料详情，帖子搜索*/
@Api(description = "帖子详情")
@RestController
@RequestMapping("/api/material")
public class MaterialController {
    @Autowired
    private MaterialService materialService;

    /*添加资料
     * 添加 title，textconten，imageconten  均为String
     *
     * */
    @ApiOperation(value = "新建资料")
    @PostMapping("/add")
    public Result addMine(@RequestBody MaterialAddPA materialAddPA){
        boolean add =materialService.add(materialAddPA);
        return ControllerSuccessUtil.result(add);
    }



    /*
     * 不需任何参数，根据userid来访问他的浏览记录，查询结果为tag_name
     * */
    @ApiOperation(value = "主页推荐搜索")
    @GetMapping("/recommend")
    @ResponseBody
    public Result recommend(){
        List<Material> recommend= materialService.recommend();
        return Result.success().data("row",recommend);
    }


    /*
     * 搜索功能
     * 用户输入tag_id
     * 返回title materialId favoriteCount
     * */
    @ApiOperation(value = "搜索")
    @GetMapping("/search")
    @ResponseBody
    public Result search(@RequestParam("tag") String tag){
        List<Material> search = materialService.search(tag);
        return Result.success().data("row",search);
    }


    /*
     * 用于打开资料
     * 输入materialId
     * 返回m1.title,m1.text_content,m1.image_content,m1.poster_id,m1.create_date,m1.create_date,m1.modify_date*/
    @ApiOperation(value = "资料的详情")
    @GetMapping("/detail")
    @ResponseBody
    public Result detail (@RequestParam("materialId") String materialId){
        List<Material> detail = materialService.detail(materialId);
        return Result.success().data("row",detail);
    }

    /*
     * 删除资料
     * 输入  poster Id，materialId
     * 返回boolean值判断是否成功*/
    @ApiOperation(value = "删除资料")
    @GetMapping("/deletede")
    @ResponseBody
    public Result  deletede (@RequestParam("materialId")String materialId){
        boolean deletede = materialService.deletede(kytUtil.getUserId(),materialId);
        return ControllerSuccessUtil.result(deletede);

    }

    @ApiOperation(value = "浏览记录")
    @GetMapping("/searchbro")
    public Result searchbro() {
        List<Material> searchbro = materialService.searchbro(kytUtil.getUserId());
        return Result.success().data("row",searchbro);
    }
    @ApiOperation(value = "收藏记录")
    @GetMapping("/searchfav")
    public Result searchfav() {
        List<Material> searchfav= materialService.searchfav(kytUtil.getUserId());
        return Result.success().data("row",searchfav);
    }

    @ApiOperation(value = "查询最后添加")
    @GetMapping("/selectlast")
    @ResponseBody
    public Result  selectlast (){
        List<Material> select = materialService.selectlast();
        return Result.success().data("row",select);

    }
}
