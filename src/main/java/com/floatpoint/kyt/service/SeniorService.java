package com.floatpoint.kyt.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.floatpoint.kyt.entity.dataobject.Senior;
import com.floatpoint.kyt.entity.params.add.SeniorAddPA;
import com.floatpoint.kyt.entity.params.query.SeniorSelectPA;
import com.floatpoint.kyt.entity.params.update.SeniorPositiveUpdatePA;
import com.floatpoint.kyt.entity.params.update.SeniorUpdatePA;
import com.floatpoint.kyt.entity.vo.SeniorInfoVO;

import java.util.List;

public interface SeniorService extends IService<Senior> {

    List<SeniorInfoVO> queryMySeniorInfo ();

    List<SeniorInfoVO> getSeniorInfo ();

    List<SeniorInfoVO> getSelectSeniorInfo (SeniorSelectPA seniorSelectPA);

    boolean setMySeniorInfo (SeniorUpdatePA seniorUpdatePA);

    boolean updatePositiveRating (SeniorPositiveUpdatePA seniorPositiveUpdatePA);

    boolean addSenior (SeniorAddPA seniorAddPA);

    List<Senior> getSeniorName (String userid);

    SeniorInfoVO getMySeniorInfo ();
}
