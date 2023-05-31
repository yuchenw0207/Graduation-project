package com.floatpoint.kyt.controller.api;

import com.floatpoint.kyt.common.result.Result;
import com.floatpoint.kyt.common.utils.ControllerSuccessUtil;
import com.floatpoint.kyt.entity.params.add.ChatRecordAddPA;
import com.floatpoint.kyt.entity.params.get.ChatRecordGetPA;
import com.floatpoint.kyt.entity.params.update.ChatRecordUpdatePA;
import com.floatpoint.kyt.entity.vo.ChatRecordVO;
import com.floatpoint.kyt.service.ChatRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(description = "聊天记录")
@RestController
@RequestMapping("/api/chat_record")
public class ChatRecordController {

    @Autowired
    private ChatRecordService chat_recordService;

    @ApiOperation(value = "发送一条消息（增加一条消息记录）")
    @GetMapping("/add")
    public Result addMine(@ApiParam(name = "chat_recordAddPA", value = "发送一条消息", required = true)
                          @Valid ChatRecordAddPA chat_recordAddPA) {
        boolean add = chat_recordService.add(chat_recordAddPA);
        return ControllerSuccessUtil.result(add);
    }

    @ApiOperation(value = "查询所有消息记录（根据sender和receiver）")
    @GetMapping("/get")
    public Result getMine(@ApiParam(name = "chat_recordGetPA", value = "查询消息", required = true)
                          @Valid ChatRecordGetPA chat_recordGetPA) {
        List<ChatRecordVO> chat_recordVOS = chat_recordService.get(chat_recordGetPA);
        return Result.success().data("row", chat_recordVOS);
    }

    @ApiOperation(value = "把这两人所有记录设置为已读")
    @GetMapping("/setReceived")
    public Result putMine(@ApiParam(name = "chat_recordUpdatePA", value = "消息已读", required = true)
                          @Valid ChatRecordUpdatePA chat_recordUpdatePA) {
        boolean add = chat_recordService.update(chat_recordUpdatePA);
        return ControllerSuccessUtil.result(add);
    }


}
