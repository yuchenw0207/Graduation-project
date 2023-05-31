package com.floatpoint.kyt.entity.params.add;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class PunchAddPA {
    /**
     * 打卡的内容
     */
    @Size(max = 255, message = "详情长度不能超过255")
    private String detail;

}
