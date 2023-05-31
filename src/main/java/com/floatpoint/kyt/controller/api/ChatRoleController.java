package com.floatpoint.kyt.controller.api;

import com.floatpoint.kyt.common.result.Result;
import com.floatpoint.kyt.common.utils.ControllerSuccessUtil;
import com.floatpoint.kyt.entity.params.add.ChatRoleAddPA;
import com.floatpoint.kyt.entity.params.update.ChatRoleUpdatePA;
import com.floatpoint.kyt.entity.vo.ChatRoleVO;
import com.floatpoint.kyt.service.ChatRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(description = "会话")
@RestController
@RequestMapping("/api/chat_role")
public class ChatRoleController {
    @Autowired
    private ChatRoleService chat_roleService;

    @ApiOperation(value = "增加一条会话")
    @GetMapping("/add")
    public Result addMine(@ApiParam(name = "chat_roleAddPA", value = "增加一条会话", required = true)
                          @Valid ChatRoleAddPA chat_roleAddPA) {
        boolean add = chat_roleService.add(chat_roleAddPA);
        return ControllerSuccessUtil.result(add);

    }

    @ApiOperation(value = "查询当前用户会话")
    @GetMapping("/get")
    public Result getMine() {
        List<ChatRoleVO>  chat_roleVOS = chat_roleService.get();
        return Result.success().data("row", chat_roleVOS);
    }

    @ApiOperation(value = "更新会话信息")
    @GetMapping("/update")
    public Result updateMine(@ApiParam(name = "chat_roleUpdatePA", value = "更新会话信息", required = true)
                                 @Valid ChatRoleUpdatePA chat_roleUpdatePA) {
        boolean update = chat_roleService.update1(chat_roleUpdatePA);
        return ControllerSuccessUtil.result(update);
    }

    @ApiOperation(value = "删除会话信息")
    @GetMapping("/delete")
    public Result deleteMine(@ApiParam(name = "chat_roleUpdatePA", value = "删除会话信息", required = true)
                             @Valid ChatRoleUpdatePA chat_roleUpdatePA) {
        boolean update = chat_roleService.delete(chat_roleUpdatePA);
        return ControllerSuccessUtil.result(update);
    }
}
