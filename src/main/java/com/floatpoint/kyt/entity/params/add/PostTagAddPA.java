package com.floatpoint.kyt.entity.params.add;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class PostTagAddPA {
    @NotBlank(message = "帖子id不能为空")
    private String postId;
    @NotBlank(message = "标签名不能为空")
    private String tagName;
    @NotBlank(message = "标签不能为空")
    private String tagId;
}
