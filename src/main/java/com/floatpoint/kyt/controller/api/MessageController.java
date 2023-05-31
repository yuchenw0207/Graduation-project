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
import com.floatpoint.kyt.entity.params.add.MessageAddPA;
import com.floatpoint.kyt.entity.vo.MessageVO;
import com.floatpoint.kyt.service.MessageService;

import java.util.List;

@Api(description = "消息通知相关")
@RestController
@RequestMapping("/api/message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    /**
     *新建消息通知 /api/message/add
     */
    @ApiOperation(value = "新建消息通知")
    @PostMapping("/add")
    public Result addUnCheck(@ApiParam(name = "messageAddPA", value = "待查看消息", required = true)
                                 @RequestBody MessageAddPA messageAddPA){
        boolean add=messageService.add(messageAddPA);
        return ControllerSuccessUtil.result(add);
    };

    /**
     * 删除消息通知 /api/message/delete/{id}
     */
    @ApiOperation(value = "逻辑删除消息通知")
    @DeleteMapping("/delete/{id}")
    public Result deleteChecked(@ApiParam(name = "id", value = "待删除消息id", required = true)
                                    @PathVariable("id") String id){
        if (ParamCheckUtil.isNotRightId(id)) {
            return Result.fail(ResultCode.PARAM_NOT_VALID).message("id格式错误");
        }
        boolean remove=messageService.removeById(id);
        return ControllerSuccessUtil.result(remove);
    }

    /**
     * 移出未查看消息
     */
    @ApiOperation(value = "移出查看信息")
    @PutMapping("/remove/{message_id}")
    public Result removeMessage(@PathVariable("message_id") String id){
//        if (ParamCheckUtil.isNotRightId(id)) {
//            return Result.fail(ResultCode.PARAM_NOT_VALID).message("该消息不存在");
//        }
        boolean removeFromMessage = messageService.removeFromMessage(id);
        return ControllerSuccessUtil.result(removeFromMessage);
    }

    /**
     * 分页查询所有消息
     */
    @ApiOperation(value = "分页查询所有消息")
    @GetMapping("/message-list")
    public Result messageList(){
        List<MessageVO> messageVOS = messageService.messageList();
        return Result.success().data("row", messageVOS);
    }

}
