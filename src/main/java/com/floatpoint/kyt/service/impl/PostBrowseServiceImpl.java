package com.floatpoint.kyt.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.floatpoint.kyt.common.utils.kytUtil;
import com.floatpoint.kyt.entity.dataobject.PostBrowse;
import com.floatpoint.kyt.entity.params.add.PostBrowseAddPA;
import com.floatpoint.kyt.entity.vo.PostVO;
import com.floatpoint.kyt.mapper.PostBrowseMapper;
import com.floatpoint.kyt.service.PostBrowseService;
import com.floatpoint.kyt.service.PostService;

import java.util.ArrayList;
import java.util.List;

@Accessors(chain = true)
@Service
public class PostBrowseServiceImpl extends ServiceImpl<PostBrowseMapper, PostBrowse> implements PostBrowseService {

    @Autowired
    private PostService postService;

    /**
     * 新增一条帖子的浏览记录
     * @param postBrowseAddPA
     * @return
     */
    @Override
    public boolean add(PostBrowseAddPA postBrowseAddPA) {
        PostBrowse postBrowse = new PostBrowse();
        QueryWrapper<PostBrowse> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",kytUtil.getUserId())
                .eq("post_id",postBrowseAddPA.getPostId());
        List<PostBrowse> postBrowses = baseMapper.selectList(wrapper);
        if(postBrowses.isEmpty()) {
            BeanUtils.copyProperties(postBrowseAddPA, postBrowse);
            postBrowse.setUserId(kytUtil.getUserId())
                    .setPostId(postBrowseAddPA.getPostId())
                    .setIsDelete(0);
        }
        else{
            postBrowse = postBrowses.get(0);
            postBrowse.setIsDelete(0);
        }
        return saveOrUpdate(postBrowse);
    }

    /**
     * 根据当前用户分页查询其浏览记录
     * @return
     */
    @Override
    public List<PostVO> QueryMyBrowse () {
        QueryWrapper<PostBrowse> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",kytUtil.getUserId())
                .orderByDesc("create_date");

        List<PostBrowse> records = new ArrayList<>();
        records = baseMapper.selectList(wrapper);


        List<PostVO> postVOS = new ArrayList<>();
        postVOS.clear();
        for(PostBrowse record: records)
        {
            postVOS.add(postService.queryById(record.getPostId()));
        }
        return postVOS;
    }

    @Override
    public boolean delete(String postId)
    {
        QueryWrapper<PostBrowse> wrapper = new QueryWrapper<>();
        wrapper.eq("post_id",postId)
                .eq("user_id",kytUtil.getUserId());
        return remove(wrapper);
    }

}
