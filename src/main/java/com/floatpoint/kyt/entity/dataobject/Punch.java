package com.floatpoint.kyt.entity.dataobject;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@Accessors(chain = true)
public class Punch implements Serializable {
    @TableId(value="punch_id",type= IdType.ID_WORKER_STR)
    private String punchId;

    private String userId;

    private String detail;

    @TableField(fill = FieldFill.INSERT)
    private Date createDate;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date modifiedDate;

    @TableLogic
    private Integer isDelete;
}
