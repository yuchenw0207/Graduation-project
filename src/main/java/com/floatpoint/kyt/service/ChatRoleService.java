package com.floatpoint.kyt.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.floatpoint.kyt.entity.dataobject.ChatRole;
import com.floatpoint.kyt.entity.params.add.ChatRoleAddPA;
import com.floatpoint.kyt.entity.params.update.ChatRoleUpdatePA;
import com.floatpoint.kyt.entity.vo.ChatRoleVO;

import java.util.List;

public interface ChatRoleService extends IService<ChatRole> {
    boolean add(ChatRoleAddPA chat_roleAddPA);
    List<ChatRoleVO> get();
    List<ChatRoleVO> get1();
    boolean update1(ChatRoleUpdatePA chat_roleUpdatePA);
    boolean delete(ChatRoleUpdatePA chat_roleUpdatePA);
}
