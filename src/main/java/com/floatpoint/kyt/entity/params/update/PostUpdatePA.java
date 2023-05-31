package com.floatpoint.kyt.entity.params.update;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 未使用该部分, 暂定Post不允许修改
 */
@Data
public class PostUpdatePA {

    @NotBlank(message = "标题不能为空")
    @Size(message = "标题不能超过100", max = 100)
    private String title;

    @NotBlank(message = "内容不能为空")
    @Size(message = "内容不能超过1000", max = 1000)
    private String textContent;

    private String imageContent;
}
