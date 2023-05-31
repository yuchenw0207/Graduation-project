package com.floatpoint.kyt.entity.vo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserVO {
    // 用户名
    private String userName1;

    // 姓名
    private String studentName;

    // 专业
    private String specialty;
}
