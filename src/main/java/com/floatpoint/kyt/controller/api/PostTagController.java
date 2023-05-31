package com.floatpoint.kyt.controller.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.floatpoint.kyt.common.result.Result;
import com.floatpoint.kyt.common.utils.ControllerSuccessUtil;
import com.floatpoint.kyt.entity.params.add.PostTagAddPA;
import com.floatpoint.kyt.entity.vo.PostTagVO;
import com.floatpoint.kyt.entity.vo.PostVO;
import com.floatpoint.kyt.service.PostTagService;

import javax.validation.Valid;
import java.util.List;

@Api(description = "帖子的标签映射")
@RestController
@RequestMapping("/api/postTag")
public class PostTagController {
    @Autowired
    PostTagService postTagService;

    @ApiOperation(value = "添加标签")
    @PostMapping("/add")
    public Result addPostTag(@ApiParam(name = "postTagAddPA", value = "待添加的标签", required = true)
                              @Valid  @RequestBody PostTagAddPA postTagAddPA){
        boolean add = postTagService.add(postTagAddPA);
        return ControllerSuccessUtil.result(add);
    }


    @ApiOperation(value = "展示拥有某个标签的帖子")
    @GetMapping("/queryByTag/{tagId}")
    public Result queryByTag(@ApiParam(name = "tagId", value = "被查询的标签id", required = true)
                                 @PathVariable("tagId") String tagId){

        List<PostVO> postVOS = postTagService.queryByTag(tagId);
        return Result.success().data("row",postVOS);
    }

    @ApiOperation(value = "展示某个帖子的所有标签")
    @GetMapping("/queryByPost/{postId}")
    public Result queryByPost(@ApiParam(name = "postId", value = "被查询的帖子id", required = true)
                             @PathVariable("postId") String postId){

        List<PostTagVO> postTagVOS = postTagService.queryByPost(postId);
        return Result.success().data("row",postTagVOS);
    }
    @ApiOperation("删除某个帖子的标签")
    @DeleteMapping("/deletePostTag/{tagId}/{postId}")
    public Result delete(@ApiParam(name="tagId", value = "待去除的的标签", required = true)
                         @PathVariable("tagId") String tagId,
                         @ApiParam(name = "postId", value = "被操作的帖子", required = true)
                         @PathVariable("postId") String postId){
        System.out.println(tagId);
        boolean delete = postTagService.delete(tagId,postId);
        return ControllerSuccessUtil.result(delete);
    }

}
