package com.floatpoint.kyt.entity.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class ChatRecordVO {

    @TableField("record_id")
    private String chatRecordId;


    @TableField("receiver_id")
    private String receiverId;

    @TableField("sender_id")
    private String senderId;

    @TableField("is_received")
    private Integer isReceived;

    private String detail;

    private String speaker;

    @TableField("create_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createDate;

}
