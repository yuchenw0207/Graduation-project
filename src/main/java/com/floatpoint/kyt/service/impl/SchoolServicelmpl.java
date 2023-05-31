package com.floatpoint.kyt.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.floatpoint.kyt.entity.dataobject.School;
import com.floatpoint.kyt.entity.dataobject.SchoolTag;
import com.floatpoint.kyt.entity.params.get.SchoolGetPA;
import com.floatpoint.kyt.entity.vo.SchoolVO;
import com.floatpoint.kyt.mapper.SchoolMapper;
import com.floatpoint.kyt.service.SchoolService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class SchoolServicelmpl extends ServiceImpl<SchoolMapper, School> implements SchoolService {


    @Override
    public List<SchoolVO> get(SchoolGetPA schoolGetPA) {
        List<SchoolVO> schoolVOS = new ArrayList<>();
        QueryWrapper<School> wrapper = new QueryWrapper<>();
        wrapper.like("school_name", schoolGetPA.getSchoolName())
                .eq("is_delete",0);
        List<School> schools = baseMapper.selectList(wrapper);

        for (School school : schools) {
            SchoolVO schoolVO = new SchoolVO();
            BeanUtils.copyProperties(school, schoolVO);
            schoolVOS.add(schoolVO);
        }
        return schoolVOS;
    }

    @Override
    public SchoolVO getschool(SchoolTag school_tag) {
        SchoolVO schoolVO = new SchoolVO();
        QueryWrapper<School> wrapper = new QueryWrapper<>();
        wrapper.eq("school_name",school_tag.getSchoolName())
                .eq("is_delete",0);
        School school = new School();
        school = baseMapper.selectOne(wrapper);
        BeanUtils.copyProperties(school,schoolVO);
        return schoolVO;
    }




}
