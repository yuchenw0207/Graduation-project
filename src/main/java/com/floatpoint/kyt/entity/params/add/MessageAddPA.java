package com.floatpoint.kyt.entity.params.add;


import lombok.Data;

import java.util.Date;

@Data
public class MessageAddPA {

    private String content;//单纯的文本无法实现多少人给他点赞的情况，消息的内容包括？

    private String receiverId;

    private Integer type;//定义消息的类型
}
