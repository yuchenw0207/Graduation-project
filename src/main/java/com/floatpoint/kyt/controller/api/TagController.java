package com.floatpoint.kyt.controller.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.floatpoint.kyt.common.result.Result;
import com.floatpoint.kyt.common.result.ResultCode;
import com.floatpoint.kyt.common.utils.ControllerSuccessUtil;
import com.floatpoint.kyt.common.utils.kytUtil;
import com.floatpoint.kyt.common.utils.ParamCheckUtil;
import com.floatpoint.kyt.entity.params.add.TagAddPA;
import com.floatpoint.kyt.entity.vo.TagVO;
import com.floatpoint.kyt.service.TagService;

import javax.validation.Valid;
import java.util.List;

/**
 * 标签相关
 */
@Api(description = "标签相关")
@RestController
@RequestMapping("/api/Tag")
public class TagController {

    @Autowired
    private TagService tagService;
    /**
     * 新建标签/api/tag/add
     */
    @ApiOperation(value = "新建标签")
    @PostMapping("/add")
    public Result addTag(@ApiParam(name = "TagNewAddPA", value = "待新建标签", required = true)
                             @Valid @RequestBody TagAddPA tagAddPA){
        boolean add =tagService.add(tagAddPA);
        return ControllerSuccessUtil.result(add);
    }

    /**
     *删除标签/api/tag/delete/{id}
     */
    @ApiOperation(value = "逻辑删除标签")
    @DeleteMapping("/delete/{tag_id}")
    public Result deleteMine(@ApiParam(name = "tag_id", value = "待删除标签id", required = true)
                                 @PathVariable("tag_id") String id){
        if (ParamCheckUtil.isNotRightId(id)) {
            return Result.fail(ResultCode.PARAM_NOT_VALID).message("id格式错误");
        }
        boolean remove = tagService.removeById(id);
        return ControllerSuccessUtil.result(remove);
    }
    /**
     * 查看所有tag标签
     */
    @ApiOperation(value = "渲染所有标签")
    @GetMapping("/list-tag")
    public Result tagList(){
        String userId = kytUtil.getUserId();
        List<TagVO> tagVOS=tagService.TagList();
        return Result.success().data("row",tagVOS);
    }

    @ApiOperation(value ="查询标签次数")
    @PutMapping("/update/{tag_id}")
    public Result addQueryCount(@ApiParam(name = "tag_id", value = "查询标签id", required = true)
                      @PathVariable("tag_id") String id){
        if (ParamCheckUtil.isNotRightId(id)) {
            return Result.fail(ResultCode.PARAM_NOT_VALID).message("id格式错误");
        }
        boolean change = tagService.change(id);
        return ControllerSuccessUtil.result(change);
    }
}
