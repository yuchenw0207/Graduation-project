package com.floatpoint.kyt.entity.dataobject;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@TableName("schooltag")
public class SchoolTag implements Serializable {
    @TableId(value = "school_id",type = IdType.ID_WORKER_STR)
    private String schoolId;

    @TableId(value = "tag_id",type = IdType.ID_WORKER_STR)
    private String tagId;

    @TableLogic
    @TableField("is_delete")
    private Integer isDelete;

    @TableField(value = "create_date",fill = FieldFill.INSERT)
    private LocalDateTime createDate;

    @TableField(value = "modified_date",fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime modifiedDate;

    @TableField("tag_name")
    private String tagName;

    @TableField("school_name")
    private String schoolName;
}
