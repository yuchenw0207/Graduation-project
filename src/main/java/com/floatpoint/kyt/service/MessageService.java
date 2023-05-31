package com.floatpoint.kyt.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.floatpoint.kyt.entity.dataobject.Message;
import com.floatpoint.kyt.entity.params.add.MessageAddPA;
import com.floatpoint.kyt.entity.vo.MessageVO;

import java.util.List;

/**
 *
 *用户消息通知 服务类
 *新增未查看消息+查看，管理消息
*/
public interface MessageService extends IService<Message>{
    /**
     * 新增一个消息通知
     */
    boolean add(MessageAddPA messageAddPA);
    /**
     * 移出未查看
     */
    boolean removeFromMessage(String id);
    /**
     *根据用户的Id查询其所有的消息
     */
    List<MessageVO> messageList();
    /**
     * 刷新消息情况
     */
    //boolean refreshUnCheck();
}
