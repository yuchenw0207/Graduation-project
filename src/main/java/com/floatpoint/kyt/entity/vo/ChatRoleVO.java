package com.floatpoint.kyt.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class ChatRoleVO {
    private String producerId;//该会话对应的学长学姐Id

    private String consumerId;//

    private String name;

    private int dayNumber;//会话有效天数

    private Integer isDelete1;//用来判断是否到期

    private Date createDate1;//服务开始日期

    @JsonFormat(pattern = "YYYY年M月d日")
    private Date modifiedDate1;//服务到期时间

    private Integer isReceived;//最后一条消息是否已读

    private String speaker;//判断最后一条消息是谁发送的

    private String imgsrc;

    private Integer role;//获取聊天列表时用来判断获取者身份（学长学姐或者是普通用户）

    @JsonFormat(pattern = "MM-dd HH:mm", timezone = "GMT+8")
    private Date lastMessageDate;//最后一条消息的时间

    private String detail;//最后一条消息的内容，用来预显示消息


}
