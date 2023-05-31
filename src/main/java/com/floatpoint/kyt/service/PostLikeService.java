package com.floatpoint.kyt.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.floatpoint.kyt.entity.dataobject.PostLike;

public interface PostLikeService extends IService<PostLike> {
    boolean add(String postId);//点赞

    boolean isLike(String postId);//当前帖子是否被当前用户点赞过

    boolean delete (String postId);//取消点赞

}
