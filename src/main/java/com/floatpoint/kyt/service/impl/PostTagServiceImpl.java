package com.floatpoint.kyt.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.floatpoint.kyt.entity.dataobject.Tag;
import com.floatpoint.kyt.entity.params.add.TagAddPA;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.floatpoint.kyt.entity.dataobject.PostTag;
import com.floatpoint.kyt.entity.params.add.PostTagAddPA;
import com.floatpoint.kyt.entity.vo.PostTagVO;
import com.floatpoint.kyt.entity.vo.PostVO;
import com.floatpoint.kyt.mapper.PostTagMapper;
import com.floatpoint.kyt.service.PostService;
import com.floatpoint.kyt.service.PostTagService;
import com.floatpoint.kyt.service.TagService;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostTagServiceImpl extends ServiceImpl<PostTagMapper, PostTag> implements PostTagService {

    @Autowired
    private PostService postService;

    @Autowired
    private TagService tagService;

    /**
     * 给一个帖子添加一个标签
     * @param postTagAddPA
     * @return
     */
    public boolean add (PostTagAddPA postTagAddPA)
    {
        if(postTagAddPA.getTagId().equals("NONE") ){
            Tag tag = new Tag();
            tag = tagService.tagNameFindPost(postTagAddPA.getTagName());
            if(tag == null) {
                TagAddPA tagAddPA = new TagAddPA();
                tagAddPA.setTagName(postTagAddPA.getTagName());
                tagAddPA.setType(1);
                tagService.add(tagAddPA);
                tag = tagService.tagNameFindPost(postTagAddPA.getTagName());
            }
            postTagAddPA.setTagId(tag.getTagId());
        }
        System.out.println("*************");
        System.out.println(postTagAddPA.getPostId());
        PostTag postTag = new PostTag();
        QueryWrapper<PostTag> wrapper = new QueryWrapper<>();
        wrapper.eq("post_id",postTagAddPA.getPostId())
                .eq("tag_id",postTagAddPA.getTagId());
        List<PostTag> postTags = baseMapper.selectList(wrapper);
        if(postTags.isEmpty()) {
            BeanUtils.copyProperties(postTagAddPA, postTag);
            System.out.println("*************");
            System.out.println(postTag.getPostId());
            postTag.setIsDelete(0);
            return save(postTag);
        }
        else
            return false;
    }

    /**
     * 根据标签查找拥有该标签的帖子
     * @param tagId
     * @return
     */
    public List<PostVO> queryByTag(String tagId) {
        QueryWrapper<PostTag> wrapper = new QueryWrapper<>();
        wrapper.eq("tag_id",tagId)
                .orderByDesc("create_date");
        List<PostTag> postTags = baseMapper.selectList(wrapper);
        List<PostVO> postVOS = new ArrayList<>();
        tagService.change(tagId);
        for(PostTag record : postTags)
        {
            if(postService.queryById(record.getPostId()).getPostId()!=null)
                postVOS.add(postService.queryById(record.getPostId()));
        }
        return postVOS;
    }


    /**
     * 查询某个帖子拥有的所有标签
     * @param postId
     * @return
     */
    public List<PostTagVO> queryByPost(String postId) {
        QueryWrapper<PostTag> wrapper = new QueryWrapper<>();
        wrapper.eq("post_id",postId)
                .orderByDesc("create_date");
        List<PostTag> postTags = baseMapper.selectList(wrapper);
        List<PostTagVO> postTagVOS = new ArrayList<>();
        for(PostTag record : postTags) {
            Tag tag= new Tag();
            PostTagVO postTagVO = new PostTagVO();
            tag = tagService.getById(record.getTagId());
            BeanUtils.copyProperties(tag, postTagVO);
            postTagVOS.add(postTagVO);
        }
        return postTagVOS;
    }


    /**
     * 移除某个帖子拥有的tag
     * @param tagId
     * @param postId
     * @return
     */
    public boolean delete(String tagId, String postId){
        QueryWrapper<PostTag> wrapper= new QueryWrapper<>();
        wrapper.eq("tag_id",tagId)
                .eq("post_id",postId);
        return remove(wrapper);
    }

}