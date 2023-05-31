package com.floatpoint.kyt.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.floatpoint.kyt.entity.dataobject.PostFavorite;
import com.floatpoint.kyt.entity.vo.PostVO;

import java.util.List;

public interface PostFavoriteService extends IService<PostFavorite> {
    boolean add(String postId);

    List<PostVO> pageQueryByUser(long current, long limit);

    boolean delete (String postId);

    boolean isFavorite(String postId);
}
