package com.floatpoint.kyt.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.floatpoint.kyt.entity.dataobject.Post;
import com.floatpoint.kyt.entity.params.add.PostAddPA;
import com.floatpoint.kyt.entity.vo.PostVO;

import java.util.List;

public interface PostService extends IService<Post> {

    List<PostVO> add (PostAddPA postAddPa) ;

    List<PostVO> pageQueryMyPost (long current, long limit) ;//分页查询我的帖子

    PostVO queryById (String id);//按id查询帖子信息

    List<PostVO> QueryByName (String title);//按名字分页查询帖子

    boolean delete(String postId);//删除帖子

    List<PostVO> commend(long current, long limit);//推荐算法
}
