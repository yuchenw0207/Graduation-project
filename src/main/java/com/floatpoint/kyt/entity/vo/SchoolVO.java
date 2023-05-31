package com.floatpoint.kyt.entity.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class SchoolVO {

    //查询的学校名称
    @TableField("school_name")
    private String schoolName;

    //学校的详细信息
    @TableField("detail_information")
    private String detailInformation;

}
