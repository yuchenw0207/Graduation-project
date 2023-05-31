package com.floatpoint.kyt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.floatpoint.kyt.entity.dataobject.Material;
import com.floatpoint.kyt.entity.dataobject.Materialbrowse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


import java.util.List;

@Mapper
public interface MaterialbrowseMapper extends BaseMapper<Materialbrowse>{

    public boolean deletebro(@Param("userId") String userId,@Param("materialId") String materialId);
    public boolean deleteall(@Param("userId") String userId);
}
