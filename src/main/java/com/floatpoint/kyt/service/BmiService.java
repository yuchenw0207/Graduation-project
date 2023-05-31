package com.floatpoint.kyt.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.floatpoint.kyt.entity.dataobject.Bmi;
import com.floatpoint.kyt.entity.params.add.BmiAddPA;

import java.util.List;

public interface BmiService extends IService<Bmi>{
    boolean addwh(BmiAddPA bmiAddPA);
    boolean updatawh(String userId,String height,String weight,String gender,String age);
    List<Bmi> searchjudge(String userId);
    List<Bmi> searchbmi(String userId);
}
