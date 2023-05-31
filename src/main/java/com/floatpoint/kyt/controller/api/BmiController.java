package com.floatpoint.kyt.controller.api;
import com.baomidou.mybatisplus.extension.api.R;
import com.floatpoint.kyt.common.utils.ControllerSuccessUtil;
import com.floatpoint.kyt.common.utils.kytUtil;
import com.floatpoint.kyt.entity.dataobject.Bmi;
import com.floatpoint.kyt.entity.params.add.BmiAddPA;
import com.floatpoint.kyt.common.result.Result;
import com.floatpoint.kyt.service.BmiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(description = "BMI")
@RestController
@RequestMapping("/api/bmi")
public class BmiController {
    @Autowired
    private BmiService bmiService;
    @ApiOperation(value = "上传身高体重")
    @PostMapping("/addwh")
    public Result addwh(@RequestBody BmiAddPA bmiAddPA){
        boolean add=bmiService.addwh(bmiAddPA);
        return ControllerSuccessUtil.result(add);
    }
    @ApiOperation(value = "查询判断")
    @GetMapping("/searchjudge")
    public Result searchjudge(){
        String str;
        List<Bmi> searchjudge=bmiService.searchjudge(kytUtil.getUserId());
        if(searchjudge.size()==0)
        { str="0";
            return Result.success().data("row",str);}
        //System.out.println(searchjudge);
        else
        {
            str="1";
            return Result.success().data("row",str);}
    }

    @ApiOperation(value = "查询信息")
    @GetMapping("/searchbmi")
    public Result searchbmi(){

        List<Bmi> searchbmi=bmiService.searchbmi(kytUtil.getUserId());
        return Result.success().data("row",searchbmi);
    }

    @ApiOperation(value = "更新身高体重")
    @GetMapping("/updatawh")
    @ResponseBody
    public Result updatawh(@RequestParam("height")String height,@RequestParam("weight")String weight,@RequestParam("gender")String gender,@RequestParam("age")String age){
        boolean updatawh=bmiService.updatawh(kytUtil.getUserId(),height,weight,gender,age);
        return ControllerSuccessUtil.result(updatawh);
    }
}
