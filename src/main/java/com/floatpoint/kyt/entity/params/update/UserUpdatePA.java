package com.floatpoint.kyt.entity.params.update;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author yiming
 * @date 2021年05月11日 10:13
 */
@Data
public class UserUpdatePA {



    /**
     * 修改昵称
     */
    @NotBlank(message = "昵称不能为空")
    @Size(max = 50, message = "昵称长度不可超过50")
    private String userName1;

    /**
     * 修改密码
     */
    @NotBlank(message = "密码不能为空")
    @Size(min = 6, message = "密码长度不可小于6")
    private  String password;

    /**
     * 修改目标院校
     */
    @NotBlank(message = "目标院校不能为空")
    @Size(max = 50, message = "目标院校名称长度不可超过50")
    private  String targetSchool;

    /**
     * 修改目标专业
     */
    @NotBlank(message = "目标专业不能为空")
    @Size(max = 50, message = "院校专业名称长度不可超过50")
    private String targetSpecialty;
}
