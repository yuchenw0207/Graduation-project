package com.floatpoint.kyt.controller.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.floatpoint.kyt.common.result.Result;
import com.floatpoint.kyt.common.utils.ControllerSuccessUtil;
import com.floatpoint.kyt.entity.params.add.PostAddPA;
import com.floatpoint.kyt.entity.vo.PostVO;
import com.floatpoint.kyt.service.PostService;

import javax.validation.Valid;
import java.util.List;

@Api(description = "帖子相关")
@RestController
@RequestMapping("/api/post")
public class PostController {
    @Autowired
    private PostService postService;

    /**
     * 发帖  /api/post/add
     * @param postAddPA 发帖内容
     * @return Result中的success为true则增加成功,false失败;
     */
    @ApiOperation(value =  "发帖")
    @PostMapping("/add")
    public Result addPost(@ApiParam(name = "PostAddPA", value = "发帖内容", required = true)
                          @Valid  @RequestBody PostAddPA postAddPA) {
        List<PostVO> postVOS = postService.add(postAddPA);
        return Result.success().data("row",postVOS);
    }


    /**
     * 分页查询个人发帖记录 /api/post/page-myPost/{current}/{limit}
     * @param current 当前页码
     * @param limit 页面上限
     * @return Result中的success为true则增加成功,false失败; data中存放查询结果
     */
    @ApiOperation(value = "分页查询个人发帖")
    @GetMapping("/page-myPost/{current}/{limit}")
    public Result pageQueryMyPost(@ApiParam(name = "current", value = "当前页码", required = true)
                                  @PathVariable("current") long current,
                                  @ApiParam(name = "limit", value = "页面上限", required = true)
                                  @PathVariable("limit") long limit){
        List<PostVO> postVOS = postService.pageQueryMyPost(current,limit);
        return Result.success().data("row",postVOS);
    }


    /**
     * 分页按名字查询帖子 /api/post/page-postByName/{current}/{limit}/{title}
     * @param title 帖子标题
     * @return Result中的success为true则增加成功,false失败; data中存放查询结果
     */
    @ApiOperation(value = "按名字查询")
    @GetMapping("/queryPostByName/{title}")
    public Result QueryByName(@ApiParam(name = "title", value = "帖子标题", required = true)
                                  @PathVariable("title") String title){
        List<PostVO> postVOS = postService.QueryByName(title);
        return Result.success().data("row",postVOS);
    }

    /**
     * 帖子首页推荐的记录展示 /api/post/commend/{current}/{limit}
     * @param current 当前页码
     * @param limit 页面上限
     * @return Result中的success为true则增加成功,false失败; data中存放查询结果
     */
    @ApiOperation(value = "帖子首页推荐")
    @GetMapping("/commend/{current}/{limit}")
    public Result commend(@ApiParam(name = "current", value = "当前页码", required = true)
                              @PathVariable("current") long current,
                          @ApiParam(name = "limit", value = "页面上限", required = true)
                              @PathVariable("limit") long limit){
        List<PostVO> postVOS = postService.commend(current,limit);
        return Result.success().data("row",postVOS);
    }


    /**
     * 删除某个帖子 /api/post/deleteByPostId/{postId}
     * @param postId 待查询的帖子id
     * @return Result中的success为true则增加成功,false失败; data中存放查询结果
     */
    @ApiOperation(value = "删除贴子")
    @DeleteMapping("/deleteByPostId/{postId}")
    public Result deleteByPostId(@ApiParam(name = "postId", value = "待删除的帖子id", required = true)
                                 @PathVariable("postId") String postId)
    {
        boolean delete = postService.delete(postId);
        return Result.success().data("row",delete);
    }


}
