package com.floatpoint.kyt.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class PostReplyVO {
    private String replyId;

    private String detail;

    private String userId;

    private String userName;
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss", timezone = "GMT+8")
    private Date createDate;
}
