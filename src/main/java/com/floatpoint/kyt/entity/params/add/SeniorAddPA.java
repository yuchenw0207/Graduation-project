package com.floatpoint.kyt.entity.params.add;

import lombok.Data;

@Data
public class SeniorAddPA {
    //学长学姐姓名
    String seniorName;
    //在读院校
    String currentSchool;
    //在读专业
    String currentSpeciality;
    //考研时间
    Integer kyTime;
    //考研分数
    Integer kyMark;
    //毕业院校
    String undergraduateSchool;
    //个人简介
    String profile;

}
