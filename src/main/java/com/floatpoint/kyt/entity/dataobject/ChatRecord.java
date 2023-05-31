package com.floatpoint.kyt.entity.dataobject;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Data
@TableName("chatrecord")
public class ChatRecord implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId( value = "record_id",type = IdType.ID_WORKER_STR)
    private String chatRecordId;//记录号

    @TableField("sender_id")
    private String senderId;//发送方

    @TableField("receiver_id")
    private String receiverId;//接收方

    @ApiModelProperty(value = "消息是否被接收")
    @TableField("is_received")
    @JsonValue
    private Integer isReceived;

    @TableField("detail")


    private String detail;//消息详情

    @TableLogic
    @TableField("is_delete")
    private Integer isDelete;//是否被删除

    @TableField(value = "create_date",fill = FieldFill.INSERT)
    private Date createDate;

    @TableField(value = "modified_date",fill = FieldFill.INSERT_UPDATE)
    private Date modifiedDate;

    public ChatRecord(){}
}
