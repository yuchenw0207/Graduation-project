package com.floatpoint.kyt.entity.params.get;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SchoolGetPA {

    @NotBlank(message = "输入的学校名称不能为空")
    @TableField("school_name")
    private String schoolName;

}
