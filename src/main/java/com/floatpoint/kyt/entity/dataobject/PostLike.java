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

import java.util.Date;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "PostLike对象",description = "帖子和点赞用户的映射")
@TableName(value = "postlike")
public class PostLike {
    @ApiModelProperty(value = "点赞用户")
    @TableField("user_id")
    private String userId;

    @ApiModelProperty(value = "被点赞帖子")
    @TableField("post_id")
    private String postId;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "create_date", fill = FieldFill.INSERT)
    private Date createDate;

    @ApiModelProperty(value = "逻辑删除")
    @TableField(value = "is_delete")
    private Integer isDelete;
}
