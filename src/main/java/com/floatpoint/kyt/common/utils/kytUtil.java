package com.floatpoint.kyt.common.utils;

import com.floatpoint.kyt.common.enums.RoleEnum;
import com.floatpoint.kyt.entity.dataobject.User;
import org.springframework.security.core.context.SecurityContextHolder;

public class kytUtil {

    public static String id = "123456";

    public static String getUserId(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getUserId();
    }

    public static boolean isCourier(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getRoleId().equals(RoleEnum.SENIOR);
    }
}
