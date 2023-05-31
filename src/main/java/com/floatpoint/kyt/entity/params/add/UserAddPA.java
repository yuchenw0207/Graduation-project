package com.floatpoint.kyt.entity.params.add;

import com.floatpoint.kyt.common.enums.RoleEnum;
import lombok.Data;

/**
 * 新增用户信息PA
 */
@Data
public class UserAddPA {
    //新增id
    private String userId;
    //角色ID
    private RoleEnum roleId;
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
