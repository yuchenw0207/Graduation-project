package com.floatpoint.kyt.controller.api;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.floatpoint.kyt.common.result.Result;
import com.floatpoint.kyt.common.result.ResultCode;
import com.floatpoint.kyt.common.utils.ControllerSuccessUtil;
import com.floatpoint.kyt.common.utils.ParamCheckUtil;
import com.floatpoint.kyt.common.utils.kytUtil;
import com.floatpoint.kyt.entity.params.add.UserAddPA;
import com.floatpoint.kyt.entity.params.query.UserQuery;
import com.floatpoint.kyt.entity.params.update.UserUpdatePA;
import com.floatpoint.kyt.entity.vo.UserMessageVO;
import com.floatpoint.kyt.entity.vo.UserVO;
import com.floatpoint.kyt.service.UserService;

import javax.validation.Valid;
import java.util.List;

/**
 * 前端控制器
 *
 * @author cbj
 * @author yiming
 * @since 2021-04-26
 */
@Api(description = "用户相关")
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 新建用户 /api/user/add
     */
    @ApiOperation(value = "新建用户")
    @PostMapping("/add")
    public Result addNewUser(@ApiParam(name = "userNewAddPA", value = "待增加用户", required = true)
                                 @Valid @RequestBody UserAddPA userAddPA) {
        boolean add =userService.add(userAddPA);
        return ControllerSuccessUtil.result(add);
    }
    /**
     * 获取用户信息
     */
    @ApiOperation(value = "获取目标用户信息")
    @GetMapping("/get-userMessage")
    public Result getUserMessage(){
        String userId= kytUtil.getUserId();
        List<UserMessageVO> userMessageVOS = userService.findByUserId(userId);
        return Result.success().data("row",userMessageVOS);
    }
    /**
     * 根据用户名查询用户 /api/
     */
    @ApiOperation(value = "查找目标用户")
    @GetMapping("/find-user/{current}/{limit}/{userQuery}")
    public Result PageFindUser(@ApiParam(name = "current", value = "当前页码", required = true)
                                   @PathVariable("current") long current,
                               @ApiParam(name = "limit", value = "每页记录数", required = true)
                                   @PathVariable("limit") long limit,
                               @ApiParam(name ="userQuery",value = "封装信息",required = true)
                                   UserQuery userQuery){
        List<UserVO> userVOS = userService.PageFindUser(current,limit,userQuery);
        return Result.success().data("row", userVOS);
    }
    /**
     * 修改用户个人资料
     */
    @ApiOperation(value = "修改个人资料")
    @GetMapping("/update")
    public Result updateSelfMess(@ApiParam(name = "userUpdatePA", value = "待更新信息", required = true)
                                     @Valid  UserUpdatePA userUpdatePA){
        if(!ParamCheckUtil.isRight(kytUtil.getUserId())){
            return Result.fail(ResultCode.PARAM_NOT_VALID).message("当前用户出现未知错误，暂时无法修改");
        }
        boolean update = userService.update(userUpdatePA);
        return ControllerSuccessUtil.result(update);
    }
}

