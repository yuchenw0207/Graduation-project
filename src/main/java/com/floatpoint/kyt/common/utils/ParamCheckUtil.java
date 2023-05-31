package com.floatpoint.kyt.common.utils;

/**
 * 用于参数检查
 */
public class ParamCheckUtil {

    public static boolean isRight(String id){
        if(id == null || id.length()>20){
            return true;
        }
        if(id.contains(" ")){
            return true;
        }
        for (int i = id.length();--i>=0;){
            if (!Character.isDigit(id.charAt(i))){
                return true;
            }
        }
        return false;
    }

    public static boolean isNotRightId(String id){
        if(id == null || id.length()!= 19){
            return true;
        }
        if(id.contains(" ")){
            return true;
        }
        for (int i = id.length();--i>=0;){
            if (!Character.isDigit(id.charAt(i))){
                return true;
            }
        }
        return false;
    }

    public static boolean isNotRightOpenId(String openId){
        if(openId == null || openId.length()!= 19){
            return true;
        }
        if(openId.contains(" ")){
            return true;
        }
        for (int i = openId.length();--i>=0;){
            if (!Character.isDigit(openId.charAt(i))){
                return true;
            }
        }
        return false;
    }

    // 检查是不是合理的订单号
    public static boolean isNotRightTel(String id){
        if(id == null || id.length()!= 11){
            return true;
        }
        if(id.contains(" ")){
            return true;
        }
        for (int i = id.length();--i>=0;){
            if (!Character.isDigit(id.charAt(i))){
                return true;
            }
        }
        return false;
    }

    // 检查是不是合理的学生证号
    public static boolean isNotRightStuId(String id){
        if(id == null || id.length()< 10 || id.length() > 20){
            return true;
        }
        if(id.contains(" ")){
            return true;
        }
        for (int i = id.length();--i>=0;){
            if (!Character.isDigit(id.charAt(i))){
                return true;
            }
        }
        return false;
    }
}
