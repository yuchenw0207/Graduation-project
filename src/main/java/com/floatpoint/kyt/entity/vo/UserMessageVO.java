package com.floatpoint.kyt.entity.vo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserMessageVO {
    //用户名
    private String userName1;
    //学号
    private String studentId;
    //学生姓名
    private String studentName;
    //登陆密码
    private String password;
    //专业
    private String specialty;
    //目标院校
    private String targetSchool;
    //目标专业
    private String targetSpecialty;
}
