package com.floatpoint.kyt.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.floatpoint.kyt.common.enums.RoleEnum;
import com.floatpoint.kyt.entity.dataobject.ChatRole;
import com.floatpoint.kyt.entity.dataobject.Senior;
import com.floatpoint.kyt.entity.dataobject.User;
import com.floatpoint.kyt.entity.params.add.ChatRecordAddPA;
import com.floatpoint.kyt.entity.params.add.ChatRoleAddPA;
import com.floatpoint.kyt.entity.params.get.ChatRecordGetPA;
import com.floatpoint.kyt.entity.params.update.ChatRoleUpdatePA;
import com.floatpoint.kyt.entity.vo.ChatRecordVO;
import com.floatpoint.kyt.entity.vo.ChatRoleVO;
import com.floatpoint.kyt.mapper.ChatRoleMapper;
import com.floatpoint.kyt.service.ChatRecordService;
import com.floatpoint.kyt.service.ChatRoleService;
import com.floatpoint.kyt.service.SeniorService;
import com.floatpoint.kyt.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.floatpoint.kyt.common.utils.kytUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class ChatRoleServicelmpl extends ServiceImpl<ChatRoleMapper, ChatRole> implements ChatRoleService {

    @Autowired
    private ChatRecordService chat_recordService;
    @Autowired
    UserService userService;
    @Autowired
    SeniorService seniorService;
    @Autowired
    ChatRoleService chat_roleService;
    @Override
    public boolean add(ChatRoleAddPA chat_roleAddPA) {//新增会话
        ChatRole chat_role = new ChatRole();
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date time_date = null;
        try {
            time_date = sdf.parse(sdf.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        chat_role.setCreateDate1(time_date);//将当前系统时间设定为服务开始时间

        Calendar calendar = Calendar.getInstance();
        assert time_date != null;
        calendar.setTime(time_date);
        calendar.add(Calendar.DATE,chat_roleAddPA.getDayNumber());//根据购买天数计算出服务到期天数
        chat_role.setModifiedDate1(calendar.getTime());//设置服务到期时间
        chat_role.setProducerId(chat_roleAddPA.getProducerId());//设置服务提供者Id
        chat_role.setConsumerId(kytUtil.getUserId());//设置服务购买者Id
        chat_role.setDayNumber(chat_roleAddPA.getDayNumber());//设置服务购买天数
        chat_role.setIsDelete1(0);//设置服务未到期
        return save(chat_role);
    }

    @Override
    public List<ChatRoleVO> get1() {//获得最新的聊天信息，获得当前用户的会话状态
        List<ChatRoleVO> chat_roleVOS = new ArrayList<>();//需要返回的一个List
        List<User> users1 = userService.queryUserById(kytUtil.getUserId());//首先读取当前用户的信息
        if (users1 != null && users1.size() > 0) {
            if(users1.get(0).getRoleId() ==RoleEnum.SENIOR ){//说明当前登录的用户身份是学长学姐
                QueryWrapper<ChatRole> wrapper = new QueryWrapper<>();
                wrapper.eq("producer_id", kytUtil.getUserId())//所以查询的是当前用户作为消费者的chatRole
                        ;//说明未被删除
                List<ChatRole> chat_roles = baseMapper.selectList(wrapper);
                for (ChatRole chat_role : chat_roles) {//开始封装成VO
                    ChatRoleVO chat_roleVO = new ChatRoleVO();
                    BeanUtils.copyProperties(chat_role, chat_roleVO);
                    chat_roleVO.setRole(1);//设置身份码，表明此次查询是根据当前登录用户作为学长学姐查询谁购买了我的服务
                    //获取当前系统时间
                    Date day=new Date();
                    if(chat_role.getModifiedDate1().compareTo(day)<0){//用来实现自动设置服务是否到期
                        chat_roleVO.setIsDelete1(1);//这是给前端传递服务是否到期的信息
                        ChatRole chatRole = new ChatRole();//设置数据库相应的服务到期的标志
                        BeanUtils.copyProperties(chat_role, chatRole);
                        chatRole.setIsDelete1(1);
                        QueryWrapper<ChatRole> wrapper2 = new QueryWrapper<>();
                        wrapper2.eq("producer_id", kytUtil.getUserId())//我作为的是服务提供者
                                .eq("consumer_id", chatRole.getConsumerId());
                        update(chatRole, wrapper2);//更新数据库中的服务是否到期信息

                    }

                    ChatRecordGetPA chat_recordGetPA = new ChatRecordGetPA();//封装一个 ChatRecordGetPA用来调用查询聊天记录的接口
                    chat_recordGetPA.setSenderId(chat_role.getConsumerId());//我作为服务的提供者，给我发消息的应该是服务的购买者
                    List<ChatRecordVO> chat_recordVOS = chat_recordService.get(chat_recordGetPA);
                    //chat_roleVO.setName(userService.querySeniorById(chat_roleVO.getProducerId()))   ;

                    if (chat_recordVOS != null && chat_recordVOS.size() > 0) {

                        List<User> users = userService.queryUserById(chat_role.getConsumerId());//查询的也是消费者的用户名
                        if (users != null && users.size() > 0) {//这一部分是设置要显示的名字
                            chat_roleVO.setName(users.get(0).getUserName1())  ;//应当显示的是消费者的名字

                        } else {

                        }
                        //chat_roleVO.setName(seniorService.getSeniorName(chat_role.getConsumerId()));
//                        List<Senior> seniors = seniorService.getSeniorName(chat_roleVO.getConsumerId());
//                        if (seniors != null && seniors.size() > 0) {
//                            chat_roleVO.setName(seniors.get(0).getSeniorName())  ;
//
//                        } else {
//
//                        }

                        //chat_roleVO.setName(userService.getById(chat_recordVOS.get(chat_recordVOS.size()-1).getReceiverId()).getUserName1());
                        chat_roleVO.setDetail(chat_recordVOS.get(chat_recordVOS.size()-1).getDetail())//设置最后一条消息内容
                                .setLastMessageDate(chat_recordVOS.get(chat_recordVOS.size()-1).getCreateDate())//设置最后一条消息创建时间
                                .setSpeaker(chat_recordVOS.get(chat_recordVOS.size()-1).getSpeaker())
                                .setIsReceived(chat_recordVOS.get(chat_recordVOS.size()-1).getIsReceived());//设置最后一条消息是否被接受}
                        chat_roleVOS.add(chat_roleVO);
                    } else {

//                        List<User> users = userService.queryUserById(chat_roleVO.getConsumerId());
//                        if (users != null && users.size() > 0) {
//                            chat_roleVO.setName(users.get(0).getUserName1())  ;
//
//                        } else {
//
//                        }
                        List<Senior> seniors = seniorService.getSeniorName(chat_roleVO.getConsumerId());
                        if (seniors != null && seniors.size() > 0) {
                            chat_roleVO.setName(seniors.get(0).getSeniorName())  ;

                        } else {

                        }
                        //chat_roleVO.setName(seniorService.getSeniorName(chat_role.getConsumerId()));
                        //chat_roleVO.setName(userService.getById(chat_roleVO.getProducerId()).getUserName1());
                        chat_roleVO.setDetail("快来发第一条消息吧~")//设置最后一条消息内容
                                .setLastMessageDate(chat_role.getCreateDate1())//设置最后一条消息创建时间
                                .setIsReceived(1);//设置最后一条消息是否被接受}
                        chat_roleVOS.add(chat_roleVO);

                    }

                }
            }
            else if(users1.get(0).getRoleId() ==RoleEnum.USER ){//说明当前登录的用户身份是普通用户
                QueryWrapper<ChatRole> wrapper = new QueryWrapper<>();
                wrapper.eq("consumer_id", kytUtil.getUserId())
                        ;//所以查询的是当前用户作为消费者的chatRole
                List<ChatRole> chat_roles = baseMapper.selectList(wrapper);
                for (ChatRole chat_role : chat_roles) {//开始封装成VO
                    ChatRoleVO chat_roleVO = new ChatRoleVO();
                    BeanUtils.copyProperties(chat_role, chat_roleVO);
                    chat_roleVO.setRole(0);//设置身份码，表明此次查询是根据当前登录用户作为消费者查询的
                    //获取当前系统时间
                    Date day=new Date();
                    if(chat_role.getModifiedDate1().compareTo(day)<0){//用来实现自动设置服务是否到期
                        chat_roleVO.setIsDelete1(1);//这是给前端传递服务是否到期的信息
                        ChatRole chatRole = new ChatRole();//设置数据库相应的服务到期的标志
                        BeanUtils.copyProperties(chat_role, chatRole);
                        chatRole.setIsDelete1(1);
                        QueryWrapper<ChatRole> wrapper2 = new QueryWrapper<>();
                        wrapper2.eq("consumer_id", kytUtil.getUserId())
                                .eq("producer_id", chatRole.getProducerId());
                        update(chatRole, wrapper2);//更新数据库中的服务是否到期信息

                    }

                    ChatRecordGetPA chat_recordGetPA = new ChatRecordGetPA();//封装一个 ChatRecordGetPA用来调用查询聊天记录的接口
                    chat_recordGetPA.setSenderId(chat_role.getProducerId());//我作为消费者服务的提供方才是给我发消息的
                    List<ChatRecordVO> chat_recordVOS = chat_recordService.get(chat_recordGetPA);
                    //chat_roleVO.setName(userService.querySeniorById(chat_roleVO.getProducerId()))   ;

                    if (chat_recordVOS != null && chat_recordVOS.size() > 0) {

//                        List<User> users = userService.queryUserById(chat_roleVO.getProducerId());//查询的也是服务提供方的用户名
//                        if (users != null && users.size() > 0) {//z这一部分是设置要显示的名字
//                            chat_roleVO.setName(users.get(0).getUserName1())  ;//应当显示的是服务提供方的名字
//
//                        } else {
//
//                        }
                        List<Senior> seniors = seniorService.getSeniorName(chat_roleVO.getProducerId());
                        if (seniors != null && seniors.size() > 0) {
                            chat_roleVO.setName(seniors.get(0).getSeniorName())  ;

                        } else {

                        }
                        //chat_roleVO.setName(seniorService.getSeniorName(chat_role.getProducerId()));
                        //chat_roleVO.setName(userService.getById(chat_recordVOS.get(chat_recordVOS.size()-1).getReceiverId()).getUserName1());
                        chat_roleVO.setDetail(chat_recordVOS.get(chat_recordVOS.size()-1).getDetail())//设置最后一条消息内容
                                .setLastMessageDate(chat_recordVOS.get(chat_recordVOS.size()-1).getCreateDate())//设置最后一条消息创建时间
                                .setSpeaker(chat_recordVOS.get(chat_recordVOS.size()-1).getSpeaker())
                                .setIsReceived(chat_recordVOS.get(chat_recordVOS.size()-1).getIsReceived());//设置最后一条消息是否被接受}
                        chat_roleVOS.add(chat_roleVO);
                    } else {

                        List<Senior> seniors = seniorService.getSeniorName(chat_roleVO.getProducerId());
                        if (seniors != null && seniors.size() > 0) {
                            chat_roleVO.setName(seniors.get(0).getSeniorName())  ;

                        } else {

                        }

                        //chat_roleVO.setName(userService.getById(chat_roleVO.getProducerId()).getUserName1());
                        chat_roleVO.setDetail("快来发第一条消息吧~")//设置最后一条消息内容
                                .setLastMessageDate(chat_role.getCreateDate1())//设置最后一条消息创建时间
                                .setIsReceived(1);//设置最后一条消息是否被接受}
                        chat_roleVOS.add(chat_roleVO);

                    }

                }
            }

        } else {

        }




        return chat_roleVOS;
    }

    @Override
    public List<ChatRoleVO> get() {//获得最新的聊天信息，获得当前用户的会话状态
        List<ChatRoleVO> chat_roleVOS = new ArrayList<>();//需要返回的一个List
        List<User> users1 = userService.queryUserById(kytUtil.getUserId());//首先读取当前用户的信息
        if (users1 != null && users1.size() > 0) {
            if(users1.get(0).getRoleId() ==RoleEnum.SENIOR ){//说明当前登录的用户身份是学长学姐
                QueryWrapper<ChatRole> wrapper = new QueryWrapper<>();
                wrapper.eq("producer_id", kytUtil.getUserId())//所以查询的是当前用户作为消费者的chatRole
                        .ne("is_delete",2);//说明未被删除
                List<ChatRole> chat_roles = baseMapper.selectList(wrapper);
                for (ChatRole chat_role : chat_roles) {//开始封装成VO
                    ChatRoleVO chat_roleVO = new ChatRoleVO();
                    BeanUtils.copyProperties(chat_role, chat_roleVO);
                    chat_roleVO.setRole(1);//设置身份码，表明此次查询是根据当前登录用户作为学长学姐查询谁购买了我的服务
                    //获取当前系统时间
                    Date day=new Date();
                    if(chat_role.getModifiedDate1().compareTo(day)<0){//用来实现自动设置服务是否到期
                        chat_roleVO.setIsDelete1(1);//这是给前端传递服务是否到期的信息
                        ChatRole chatRole = new ChatRole();//设置数据库相应的服务到期的标志
                        BeanUtils.copyProperties(chat_role, chatRole);
                        chatRole.setIsDelete1(1);
                        QueryWrapper<ChatRole> wrapper2 = new QueryWrapper<>();
                        wrapper2.eq("producer_id", kytUtil.getUserId())//我作为的是服务提供者
                                .eq("consumer_id", chatRole.getConsumerId());
                        update(chatRole, wrapper2);//更新数据库中的服务是否到期信息

                    }

                    ChatRecordGetPA chat_recordGetPA = new ChatRecordGetPA();//封装一个 ChatRecordGetPA用来调用查询聊天记录的接口
                    chat_recordGetPA.setSenderId(chat_role.getConsumerId());//我作为服务的提供者，给我发消息的应该是服务的购买者
                    List<ChatRecordVO> chat_recordVOS = chat_recordService.get(chat_recordGetPA);
                    //chat_roleVO.setName(userService.querySeniorById(chat_roleVO.getProducerId()))   ;

                    if (chat_recordVOS != null && chat_recordVOS.size() > 0) {

                        List<User> users = userService.queryUserById(chat_role.getConsumerId());//查询的也是消费者的用户名
                        if (users != null && users.size() > 0) {//这一部分是设置要显示的名字
                            chat_roleVO.setName(users.get(0).getUserName1())  ;//应当显示的是消费者的名字

                        } else {

                        }
                        //chat_roleVO.setName(seniorService.getSeniorName(chat_role.getConsumerId()));
//                        List<Senior> seniors = seniorService.getSeniorName(chat_roleVO.getConsumerId());
//                        if (seniors != null && seniors.size() > 0) {
//                            chat_roleVO.setName(seniors.get(0).getSeniorName())  ;
//
//                        } else {
//
//                        }

                        //chat_roleVO.setName(userService.getById(chat_recordVOS.get(chat_recordVOS.size()-1).getReceiverId()).getUserName1());
                        chat_roleVO.setDetail(chat_recordVOS.get(chat_recordVOS.size()-1).getDetail())//设置最后一条消息内容
                                .setLastMessageDate(chat_recordVOS.get(chat_recordVOS.size()-1).getCreateDate())//设置最后一条消息创建时间
                                .setSpeaker(chat_recordVOS.get(chat_recordVOS.size()-1).getSpeaker())
                                .setIsReceived(chat_recordVOS.get(chat_recordVOS.size()-1).getIsReceived());//设置最后一条消息是否被接受}
                        chat_roleVOS.add(chat_roleVO);
                    } else {

//                        List<User> users = userService.queryUserById(chat_roleVO.getConsumerId());
//                        if (users != null && users.size() > 0) {
//                            chat_roleVO.setName(users.get(0).getUserName1())  ;
//
//                        } else {
//
//                        }
                        List<Senior> seniors = seniorService.getSeniorName(chat_roleVO.getConsumerId());
                        if (seniors != null && seniors.size() > 0) {
                            chat_roleVO.setName(seniors.get(0).getSeniorName())  ;

                        } else {

                        }
                        //chat_roleVO.setName(seniorService.getSeniorName(chat_role.getConsumerId()));
                        //chat_roleVO.setName(userService.getById(chat_roleVO.getProducerId()).getUserName1());
                        chat_roleVO.setDetail("快来发第一条消息吧~")//设置最后一条消息内容
                                .setLastMessageDate(chat_role.getCreateDate1())//设置最后一条消息创建时间
                                .setIsReceived(1);//设置最后一条消息是否被接受}
                        chat_roleVOS.add(chat_roleVO);

                    }

                }
            }
            else if(users1.get(0).getRoleId() ==RoleEnum.USER ){//说明当前登录的用户身份是普通用户
                QueryWrapper<ChatRole> wrapper = new QueryWrapper<>();
                wrapper.eq("consumer_id", kytUtil.getUserId())
                        .ne("is_delete",2);//所以查询的是当前用户作为消费者的chatRole
                List<ChatRole> chat_roles = baseMapper.selectList(wrapper);
                for (ChatRole chat_role : chat_roles) {//开始封装成VO
                    ChatRoleVO chat_roleVO = new ChatRoleVO();
                    BeanUtils.copyProperties(chat_role, chat_roleVO);
                    chat_roleVO.setRole(0);//设置身份码，表明此次查询是根据当前登录用户作为消费者查询的
                    //获取当前系统时间
                    Date day=new Date();
                    if(chat_role.getModifiedDate1().compareTo(day)<0){//用来实现自动设置服务是否到期
                        chat_roleVO.setIsDelete1(1);//这是给前端传递服务是否到期的信息
                        ChatRole chatRole = new ChatRole();//设置数据库相应的服务到期的标志
                        BeanUtils.copyProperties(chat_role, chatRole);
                        chatRole.setIsDelete1(1);
                        QueryWrapper<ChatRole> wrapper2 = new QueryWrapper<>();
                        wrapper2.eq("consumer_id", kytUtil.getUserId())
                                .eq("producer_id", chatRole.getProducerId());
                        update(chatRole, wrapper2);//更新数据库中的服务是否到期信息

                    }

                    ChatRecordGetPA chat_recordGetPA = new ChatRecordGetPA();//封装一个 ChatRecordGetPA用来调用查询聊天记录的接口
                    chat_recordGetPA.setSenderId(chat_role.getProducerId());//我作为消费者服务的提供方才是给我发消息的
                    List<ChatRecordVO> chat_recordVOS = chat_recordService.get(chat_recordGetPA);
                    //chat_roleVO.setName(userService.querySeniorById(chat_roleVO.getProducerId()))   ;

                    if (chat_recordVOS != null && chat_recordVOS.size() > 0) {

//                        List<User> users = userService.queryUserById(chat_roleVO.getProducerId());//查询的也是服务提供方的用户名
//                        if (users != null && users.size() > 0) {//z这一部分是设置要显示的名字
//                            chat_roleVO.setName(users.get(0).getUserName1())  ;//应当显示的是服务提供方的名字
//
//                        } else {
//
//                        }
                        List<Senior> seniors = seniorService.getSeniorName(chat_roleVO.getProducerId());
                        if (seniors != null && seniors.size() > 0) {
                            chat_roleVO.setName(seniors.get(0).getSeniorName())  ;

                        } else {

                        }
                        //chat_roleVO.setName(seniorService.getSeniorName(chat_role.getProducerId()));
                        //chat_roleVO.setName(userService.getById(chat_recordVOS.get(chat_recordVOS.size()-1).getReceiverId()).getUserName1());
                        chat_roleVO.setDetail(chat_recordVOS.get(chat_recordVOS.size()-1).getDetail())//设置最后一条消息内容
                                .setLastMessageDate(chat_recordVOS.get(chat_recordVOS.size()-1).getCreateDate())//设置最后一条消息创建时间
                                .setSpeaker(chat_recordVOS.get(chat_recordVOS.size()-1).getSpeaker())
                                .setIsReceived(chat_recordVOS.get(chat_recordVOS.size()-1).getIsReceived());//设置最后一条消息是否被接受}
                        chat_roleVOS.add(chat_roleVO);
                    } else {

                        List<Senior> seniors = seniorService.getSeniorName(chat_roleVO.getProducerId());
                        if (seniors != null && seniors.size() > 0) {
                            chat_roleVO.setName(seniors.get(0).getSeniorName())  ;

                        } else {

                        }

                        //chat_roleVO.setName(userService.getById(chat_roleVO.getProducerId()).getUserName1());
                        chat_roleVO.setDetail("快来发第一条消息吧~")//设置最后一条消息内容
                                .setLastMessageDate(chat_role.getCreateDate1())//设置最后一条消息创建时间
                                .setIsReceived(1);//设置最后一条消息是否被接受}
                        chat_roleVOS.add(chat_roleVO);

                    }

                }
            }

        } else {

        }




        return chat_roleVOS;
    }

    @Override
    public boolean update1(ChatRoleUpdatePA chat_roleUpdatePA) {//更新会话
        ChatRole chat_role = new ChatRole();//chat_role用来更新会话信息
        List<ChatRoleVO> chat_roleVOS = chat_roleService.get1();



        Integer flag =0;

        for(ChatRoleVO chat_roleVO:chat_roleVOS){//对所有与该用户相关的会话进行遍历

            if(chat_roleVO.getProducerId().equals(chat_roleUpdatePA.getProducerId()))//如果该会话是要修改的会话
            {
                flag =1;
                if (chat_roleVO.getIsDelete1() == 0) {//当前会话服务没有到期
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(chat_roleVO.getModifiedDate1());
                    calendar.add(Calendar.DATE, chat_roleUpdatePA.getDayNumber());//根据购买天数计算出服务到期天数
                    chat_role.setModifiedDate1(calendar.getTime());//设置服务到期时间
                    chat_role.setDayNumber(chat_roleUpdatePA.getDayNumber());//设置服务购买天数
                    chat_role.setProducerId(chat_roleUpdatePA.getProducerId());//设置服务提供者Id
                    chat_role.setConsumerId(kytUtil.getUserId());//设置服务购买者Id
                    chat_role.setCreateDate1(chat_roleVO.getCreateDate1());//设置服务开始时间
                    chat_role.setIsDelete1(0);
                    break;
                }
                else if (chat_roleVO.getIsDelete1() != 0){//当前会话服务已经到期
                    Date date = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date time_date = null;
                    try {
                        time_date = sdf.parse(sdf.format(date));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    chat_role.setCreateDate1(time_date);//将当前系统时间设定为服务开始时间
                    Calendar calendar = Calendar.getInstance();
                    assert time_date != null;
                    calendar.setTime(time_date);
                    calendar.add(Calendar.DATE,chat_roleUpdatePA.getDayNumber());//根据购买天数计算出服务到期天数
                    chat_role.setModifiedDate1(calendar.getTime());//设置服务到期时间
                    chat_role.setProducerId(chat_roleUpdatePA.getProducerId());//设置服务提供者Id
                    chat_role.setConsumerId(kytUtil.getUserId());//设置服务购买者Id
                    chat_role.setDayNumber(chat_roleUpdatePA.getDayNumber());//设置服务购买天数
                    chat_role.setIsDelete1(0);//设置服务未到期

                }
            }

        }
        if(flag==0)
        {
            ChatRoleAddPA chatRoleAddPA = new ChatRoleAddPA();
            chatRoleAddPA.setProducerId(chat_roleUpdatePA.getProducerId());
            chatRoleAddPA.setDayNumber(chat_roleUpdatePA.getDayNumber());
            chat_roleService.add(chatRoleAddPA);
        }
        else
        {
            QueryWrapper<ChatRole> wrapper1 = new QueryWrapper<>();
            wrapper1.eq("consumer_id", kytUtil.getUserId())
                    .eq("producer_id", chat_role.getProducerId());
            return update(chat_role, wrapper1);
        }
        return true;
    }

    @Override
    public boolean delete(ChatRoleUpdatePA chat_roleUpdatePA) {
        ChatRole chat_role = new ChatRole();//chat_role用来更新会话信息
        QueryWrapper<ChatRole> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("consumer_id", kytUtil.getUserId())
                .eq("producer_id", chat_roleUpdatePA.getProducerId());
        List<ChatRole> chat_roles = baseMapper.selectList(wrapper1);
        for (ChatRole chatrole : chat_roles) {
            chatrole.setIsDelete1(2);
            BeanUtils.copyProperties(chatrole, chat_role);
        }
        return update(chat_role, wrapper1);
    }


}
