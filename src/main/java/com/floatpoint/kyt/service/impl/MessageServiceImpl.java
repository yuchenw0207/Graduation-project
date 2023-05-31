package com.floatpoint.kyt.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.floatpoint.kyt.entity.dataobject.Message;
import com.floatpoint.kyt.entity.params.add.MessageAddPA;
import com.floatpoint.kyt.entity.vo.MessageVO;
import com.floatpoint.kyt.mapper.MessageMapper;
import com.floatpoint.kyt.service.MessageService;
import com.floatpoint.kyt.common.utils.kytUtil;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MessageService {
    @Override
    public boolean add(MessageAddPA messageAddPA) {
        Message message=new Message();//放入数据
        BeanUtils.copyProperties(messageAddPA,message);//左侧数据传到右侧
        message.setIsConfirm(0).setIsDelete(0);//0未查看，1已查看//0表示为删除，1表示已删除
        return save(message);//插入新消息
    }
    @Override
    @Transactional(rollbackFor = {
            NullPointerException.class,
            RuntimeException.class,
            Exception.class})
    public boolean removeFromMessage(String id){
        Message message=getById(id);
        message.setIsConfirm(1);//表示已查看
        boolean update = updateById(message);
        if(!update){
            return false;
        }
        return true;
    }//移出未查看
    @Override
    public List<MessageVO> messageList() {
        List<MessageVO> messageVOS = new ArrayList<>();
        QueryWrapper<Message> wrapper = new QueryWrapper<>();
        String id=kytUtil.getUserId();
        wrapper.eq("receiver_id", id);
        List<Message> messages = baseMapper.selectList(wrapper);

        //封装消息通知
        int type;
        MessageVO messageVO;
        for (Message message : messages) {
            messageVO = new MessageVO();
            BeanUtils.copyProperties(message, messageVO);
            messageVOS.add(messageVO);
        }
        return messageVOS;
    }//根据用户Id列出其所有消息
}
