package com.floatpoint.kyt.entity.params.add;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class PostReplyAddPA {

    @NotBlank(message = "帖子id不能为空")
    private String postId;

    //帖子内容
    @NotBlank(message = "回复内容不能为空")
    @Size(max = 600, message = "帖子内容长度不能超过300")
    private String detail;

}
