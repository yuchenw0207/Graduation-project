package com.floatpoint.kyt.entity.params.get;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class ChatRecordGetPA {

    //消息发送者id
    @TableField("sender_id")
    private String senderId;



}
