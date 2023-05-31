package com.floatpoint.kyt.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.floatpoint.kyt.entity.dataobject.PostReply;
import com.floatpoint.kyt.entity.params.add.PostReplyAddPA;
import com.floatpoint.kyt.entity.vo.PostReplyVO;

import java.util.List;

public interface PostReplyService extends IService<PostReply> {

    boolean add(PostReplyAddPA postReplyAddPA);

    List<PostReplyVO> pageQueryByPost (long current, long limit, String postId);


}
