package com.floatpoint.kyt.entity.params.add;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
public class ChatRecordAddPA implements Serializable {

    /**
     * 接收方（）
     */
    @TableField("receiver_id")
    @NotBlank( message = "发送把内容不为空")
    private String receiverId;

    /**
     * 记录详情
     */
    @NotBlank( message = "发送把内容不为空")
    @Size(max = 500, message = "单次发送不能超过500")
    private String detail;

}
