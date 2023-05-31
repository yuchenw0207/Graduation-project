package com.floatpoint.kyt.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.floatpoint.kyt.entity.dataobject.SchoolTag;
import com.floatpoint.kyt.entity.params.get.SchoolTagGetschoolPA;
import com.floatpoint.kyt.entity.params.get.SchoolTagGettagPA;
import com.floatpoint.kyt.entity.vo.SchoolVO;
import com.floatpoint.kyt.entity.vo.TagVO;

import java.util.List;

public interface SchoolTagService extends IService<SchoolTag> {
    List<SchoolVO> getSchool(SchoolTagGetschoolPA schoolTagGetschoolPA);
    List<TagVO> getTag(SchoolTagGettagPA school_tagTagPA);
}
