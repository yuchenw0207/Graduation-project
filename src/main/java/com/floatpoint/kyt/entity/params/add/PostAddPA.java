package com.floatpoint.kyt.entity.params.add;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class PostAddPA {

    @NotBlank(message = "标题不能为空")
    @Size(max = 50, message = "标题长度不能超过50")
    private String title;

    @NotBlank(message = "内容不能为空")
    @Size(max = 600, message = "帖子内容长度不能超过300")
    private String textContent;

    //图片的url, 帖子最多拥有九个图片
    private String imageContent1="";

    private String imageContent2="";

    private String imageContent3="";

    private String imageContent4="";

    private String imageContent5="";

    private String imageContent6="";

    private String imageContent7="";

    private String imageContent8="";

    private String imageContent9="";
}
