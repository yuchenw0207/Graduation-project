package com.floatpoint.kyt.common.utils;

import com.floatpoint.kyt.common.result.Result;
import com.floatpoint.kyt.common.result.ResultCode;

/**
 * 检查service方法boolean类型返回值
 * 判断controller层操作是否成功
 *
 * @author yiming
 * @date 2021年05月07日 13:48
 */
public class ControllerSuccessUtil {

    public static Result result(boolean b) {
        if (!b) {
            return Result.fail(ResultCode.ACTION_FAIL);
            // ACTION_FAIL(3000,"操作失败，请稍后重试"), 自定义操作失败，将msg以弹窗的形式呈现
        }
        return Result.success();
    }

}
