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
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "Post对象",description = "帖子")
@TableName(value = "post")
public class Post implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "帖子ID")
    @TableId(value = "post_id",type = IdType.ID_WORKER_STR)
    private String postId;

    @ApiModelProperty(value = "发帖人ID")
    @TableField("poster_id")
    private String posterId;

    @ApiModelProperty(value = "帖子标题")
    private String title;

    @ApiModelProperty(value = "文本内容")
    @TableField("text_content")
    private String textContent;

    @ApiModelProperty(value = "图片内容")
    @TableField("image_content")
    private String imageContent;

    @ApiModelProperty(value = "点赞数")
    @TableField("like_count")
    private Integer likeCount;

    @ApiModelProperty(value = "收藏数")
    @TableField("favorite_count")
    private Integer favoriteCount;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "create_date", fill = FieldFill.INSERT)
    private Date createDate;

    @ApiModelProperty(value = "修改时间")
    @TableField(value = "modified_date", fill = FieldFill.INSERT_UPDATE,update = "now()")
    private Date modifiedDate;

    @ApiModelProperty(value = "逻辑删除 0表示为未删除 1表示删除")
    @TableLogic
    private Integer isDelete;
}
