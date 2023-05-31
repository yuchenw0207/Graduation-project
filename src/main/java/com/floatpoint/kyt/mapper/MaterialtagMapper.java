package com.floatpoint.kyt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.floatpoint.kyt.entity.dataobject.Materialtag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MaterialtagMapper extends BaseMapper<Materialtag>{
    public List<Materialtag> history(@Param("userId") String userId);
    public boolean deletetag(@Param("materialId")String materialId,@Param("tagName")String tagName);
    public boolean deletemt(@Param("materialId")String materialId);
}
