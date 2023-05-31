package com.floatpoint.kyt.entity.params.add;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class PostBrowseAddPA {
    //被浏览的帖子的id
    @NotBlank(message = "被加入帖子的id不能为空")
    private String postId;
}
