package com.floatpoint.kyt.controller.api;

import com.floatpoint.kyt.entity.dataobject.Punch;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.floatpoint.kyt.common.result.Result;
import com.floatpoint.kyt.common.utils.ControllerSuccessUtil;
import com.floatpoint.kyt.common.utils.kytUtil;
import com.floatpoint.kyt.entity.params.add.PunchAddPA;
import com.floatpoint.kyt.entity.vo.PunchVO;
import com.floatpoint.kyt.service.PunchService;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

@Api(description = "打卡记录相关")
@RestController
@RequestMapping("/api/punch")
public class PunchController {
    @Autowired
    private PunchService punchService;

    /**
     *新建打卡记录 /api/punch/add
     */
    @ApiOperation(value = "添加")
    @GetMapping("/add")
    public Result addPunch(@ApiParam(name = "punchAddPA", value = "新建打卡记录", required = true)
                               @Valid  PunchAddPA punchAddPA){
        Date date=new Date();
        int year=date.getYear();
        int month=date.getMonth();
        int day=date.getDate();
        System.out.println(year);
        System.out.println(month);
        System.out.println(day);

        List<PunchVO> punches=punchService.punchList();
        if (punches.size()==0){
            boolean add = punchService.add(punchAddPA);
            return Result.success().data("row", add);
        }
        Date date1=punches.get(0).getCreateDate();
        int year1=date1.getYear();
        int month1=date1.getMonth();
        int day1=date1.getDate();
        System.out.println(year1);
        System.out.println(month1);
        System.out.println(day1);
        if(year==year1&&month==month1&&day==day1) {
            return Result.success().data("row", false);
        }
        else{
            boolean add = punchService.add(punchAddPA);
            return Result.success().data("row", add);
        }
    }

    /**
     * 查看所有打卡记录  /api/punch/
     */
    @ApiOperation(value = "分页查询所有打卡记录")
    @GetMapping("/punch-list")
    public Result messageList() {
        String userId = kytUtil.getUserId();
        List<PunchVO> punchVOS = punchService.punchList();
        return Result.success().data("row", punchVOS);
    }
    /**
     * 查询当月打卡记录
     */
    @ApiOperation(value ="查询当月打卡天数")
    @GetMapping("/getMonth/{year}/{month}")
    public Result getMonthList(@ApiParam(name = "year", value = "查询的年份", required = true)
                                   @PathVariable("year") String year,
                                   @ApiParam(name = "month", value = "查询的月份", required = true)
                                    @PathVariable("month") String month) throws ParseException {
        List<Integer> tempList=punchService.getLocalMonth(year,month);
        return Result.success().data("row",tempList);

    }
}
