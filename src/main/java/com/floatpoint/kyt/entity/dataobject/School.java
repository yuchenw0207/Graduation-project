package com.floatpoint.kyt.entity.dataobject;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@TableName("school")
public class School implements Serializable {
    @TableId(value = "school_id",type = IdType.ID_WORKER_STR)
    private String schoolId;

    @TableField("school_name")
    private String schoolName;

    @TableField("detail_information")
    private String detailInformation;

    @TableField(value = "create_date",fill = FieldFill.INSERT)
    private LocalDateTime createDate;

    @TableField(value = "modified_date",fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime modifiedDate;

    @TableLogic
    @TableField("is_delete")
    private Integer isDelete;

}
