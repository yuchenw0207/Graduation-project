package com.floatpoint.kyt.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.floatpoint.kyt.entity.dataobject.SchoolTag;
import com.floatpoint.kyt.entity.dataobject.Tag;
import com.floatpoint.kyt.entity.params.add.TagAddPA;
import com.floatpoint.kyt.entity.vo.TagVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import com.floatpoint.kyt.mapper.TagMapper;
import com.floatpoint.kyt.service.TagService;

import java.util.ArrayList;
import java.util.List;


@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {



    /**
     *获取Mapper对象
     */
    @Override
    public boolean add(TagAddPA tagAddPA){
        Tag tag = new Tag();
        BeanUtils.copyProperties(tagAddPA, tag);
        tag.setQueryCounter(0).setIsDelete(0);
        return save(tag);
    }

    /**
     * 列出所有的帖子
     * @return
     */
    @Override
    public  List<TagVO> TagList(){
        List<TagVO> tagVOS= new ArrayList<>();
        QueryWrapper<Tag> wrapper=new QueryWrapper<>();
        List<Tag> tags=baseMapper.selectList(wrapper);

        //封装打卡信息
        TagVO tagVO;
        for (Tag tag: tags) {
            tagVO = new TagVO();
            BeanUtils.copyProperties(tag, tagVO);
            tagVOS.add(tagVO);
        }
        return tagVOS;
    }

    /**
     * 增加帖子查询次数
     */
    @Override
    public boolean change(String id){
        Tag tag=getById(id);
        tag.setQueryCounter(tag.getQueryCounter()+1);
        boolean update = updateById(tag);
        if(!update){
            return false;
        }
        return true;
    };
    //获取学校标签
    @Override
    public TagVO gettag(SchoolTag school_tag) {
        TagVO tagVO = new TagVO();
        QueryWrapper<Tag> wrapper = new QueryWrapper<>();
        wrapper.eq("tag_name",school_tag.getTagName())
                .eq("is_delete",0);
        Tag tag = new Tag() ;
        tag = baseMapper.selectOne(wrapper);
        BeanUtils.copyProperties(tag,tagVO);
        return tagVO;
    }
    /**
     * 根据名字查询标签
     */
    @Override
    public Tag tagNameFindPost(String name){
        QueryWrapper<Tag> wrapper = new QueryWrapper<>();
        wrapper.eq("tag_name",name)
                .eq("type",1);
        Tag tag=new Tag();
        tag=baseMapper.selectOne(wrapper);
        if(tag!=null){
            return tag;
        }
        return null;
    }

    @Override
    public Tag tagNameFindSchool(String name){
        QueryWrapper<Tag> wrapper = new QueryWrapper<>();
        wrapper.eq("tag_name",name)
                .eq("type",0);
        Tag tag=new Tag();
        tag=baseMapper.selectOne(wrapper);
        if(tag!=null){
            return tag;
        }
        return null;
    }



}
