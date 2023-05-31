package com.floatpoint.kyt.entity.dataobject;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@Accessors(chain = true)
public class Materialfavorite implements Serializable{
    @TableId(value = "fav_id",type= IdType.ID_WORKER_STR)
    private String favId;
    private String userId;
    private String materialId;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date modifiedDate;
    @TableLogic
    private Integer isDelete;
}
