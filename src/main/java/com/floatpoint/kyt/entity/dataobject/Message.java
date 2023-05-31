
package com.floatpoint.kyt.entity.dataobject;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@Accessors(chain = true)
public class Message implements Serializable {

    @TableId(value="message_id",type= IdType.ID_WORKER_STR)
    private String messageId;

    private String receiverId;

    private Integer type;

    private String content;

    private Integer isConfirm;

    @TableField(fill = FieldFill.INSERT)
    private Date createDate;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date modifiedDate;

    @TableLogic
    private Integer isDelete;

}
