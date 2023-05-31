package com.floatpoint.kyt.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.floatpoint.kyt.entity.dataobject.PostBrowse;
import com.floatpoint.kyt.entity.params.add.PostBrowseAddPA;
import com.floatpoint.kyt.entity.vo.PostVO;

import java.util.List;

public interface PostBrowseService extends IService<PostBrowse> {

        boolean add(PostBrowseAddPA postBrowseAddPA);

        boolean delete(String browseId);

        List<PostVO> QueryMyBrowse();
}
