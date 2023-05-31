package com.floatpoint.kyt.entity.dataobject;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@Accessors(chain = true)
public class Weather implements Serializable {
    @TableId(value = "weather_id")
    private Date weatherid;
    private String weatherinf;

}
