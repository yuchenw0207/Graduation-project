package com.floatpoint.kyt.entity.dataobject;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;


@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Senior学长学姐", description="学长学姐")
public class Senior implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "user_id", type = IdType.ID_WORKER_STR)
    @ApiModelProperty(value = "用户id")
    private String userId;

    @ApiModelProperty(value = "学长学姐名")
    private String seniorName;

    @ApiModelProperty(value = "在读院校")
    private String currentSchool;

    @ApiModelProperty(value = "在读专业")
    private String currentSpeciality;

    @ApiModelProperty(value = "考研时间")
    @TableField(value = "ky_time")
    private Integer kyTime;

    @ApiModelProperty(value = "初试分数")
    private Integer kyMark;

    @ApiModelProperty(value = "本科院校")
    private String undergraduateSchool;

    @ApiModelProperty(value = "个人简介")
    private String profile;

    @ApiModelProperty(value = "好评率")
    private float positiveRating;

    @ApiModelProperty(value = "评价人数")
    private int evaluateCount;

    @TableField(fill = FieldFill.INSERT)
    private Date createDate;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date modifiedDate;

    @TableLogic
    private Integer isDelete;

}
