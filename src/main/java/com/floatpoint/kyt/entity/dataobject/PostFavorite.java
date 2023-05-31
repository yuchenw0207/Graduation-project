package com.floatpoint.kyt.entity.dataobject;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.jeffreyning.mybatisplus.anno.MppMultiId;
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
@ApiModel(value = "PostFavorite对象",description = "帖子收藏")
@TableName(value = "postfavorite")
public class PostFavorite implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户ID")
    @TableField(value="user_id")
    private String userId;

    @ApiModelProperty(value = "帖子id")
    @TableField(value="post_id")
    private String postId;

    @ApiModelProperty(value = "逻辑删除 0表示为未删除 1表示删除")
    @TableField(value = "is_delete")
    private Integer isDelete;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "create_date", fill = FieldFill.INSERT)
    private Date createDate;
}
