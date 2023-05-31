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
@ApiModel(value = "PostReply对象",description = "帖子回复")
@TableName(value = "postreply")
public class PostReply implements Serializable {
    private static long SerialVersionUID=1L;

    @ApiModelProperty(value = "帖子回复ID")
    @TableId(value = "reply_id",type = IdType.ID_WORKER_STR)
    private String replyId;

    @ApiModelProperty(value = "主动回复的用户ID")
    @TableField(value = "user_id")
    private String userId;

    @ApiModelProperty(value = "被回复帖子ID")
    @TableField(value = "post_id")
    private String postId;

    @ApiModelProperty(value = "回复内容")
    private String detail;

    @ApiModelProperty(value = "逻辑删除 0表示为未删除 1表示删除")
    @TableLogic
    private Integer isDelete;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "create_date", fill = FieldFill.INSERT)
    private Date createDate;

}
