package com.floatpoint.kyt.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
import com.floatpoint.kyt.entity.dataobject.PostLike;
import com.floatpoint.kyt.mapper.PostLikeMapper;
import com.floatpoint.kyt.service.PostLikeService;
import com.floatpoint.kyt.service.PostService;

import java.util.ArrayList;
import java.util.List;

@Accessors(chain = true)
@Service
public class PostLikeServiceImpl extends ServiceImpl<PostLikeMapper, PostLike> implements PostLikeService {

    @Autowired
    PostService postService;

    @Autowired
    MessageService messageService;

    @Autowired
    UserService userService;

    @Override
    public boolean add(String postId){
        Post post = new Post();
        PostLike postLike = new PostLike();
        postLike.setPostId(postId)
                .setUserId(kytUtil.getUserId())
                .setIsDelete(0);
        BeanUtils.copyProperties(postService.getById(postId), post);
        post.setLikeCount(post.getLikeCount()+1);
        postService.saveOrUpdate(post);
        //设置发送消息
        MessageAddPA messageAddPA = new MessageAddPA();
        messageAddPA.setReceiverId(post.getPosterId());
        messageAddPA.setContent(userService.querySeniorById(kytUtil.getUserId())+"点赞了您的帖子"+" "+post.getTitle());
        messageAddPA.setType(0);//0为点赞
        messageService.add(messageAddPA);
        return  save(postLike);
    }

    @Override
    public boolean isLike(String postId)
    {
        QueryWrapper<PostLike> wrapper = new QueryWrapper<>();
        List<PostLike> postLikes= new ArrayList<>();
        wrapper.eq("post_id", postId)
                .eq("user_id",kytUtil.getUserId());
        postLikes = baseMapper.selectList(wrapper);
        return !postLikes.isEmpty();
    }

    @Override
    public boolean delete(String postId)
    {
        Post post = new Post();
        QueryWrapper<PostLike> wrapper = new QueryWrapper<>();
        wrapper.eq("post_id", postId)
                .eq("user_id",kytUtil.getUserId());
        BeanUtils.copyProperties(postService.getById(postId), post);
        post.setLikeCount(post.getLikeCount()-1);
        postService.saveOrUpdate(post);
        return remove(wrapper);
    }

}
