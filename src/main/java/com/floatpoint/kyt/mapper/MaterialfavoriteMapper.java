package com.floatpoint.kyt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.floatpoint.kyt.entity.dataobject.Materialfavorite;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


import java.util.List;

@Mapper
public interface MaterialfavoriteMapper extends BaseMapper<Materialfavorite>{
    public boolean favoritecount(@Param("materialId") String materialId);
    public boolean decfavoritecount(@Param("materialId") String materialId);

    public List<Materialfavorite> jude(@Param("userId")String userId , @Param("materialId")String materialId);
    public boolean deletefav(@Param("userId")String userId , @Param("materialId")String materialId);
}
