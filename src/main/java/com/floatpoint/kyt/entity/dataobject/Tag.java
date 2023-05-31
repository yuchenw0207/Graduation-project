package com.floatpoint.kyt.entity.dataobject;


import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@Accessors(chain = true)//用于Service层
public class Tag implements Serializable {

    @TableId(value="tag_id",type= IdType.ID_WORKER_STR)
    private String tagId;

    private Integer type;

    private String tagName;//对于文本较大的情况。一般可使用string

    private Integer queryCounter;

    @TableField(fill = FieldFill.INSERT)
    private Date createDate;

    @TableField(value="modified_date",fill = FieldFill.INSERT_UPDATE)
    private Date modifiedDate;

    @TableLogic
    private Integer isDelete;


}
