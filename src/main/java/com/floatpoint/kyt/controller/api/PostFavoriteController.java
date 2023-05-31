package com.floatpoint.kyt.controller.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.floatpoint.kyt.common.result.Result;
import com.floatpoint.kyt.common.utils.ControllerSuccessUtil;
import com.floatpoint.kyt.entity.vo.PostVO;
import com.floatpoint.kyt.service.PostFavoriteService;

import javax.validation.Valid;
import java.util.List;

@Api(description = "帖子收藏")
@RestController
@RequestMapping("/api/postFavorite")
public class PostFavoriteController {
    @Autowired
    PostFavoriteService postFavoriteService;

    /**
     * 收藏帖子/api/postFavorite/addPostFavorite
     * @param postId
     * @return
     */
    @ApiOperation("收藏帖子")
    @PostMapping("/addPostFavorite")
    public Result addPostFavorite(@ApiParam(name = "postId", value = "待收藏帖子id",required = true )
                                  @Valid @RequestBody String postId){
        boolean add= postFavoriteService.add(postId);
        return ControllerSuccessUtil.result(add);
    }

    /**
     * 分页查询个人收藏夹/api/postFavorite/page-MyFavorite/{current}/{limit}
     * @param current
     * @param limit
     * @return
     */
    @ApiOperation("分页查询个人收藏夹")
    @GetMapping("/page-MyFavorite/{current}/{limit}")
    public Result pageQueryByUser(@ApiParam(name = "current", value = "当前页码", required = true)
                                      @PathVariable("current") long current,
                                  @ApiParam(name = "limit", value = "页面上限", required = true)
                                      @PathVariable("limit") long limit) {
        List<PostVO> postVOS = postFavoriteService.pageQueryByUser(current, limit);
        return Result.success().data("row",postVOS);
    }

    /**
     * 取消收藏/api/postFavorite/deletePostFavorite
     * @param postId
     * @return
     */
    @ApiOperation("取消收藏")
    @DeleteMapping("/deletePostFavorite/{postId}")
    public Result removeFavorite(@ApiParam(name = "postId", value = "待删除收藏帖子id",required = true )
                                 @PathVariable("postId")            String postId)
    {
        boolean delete = postFavoriteService.delete(postId);
        return ControllerSuccessUtil.result(delete);
    }


    /**
     *
     * @param postId
     * @return
     */
    @ApiOperation("当前帖子是否被当前用户收藏")
    @GetMapping("/isFavorited/{postId}")
    public Result isFavorited(@ApiParam(name = "postId", value = "待确认的帖子id", required = true)
                              @PathVariable("postId") String postId){
        boolean isFavorited = postFavoriteService.isFavorite(postId);
        return Result.success().data("row",isFavorited);
    }






}
