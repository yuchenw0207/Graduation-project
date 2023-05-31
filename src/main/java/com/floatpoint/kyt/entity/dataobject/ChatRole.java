package com.floatpoint.kyt.entity.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Data
@TableName("chatrole")
public class ChatRole implements Serializable {
    @TableId(value = "consumer_id",type = IdType.ID_WORKER_STR)//顾客
    private String consumerId;
    @TableId(value = "producer_id",type = IdType.ID_WORKER_STR)//提供者，学长学姐
    private String producerId;

    @TableField("day_number")//服务天数
    private int dayNumber;

    @TableField("is_delete")
    private Integer isDelete1;

    @TableField("create_date")
    @JsonFormat(pattern = "YYYY年M月dd日 HH:mm", timezone = "GMT+8")
    private Date createDate1;//服务开始时间

    @TableField("modified_date")

    private Date modifiedDate1;//服务到期时间


}
