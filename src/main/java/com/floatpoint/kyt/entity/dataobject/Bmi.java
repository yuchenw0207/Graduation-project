package com.floatpoint.kyt.entity.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
@Data
@Accessors(chain = true)
public class Bmi implements Serializable {
    @TableId(value = "user_id")
    private String userId;
    private String weight;
    private String height;
    private String gender;
    private String age;

}
