package com.floatpoint.kyt.entity.dataobject;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.floatpoint.kyt.common.enums.RoleEnum;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;


@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="user对象", description="用户")
public class User implements Serializable  ,UserDetails{

    private static final long serialVersionUID = 1L;
    @TableId(value = "user_id", type = IdType.ID_WORKER_STR)
    private String userId;
    @ApiModelProperty(value = "角色")
    @TableField(value = "role_id")
    private RoleEnum roleId;//用于区分用户权限
    @TableField(value="user_name")
    private String userName1;

    @TableField(value = "student_id")
    private String studentId;

    @TableField(value = "student_name")
    private String studentName;

    private String password;

    private String specialty;

    private String targetSchool;

    private String targetSpecialty;



    @TableField(fill = FieldFill.INSERT)
    private Date createDate;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date modifiedDate;

    @TableLogic
    private Integer isDelete;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<GrantedAuthority>() {{
            add(new SimpleGrantedAuthority(getRoleId().getName()));
        }};
    }

    @Override
    public String getUsername() {
        return null;
    }


    /**
     * 是否启用
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

}
