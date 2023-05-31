package com.floatpoint.kyt.controller.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.floatpoint.kyt.common.result.Result;
import com.floatpoint.kyt.common.utils.ControllerSuccessUtil;
import com.floatpoint.kyt.service.PostLikeService;

import javax.validation.Valid;

@Api(description = "帖子点赞")
@RestController
@RequestMapping("/api/postLike")
public class PostLikeController {
    @Autowired
    PostLikeService postLikeService;


    @ApiOperation("点赞帖子")
    @PostMapping("/postAdd")
    public Result postAdd(@ApiParam(name = "postId", value = "待点赞帖子", required = true)
                           @Valid @RequestBody String postId)
    {
        System.out.println(postId);
        boolean add = postLikeService.add(postId);
        return ControllerSuccessUtil.result(add);
    }


    @ApiOperation("当前帖子是否已被点赞")
    @GetMapping("/isLike/{postId}")
    public Result isLike(@ApiParam(name = "postId", value = "被检测帖子", required = true)
                         @PathVariable(value = "postId") String postId){
        boolean isLike = postLikeService.isLike(postId);
        return Result.success().data("row",isLike);
    }

    @ApiOperation("删除点赞")
    @DeleteMapping("/deleteLike/{postId}")
    public Result deleteLike(@ApiParam(name = "postId", value = "被取消的帖子", required = true)
                                 @PathVariable(value = "postId") String postId){
        boolean delete= postLikeService.delete(postId);
        return ControllerSuccessUtil.result(delete);
    }










}
