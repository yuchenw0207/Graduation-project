package com.floatpoint.kyt.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.floatpoint.kyt.entity.dataobject.Weather;
import com.floatpoint.kyt.entity.params.add.MaterialAddPA;
import com.floatpoint.kyt.entity.params.add.WeatherAddPA;

public interface WeatherService extends IService<Weather>{
    boolean addweather(WeatherAddPA weatherAddPA);
}
