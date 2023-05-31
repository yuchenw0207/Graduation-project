package com.floatpoint.kyt.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class MessageVO {

    //消息的id
    private  String messageId;
    //消息的类型
    private Integer type;
    //消息的内容
    private String content;
    //消息是否收到
    private Integer isConfirm;
    //
    private String msgsrc;
    //消息发送的时间
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createDate;

}
