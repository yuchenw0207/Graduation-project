package com.floatpoint.kyt.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class PostVO {


    private String postId;

    private String posterName;

    private String posterId;

    private String title;

    private String textContent;

    private String imageContent1="";

    private String imageContent2="";

    private String imageContent3="";

    private String imageContent4="";

    private String imageContent5="";

    private String imageContent6="";

    private String imageContent7="";

    private String imageContent8="";

    private String imageContent9="";

    private Integer likeCount;

    private Integer favoriteCount;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss", timezone = "GMT+8")
    private Date createDate;
}
