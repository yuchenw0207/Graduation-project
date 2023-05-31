package com.floatpoint.kyt.controller.api;

import com.floatpoint.kyt.common.result.Result;
import com.floatpoint.kyt.entity.params.add.SeniorAddPA;
import com.floatpoint.kyt.entity.params.query.SeniorSelectPA;
import com.floatpoint.kyt.entity.params.update.SeniorPositiveUpdatePA;
import com.floatpoint.kyt.entity.params.update.SeniorUpdatePA;
import com.floatpoint.kyt.entity.vo.SeniorInfoVO;
import com.floatpoint.kyt.service.SeniorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Api(description = "学长学姐相关")
@RestController
@RequestMapping("/api/senior")
public class SeniorController {

    @Autowired
    private SeniorService seniorService;

    @ApiOperation(value = "查询用户作为学长学姐的信息")
    @GetMapping("/queryMySeniorInfo")
    public Result queryMySeniorInfo (){
        List<SeniorInfoVO> seniorInfoVOS = seniorService.queryMySeniorInfo();
        return Result.success().data("row",seniorInfoVOS);
//        SeniorInfoVO seniorInfoVO = seniorService.getMySeniorInfo();
//        return Result.success().data("row",seniorInfoVO);
    }

    @ApiOperation(value = "查询所有学长学姐的信息")
    @GetMapping("/getSeniorInfo")
    public Result getSeniorInfo (){
        List<SeniorInfoVO> seniorInfoVOS = seniorService.getSeniorInfo();
        return Result.success().data("row",seniorInfoVOS);
    }

    @ApiOperation(value = "根据筛选条件查询学长学姐的信息")
    @GetMapping("/getSelectSeniorInfo")
    public Result getSelectSeniorInfo (@ApiParam(name="SeniorUpdatePA",value = "根据筛选条件查询学长学姐的信息" , required = true)
                                       @Valid SeniorSelectPA seniorSelectPA){
        List<SeniorInfoVO> seniorInfoVOS = seniorService.getSelectSeniorInfo(seniorSelectPA);
        return Result.success().data("row",seniorInfoVOS);
    }

    @ApiOperation(value = "更新自己学长学姐的信息")
    @GetMapping("/setMySeniorInfo/{SeniorUpdatePA}")
    public Result setMySeniorInfo (@ApiParam(name="SeniorUpdatePA",value = "更新自己学长学姐的信息" , required = true)
                                   @Valid SeniorUpdatePA seniorUpdatePA){
        boolean update = seniorService.setMySeniorInfo(seniorUpdatePA);
        return Result.success().data("row",update);
    }

    @ApiOperation(value = "评价该学长学姐")
    @GetMapping("/updatePositiveRating/{SeniorPositiveUpdatePA}")
    public Result updatePositiveRating (@ApiParam(name="SeniorPositiveUpdatePA",value = "评价该学长学姐" , required = true)
                                        @Valid SeniorPositiveUpdatePA seniorPositiveUpdatePA){
        boolean update = seniorService.updatePositiveRating(seniorPositiveUpdatePA);
        return Result.success().data("row",update);
    }

    @ApiOperation(value = "成为学长学姐")
    @GetMapping("/tobeSenior")
    public Result tobeSenior (@ApiParam(name="SeniorAddPA",value = "成为学长学姐" , required = true)
                              @Valid SeniorAddPA seniorAddPA){
        boolean add = seniorService.addSenior(seniorAddPA);
        return Result.success().data("row", add);
    }

}
