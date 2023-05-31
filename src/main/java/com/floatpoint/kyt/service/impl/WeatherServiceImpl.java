package com.floatpoint.kyt.service.impl;
import com.floatpoint.kyt.entity.dataobject.Weather;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.floatpoint.kyt.entity.params.add.WeatherAddPA;
import com.floatpoint.kyt.mapper.MaterialMapper;
import com.floatpoint.kyt.mapper.WeatherMapper;
import com.floatpoint.kyt.service.WeatherService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class WeatherServiceImpl extends ServiceImpl<WeatherMapper, Weather>implements WeatherService {
    @Autowired
    MaterialMapper materialMapper;
    @Override
    public boolean addweather(WeatherAddPA weatherAddPA)
    {
        Weather weather=new Weather();
        BeanUtils.copyProperties(weatherAddPA,weather);
       return save(weather);
    }
}
