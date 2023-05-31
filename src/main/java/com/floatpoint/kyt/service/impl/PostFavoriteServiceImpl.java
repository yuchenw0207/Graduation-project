package com.floatpoint.kyt.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.floatpoint.kyt.entity.dataobject.Post;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.floatpoint.kyt.common.utils.kytUtil;
import com.floatpoint.kyt.entity.dataobject.PostFavorite;
import com.floatpoint.kyt.entity.vo.PostVO;
import com.floatpoint.kyt.mapper.PostFavoriteMapper;
import com.floatpoint.kyt.service.PostFavoriteService;
import com.floatpoint.kyt.service.PostService;

import java.util.ArrayList;
import java.util.List;

@Accessors(chain = true)
@Service
public class PostFavoriteServiceImpl extends ServiceImpl<PostFavoriteMapper, PostFavorite> implements PostFavoriteService {

    @Autowired
    private PostService postService;



    /**
     * 添加一条新帖子收藏记录
     * @param postId
     * @return
     */
    @Override
    public boolean add(String postId)
    {
        PostFavorite postFavorite = new PostFavorite();
        postFavorite.setPostId(postId)
                .setIsDelete(0)
                .setUserId(kytUtil.getUserId());
        Post post= postService.getById(postId);
        post.setFavoriteCount(post.getFavoriteCount()+1);
        postService.saveOrUpdate(post);
        return save(postFavorite);
    }


    /**
     * 按页根据当前用户查找其收藏列表
     * @param current
     * @param limit
     * @return
     */
    @Override
    public List<PostVO> pageQueryByUser(long current, long limit){
        Page<PostFavorite> pagePostFavorite = new Page<>(current, limit);
        QueryWrapper<PostFavorite> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",kytUtil.getUserId());
        this.page(pagePostFavorite,wrapper);
        List<PostFavorite> records = pagePostFavorite.getRecords();

        List<PostVO> postVOS=new ArrayList<>();
        for(PostFavorite record : records)
        {
            postVOS.add(postService.queryById(record.getPostId()));
        }
        return postVOS;
    }

    @Override
    public boolean isFavorite(String postId){
        QueryWrapper<PostFavorite> wrapper = new QueryWrapper<>();
        wrapper.eq("post_id",postId)
                .eq("user_id",kytUtil.getUserId());
        List<PostFavorite> postFavorites = baseMapper.selectList(wrapper);
        return !postFavorites.isEmpty();
    }





    /**！！！不能使用deleteByMultiId
     * 删除收藏记录
     * @param postId
     * @return
     */
    @Override
    public boolean delete (String postId)
    {
        QueryWrapper<PostFavorite> wrapper = new QueryWrapper<>();
        wrapper.eq("post_id",postId)
                .eq("user_id",kytUtil.getUserId());
        Post post = new Post();
        BeanUtils.copyProperties(postService.getById(postId), post);
        post.setFavoriteCount(post.getFavoriteCount()-1);
        postService.saveOrUpdate(post);
        return  remove(wrapper);
    }
}
