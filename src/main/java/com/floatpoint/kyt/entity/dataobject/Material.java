package com.floatpoint.kyt.entity.dataobject;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@Accessors(chain = true)

public class Material implements Serializable {
    @TableId(value = "material_id",type= IdType.ID_WORKER_STR)
    private String materialId;
    private String posterId;
    private String title;
    private String textContent;
    private String imageContent;
    @TableField(fill=FieldFill.UPDATE)
    private Integer likeCount;
    @TableField(fill=FieldFill.UPDATE)
    private Integer favoriteCount;
    @TableField(fill = FieldFill.INSERT)
    private Date createDate;
    @TableField(value = "modified_date",fill = FieldFill.INSERT_UPDATE)
    private Date modifiedDate;
    @TableLogic
    private Integer isDelete;



}
