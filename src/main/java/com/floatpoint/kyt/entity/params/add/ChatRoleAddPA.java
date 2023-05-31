package com.floatpoint.kyt.entity.params.add;

import lombok.Data;

import java.io.Serializable;

@Data
public class ChatRoleAddPA implements Serializable {//增加一条学长学姐和顾客之间的会话

    private String producerId;//学长学姐Id

    private int dayNumber;//购买的天数
}
