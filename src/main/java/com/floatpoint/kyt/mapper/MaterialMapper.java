package com.floatpoint.kyt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.floatpoint.kyt.entity.dataobject.Material;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MaterialMapper extends BaseMapper<Material>{
    public List<Material> recommend();
    public List<Material> searchbro(@Param("userId") String userId);
    public List<Material> searchfav(@Param("userId") String userId);
    public boolean deletede(@Param("posterId") String posterId,@Param("materialId") String materialId);
    public List<Material> search(@Param("tag") String tag);
    public List<Material> detail(@Param("materialId") String materialId);
    public List<Material> selectlast();


}
