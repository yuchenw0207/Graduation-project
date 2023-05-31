package com.floatpoint.kyt.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.floatpoint.kyt.entity.dataobject.PostTag;
import com.floatpoint.kyt.entity.params.add.PostTagAddPA;
import com.floatpoint.kyt.entity.vo.PostTagVO;
import com.floatpoint.kyt.entity.vo.PostVO;

import java.util.List;

public interface PostTagService extends IService<PostTag> {

    boolean add (PostTagAddPA postTagAddPA);

    List<PostVO> queryByTag(String tagId);

    List<PostTagVO> queryByPost(String postId);

    boolean delete(String tagId, String postId);

}
