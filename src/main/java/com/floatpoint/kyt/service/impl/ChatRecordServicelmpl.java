package com.floatpoint.kyt.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.floatpoint.kyt.entity.dataobject.ChatRecord;
import com.floatpoint.kyt.entity.params.add.ChatRecordAddPA;
import com.floatpoint.kyt.entity.params.get.ChatRecordGetPA;
import com.floatpoint.kyt.entity.params.update.ChatRecordUpdatePA;
import com.floatpoint.kyt.entity.vo.ChatRecordVO;
import com.floatpoint.kyt.mapper.ChatRecordMapper;
import com.floatpoint.kyt.service.ChatRecordService;
import com.floatpoint.kyt.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.floatpoint.kyt.common.utils.kytUtil;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class ChatRecordServicelmpl extends ServiceImpl<ChatRecordMapper, ChatRecord> implements ChatRecordService {

     /* 增加一条聊天记录
     * @param chat_recordAddPA
     * @return
     */
    @Override
    public boolean add(ChatRecordAddPA chat_recordAddPA) {
        ChatRecord chatRecord = new ChatRecord();
        BeanUtils.copyProperties(chat_recordAddPA, chatRecord);
        chatRecord.setSenderId(kytUtil.getUserId());
        chatRecord.setIsReceived(0);
        chatRecord.setIsDelete(0);
        return save(chatRecord);
    }

    /**
     * 根据发消息人和接收人查询消息记录
     * @param chat_recordGetPA
     * @return
     */
    @Override
    public List<ChatRecordVO> get(ChatRecordGetPA chat_recordGetPA) {
        List<ChatRecordVO> chat_recordVOS = new ArrayList<>();//需要返回的一个List
        QueryWrapper<ChatRecord> wrapper = new QueryWrapper<>();
        wrapper.eq("receiver_id", kytUtil.getUserId())
                .eq("sender_id", chat_recordGetPA.getSenderId())
                .orderByDesc("create_date")
                .eq("is_delete",0);
        List<ChatRecord> chat_records = baseMapper.selectList(wrapper);

        for(ChatRecord chat_record : chat_records){
            ChatRecordVO chat_recordVO = new ChatRecordVO();
            BeanUtils.copyProperties(chat_record,chat_recordVO);

            chat_recordVO.setSpeaker("server");
            chat_recordVOS.add(chat_recordVO);
        }

        QueryWrapper<ChatRecord> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("receiver_id", chat_recordGetPA.getSenderId())
                .eq("sender_id",kytUtil.getUserId() )
                .orderByDesc("create_date")
                .eq("is_delete",0);
        List<ChatRecord> chat_records1 = baseMapper.selectList(wrapper1);

        for(ChatRecord chat_record : chat_records1){
            ChatRecordVO chat_recordVO = new ChatRecordVO();
            BeanUtils.copyProperties(chat_record,chat_recordVO);
            chat_recordVO.setSpeaker("customer");
            chat_recordVOS.add(chat_recordVO);
        }
        chat_recordVOS.sort(Comparator.comparing(ChatRecordVO::getCreateDate));
        return chat_recordVOS;
    }

    @Override
    public boolean update(ChatRecordUpdatePA chat_recordUpdatePA) {
        List<ChatRecord> chat_recordS = new ArrayList<>();
        QueryWrapper<ChatRecord> wrapper = new QueryWrapper<>();
        wrapper.eq("sender_id",chat_recordUpdatePA.getProducerId())
                .eq("receiver_id",kytUtil.getUserId())
                .eq("is_received",0);
        List<ChatRecord> chat_records = baseMapper.selectList(wrapper);
        for(ChatRecord chat_record : chat_records) {
            chat_record.setIsReceived(1);
            QueryWrapper<ChatRecord> wrapper1 = new QueryWrapper<>();
            wrapper1.eq("record_id",chat_record.getChatRecordId());
            update(chat_record,wrapper1);
        }
       return true;
    }
}
