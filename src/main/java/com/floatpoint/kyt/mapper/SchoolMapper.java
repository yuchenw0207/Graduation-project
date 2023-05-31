package com.floatpoint.kyt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.floatpoint.kyt.entity.dataobject.School;

public interface SchoolMapper extends BaseMapper<School> {
    boolean get();
}
