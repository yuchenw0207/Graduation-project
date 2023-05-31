package com.floatpoint.kyt.controller.api;

import com.floatpoint.kyt.common.result.Result;
import com.floatpoint.kyt.entity.params.get.SchoolGetPA;
import com.floatpoint.kyt.entity.vo.SchoolVO;
import com.floatpoint.kyt.service.SchoolService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Api(description = "学校信息相关")
@RestController
@RequestMapping("/api/school")
public class SchoolController {

    @Autowired
    private SchoolService schoolService;
    /**
        *查询学校信息 /api/school/get
     */
    @ApiOperation(value = "查询学校信息")
    @GetMapping("/get")
    public Result getMine(@ApiParam(name = "schoolGetPA", value = "查询学校信息", required = true)
                          @Valid SchoolGetPA schoolGetPA) {
        List<SchoolVO> schoolVOS = schoolService.get(schoolGetPA);
        return Result.success().data("row", schoolVOS);

    }


}
