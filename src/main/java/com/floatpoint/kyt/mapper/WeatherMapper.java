package com.floatpoint.kyt.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.floatpoint.kyt.entity.dataobject.Weather;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface WeatherMapper extends BaseMapper<Weather> {
}
