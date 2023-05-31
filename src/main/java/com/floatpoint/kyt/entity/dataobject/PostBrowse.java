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
@ApiModel(value = "PostBrowse对象",description = "帖子浏览")
@TableName(value = "postbrowse")
public class PostBrowse implements Serializable {
    private static long serialVersionUID=1L;

    @ApiModelProperty(value = "浏览记录ID")
    @TableId(value = "browse_id", type = IdType.ID_WORKER_STR)
    private String browseId;

    @ApiModelProperty("浏览者ID")
    @TableField(value = "user_id")
    private String userId;

    @ApiModelProperty("帖子ID")
    @TableField(value = "post_id")
    private String postId;

    @ApiModelProperty(value = "逻辑删除 0表示为未删除 1表示删除")
    @TableLogic
    private Integer isDelete;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "create_date", fill = FieldFill.INSERT_UPDATE,update = "now()")
    private Date createDate;
}
