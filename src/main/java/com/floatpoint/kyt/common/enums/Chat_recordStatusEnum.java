package com.floatpoint.kyt.common.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum Chat_recordStatusEnum implements IEnum<Integer> {

    UNREAD(0, "信息未读"),

    ISREAD(1, "信息已读"),;

    private int type;

    private String name;


    Chat_recordStatusEnum(int type, String name) {
        this.type = type;
        this.name = name;
    }


    @Override
    public Integer getValue() {
        return this.type;
    }
    public static Chat_recordStatusEnum getByType(int type) {
        return Arrays.stream(values()).filter(e -> e.getType() == type).findFirst().orElse(null);
    }

    public static Chat_recordStatusEnum getByName(String name) {
        return Arrays.stream(values()).filter(e -> e.getName().equals(name)).findFirst().orElse(null);
    }
}
