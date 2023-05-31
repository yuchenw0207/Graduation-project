package com.floatpoint.kyt.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.floatpoint.kyt.entity.dataobject.ChatRecord;
import com.floatpoint.kyt.entity.params.add.ChatRecordAddPA;
import com.floatpoint.kyt.entity.params.get.ChatRecordGetPA;
import com.floatpoint.kyt.entity.params.update.ChatRecordUpdatePA;
import com.floatpoint.kyt.entity.vo.ChatRecordVO;

import java.util.List;

public interface ChatRecordService extends IService<ChatRecord> {
    boolean add(ChatRecordAddPA chat_recordAddPA);
    List<ChatRecordVO> get(ChatRecordGetPA chat_recordGetPA);
    boolean update(ChatRecordUpdatePA chat_recordUpdatePA);
  }
