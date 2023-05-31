package com.floatpoint.kyt.controller.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.floatpoint.kyt.common.result.Result;
import com.floatpoint.kyt.common.utils.ControllerSuccessUtil;
import com.floatpoint.kyt.entity.params.add.PostBrowseAddPA;
import com.floatpoint.kyt.entity.vo.PostVO;
import com.floatpoint.kyt.service.PostBrowseService;

import javax.validation.Valid;
import java.util.List;

@Api(description = "帖子浏览记录")
@RestController
@RequestMapping("/api/PostBrowse")
public class PostBrowseController {
    @Autowired
    private PostBrowseService postBrowseService;

    /**
     * 增加帖子浏览记录 /api/PostBrowse/addPostBrowse
     * @param postBrowseAddPA
     * @return  Result中的success为true则增加成功,false失败
     */
    @ApiOperation("增加帖子浏览记录")
    @PostMapping("/addPostBrowse")
    public Result addPostBrowse(@ApiParam(name = "postBrowseAddPA", value = "某条帖子浏览记录", required = true)
                                @Valid @RequestBody PostBrowseAddPA postBrowseAddPA) {

        boolean add = postBrowseService.add(postBrowseAddPA);
        return ControllerSuccessUtil.result(add);
    }

    /**
     * 查询个人帖子浏览记录/api/PostBrowse/page-MyBrowse/{current}/{limit}
     * @return Result中的success为true则增加成功,false失败; data中存放查询结果
     */
    @ApiOperation("查询个人的帖子浏览记录")
    @GetMapping("/QueryMyBrowse")
    public Result pageQueryMyBrowse() {
        List<PostVO> postVOS = postBrowseService.QueryMyBrowse();
        return Result.success().data("row",postVOS);
    }


    @ApiOperation("删除帖子浏览记录")
    @DeleteMapping("/delete/{postId}")
    public Result delete(@ApiParam(name = "postId", value = "待删除记录的id", required = true)
                         @Valid @PathVariable("postId") String postId)
    {
        boolean delete = postBrowseService.delete(postId);
        return ControllerSuccessUtil.result(delete);
    }

}
