package com.floatpoint.kyt.entity.params.add;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class TagAddPA {
    /**
     * tag标题
     */
    @NotBlank(message = "标签的标题不能为空")
    @Size(max = 50, message = "标题长度不能超过50")
    private String tagName;

    /**
     * 标签的类型
     */
    private Integer type;
}
