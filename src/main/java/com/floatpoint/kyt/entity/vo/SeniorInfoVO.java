package com.floatpoint.kyt.entity.vo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class SeniorInfoVO {

    //用户id
    private String userId;

    //用户名称
    private String seniorName;

    //在读院校
    private String currentSchool;

    //在读专业
    private String currentSpeciality;

    //本科院校
    private String undergraduateSchool;

    //考研时间
    private Integer kyTime;

    //初试分数
    private int kyMark;

    //个人简介
    private String profile;

    //平均星级
    private float positiveRating;

    //评价人数
    private int evaluateCount;

}
