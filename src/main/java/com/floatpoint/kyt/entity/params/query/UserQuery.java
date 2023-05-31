package com.floatpoint.kyt.entity.params.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value="User查询对象",description = "user查询对象封装")
@Data
public class UserQuery {
    @ApiModelProperty(value = "用户名称,模糊查询")
    private String userName1;
}
