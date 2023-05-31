package com.floatpoint.kyt.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.floatpoint.kyt.entity.dataobject.Bmi;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


import java.util.List;

@Mapper
public interface BmiMapper extends BaseMapper<Bmi> {
    List<Bmi>searchjudge(@Param("userId") String userId);
    List<Bmi>searchbmi(@Param("userId") String userId);
    Boolean updatawh(@Param("userId") String userId,@Param("height") String height,@Param("weight") String weight,@Param("gender") String gender,@Param("age") String age);
}
