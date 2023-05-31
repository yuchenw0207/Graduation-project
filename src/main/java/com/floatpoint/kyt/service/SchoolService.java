package com.floatpoint.kyt.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.floatpoint.kyt.entity.dataobject.School;
import com.floatpoint.kyt.entity.dataobject.SchoolTag;
import com.floatpoint.kyt.entity.params.get.SchoolGetPA;
import com.floatpoint.kyt.entity.vo.SchoolVO;

import java.util.List;

public interface SchoolService extends IService<School> {
    List<SchoolVO> get(SchoolGetPA schoolGetPA);
    SchoolVO getschool(SchoolTag school_tag);


}
