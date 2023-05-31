package com.floatpoint.kyt.entity.params.query;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SeniorSelectPA {

    @ApiModelProperty(value = "在读学校")
    private String currentSchool;

    @ApiModelProperty(value = "在读专业")
    private String currentSpeciality;

}
