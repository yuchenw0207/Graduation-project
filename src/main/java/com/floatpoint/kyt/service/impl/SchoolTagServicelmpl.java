package com.floatpoint.kyt.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.floatpoint.kyt.entity.dataobject.SchoolTag;
import com.floatpoint.kyt.entity.params.get.SchoolTagGetschoolPA;
import com.floatpoint.kyt.entity.params.get.SchoolTagGettagPA;
import com.floatpoint.kyt.entity.vo.SchoolVO;
import com.floatpoint.kyt.entity.vo.TagVO;
import com.floatpoint.kyt.mapper.SchoolTagMapper;
import com.floatpoint.kyt.service.SchoolService;
import com.floatpoint.kyt.service.SchoolTagService;
import com.floatpoint.kyt.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SchoolTagServicelmpl extends ServiceImpl<SchoolTagMapper, SchoolTag> implements SchoolTagService {

    @Autowired
    private SchoolService schoolService;
    @Autowired
    private TagService tagService;
    @Override
    public List<SchoolVO> getSchool(SchoolTagGetschoolPA schoolTagGetschoolPA) {
        List<SchoolVO> schoolVOS  = new ArrayList<>();//用来返回
        QueryWrapper<SchoolTag> wrapper = new QueryWrapper<>();
        wrapper.like("tag_name", schoolTagGetschoolPA.getTagName())
                .eq("is_delete",0);
        List<SchoolTag> school_tags = baseMapper.selectList(wrapper);
        for(SchoolTag school_tag:school_tags){
            SchoolVO schoolVO = new SchoolVO();
           schoolVO = schoolService.getschool(school_tag);
           schoolVOS.add(schoolVO);
        }
        return schoolVOS;
    }

    @Override
    public List<TagVO> getTag(SchoolTagGettagPA school_tagGettagPA) {
        List<TagVO> tagVOS  = new ArrayList<>();//用来返回
        QueryWrapper<SchoolTag> wrapper = new QueryWrapper<>();
        wrapper.like("school_name", school_tagGettagPA.getSchoolName())
                .eq("is_delete",0);
        List<SchoolTag> school_tags = baseMapper.selectList(wrapper);
        for(SchoolTag school_tag : school_tags){
                TagVO tagVO = new TagVO();
                tagVO = tagService.gettag(school_tag);
                tagVOS.add(tagVO);
        }
        return tagVOS;
    }
}
