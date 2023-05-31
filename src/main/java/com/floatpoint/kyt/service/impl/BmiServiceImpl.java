package com.floatpoint.kyt.service.impl;

import com.floatpoint.kyt.common.utils.kytUtil;
import com.floatpoint.kyt.entity.dataobject.Bmi;
import com.floatpoint.kyt.entity.params.add.BmiAddPA;
import com.floatpoint.kyt.mapper.BmiMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.floatpoint.kyt.service.BmiService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BmiServiceImpl extends ServiceImpl<BmiMapper, Bmi>implements BmiService {
    @Autowired
    BmiMapper bmiMapper;
    @Override
    public boolean addwh(BmiAddPA bmiAddPA)
    {
        Bmi bmi=new Bmi();
        BeanUtils.copyProperties(bmiAddPA,bmi);
        bmi.setUserId(kytUtil.getUserId());
        return save(bmi);
    }
    @Override
    public List<Bmi> searchjudge(String userId)
    {
       List<Bmi> searchjudge =bmiMapper.searchjudge(userId);
       return searchjudge;
    }
    @Override
    public List<Bmi> searchbmi(String userId)
    {
        List<Bmi> searchbmi =bmiMapper.searchbmi(userId);
        return searchbmi;
    }

    @Override
    public boolean updatawh(String userId,String height,String weight,String gender,String age){
        boolean updatawh = bmiMapper.updatawh(userId, height, weight, gender,age);
        return updatawh;

    }
}
