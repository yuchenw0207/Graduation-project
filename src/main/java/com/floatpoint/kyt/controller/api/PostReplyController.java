package com.floatpoint.kyt.controller.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.floatpoint.kyt.common.result.Result;
import com.floatpoint.kyt.common.utils.ControllerSuccessUtil;
import com.floatpoint.kyt.entity.params.add.PostReplyAddPA;
import com.floatpoint.kyt.entity.vo.PostReplyVO;
import com.floatpoint.kyt.service.PostReplyService;

import javax.validation.Valid;
import java.util.List;

@Api(description = "帖子的回复")
@RestController
@RequestMapping("/api/postReply")
public class PostReplyController {
    @Autowired
    private PostReplyService postReplyService;

    /**
     * 回复一个帖子，新增一条回复记录
     * @param postReplyAddPA
     * @return
     */
    @ApiOperation(value = "回复帖子")
    @PostMapping("/add")
    public Result addPostReply(@ApiParam(name = "postReplyAddPA", value = "待添加的回复",required = true)
                               @Valid @RequestBody PostReplyAddPA postReplyAddPA){
        boolean add;
        add = postReplyService.add(postReplyAddPA);
        return ControllerSuccessUtil.result(add);
    }


    /**
     * 分页查询某个帖子的所有回复
     * @param current
     * @param limit
     * @param postId
     * @return
     */
    @ApiOperation(value = "分页查询某个帖子的回复")
    @GetMapping("/page-PostReply/{current}/{limit}/{postId}")
    public Result pageQueryByPost(@ApiParam(name = "current", value = "当前页码",required = true)
                                  @PathVariable("current")long current,
                                  @ApiParam(name = "limit", value = "每页上限",required = true)
                                  @PathVariable("limit")long limit,
                                  @ApiParam(name = "postId", value = "被展示的帖子id",required = true)
                                      @PathVariable("postId") String postId){

        List<PostReplyVO> postReplyVOS= postReplyService.pageQueryByPost(current,limit,postId);
        return Result.success().data("row",postReplyVOS);
    }

}
