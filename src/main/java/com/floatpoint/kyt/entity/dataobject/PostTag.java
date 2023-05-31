package com.floatpoint.kyt.entity.dataobject;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "PostTag对象",description = "帖子标签")
@TableName(value = "posttag")
public class PostTag implements Serializable {
    private static long serialVersionUID = 1L;

    @ApiModelProperty(value = "拥有标签的帖子ID")
    @TableField(value = "post_id")
    private String postId;

    @ApiModelProperty(value = "标签")
    @TableField(value = "tag_id")
    private String tagId;

    @ApiModelProperty(value = "逻辑删除 0表示为未删除 1表示删除")
    private Integer isDelete;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "create_date", fill = FieldFill.INSERT)
    private Date createDate;

    @ApiModelProperty(value = "修改时间")
    @TableField(value = "modified_date", fill = FieldFill.INSERT_UPDATE)
    private Date modifiedDate;
}
