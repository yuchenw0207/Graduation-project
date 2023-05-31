package com.floatpoint.kyt.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.floatpoint.kyt.entity.params.add.MessageAddPA;
import com.floatpoint.kyt.service.MessageService;
import com.floatpoint.kyt.service.UserService;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.floatpoint.kyt.common.utils.kytUtil;
import com.floatpoint.kyt.entity.dataobject.Post;
import com.floatpoint.kyt.entity.dataobject.PostReply;
import com.floatpoint.kyt.entity.params.add.PostReplyAddPA;
import com.floatpoint.kyt.entity.vo.PostReplyVO;
import com.floatpoint.kyt.mapper.PostReplyMapper;
import com.floatpoint.kyt.service.PostReplyService;
import com.floatpoint.kyt.service.PostService;

import java.util.ArrayList;
import java.util.List;

@Accessors(chain = true)
@Service
public class PostReplyServiceImpl extends ServiceImpl<PostReplyMapper, PostReply> implements PostReplyService {

    @Autowired
    PostService postService;

    @Autowired
    MessageService messageService;

    @Autowired
    UserService userService;

    /**
     * 添加一条针对某个帖子的回复记录
     * @param postReplyAddPA
     * @return
     */
    public boolean add(PostReplyAddPA postReplyAddPA)
    {
        Post post = new Post();
        PostReply postReply = new PostReply();
        BeanUtils.copyProperties(postReplyAddPA, postReply);
        postReply.setUserId(kytUtil.getUserId())
                .setPostId(postReplyAddPA.getPostId())
                .setDetail(postReplyAddPA.getDetail())
                .setIsDelete(0);
        post = postService.getById(postReply.getPostId());//回复对应的帖子
        postService.saveOrUpdate(post);
        MessageAddPA messageAddPA = new MessageAddPA();
        messageAddPA.setType(1);
        messageAddPA.setReceiverId(post.getPosterId());
        messageAddPA.setContent(userService.getById(kytUtil.getUserId()).getUserName1()+"评论了您的帖子 "+post.getTitle());
        messageService.add(messageAddPA);
        return save(postReply);
    }

    /**
     * 按帖子展示回复
     * @param current
     * @param limit
     * @param postId
     * @return
     */
    public List<PostReplyVO> pageQueryByPost (long current, long limit, String postId)
    {

        Page<PostReply> pagePostReply = new Page<>(current, limit);
        QueryWrapper<PostReply> wrapper = new QueryWrapper<>();
        wrapper.eq("post_id",postId)
                .orderByDesc("create_date");
        this.page(pagePostReply,wrapper);
        List<PostReply> records = pagePostReply.getRecords();
        List<PostReplyVO> postReplyVOS = new ArrayList<>();
        for(PostReply record : records)
        {
            PostReplyVO postReplyVO = new PostReplyVO();
            BeanUtils.copyProperties(record, postReplyVO);
            postReplyVO.setUserName(userService.getById(record.getUserId()).getUserName1());
            postReplyVOS.add(postReplyVO);
        }
        return postReplyVOS;
    }



}
