package com.floatpoint.kyt.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.floatpoint.kyt.common.enums.RoleEnum;
import com.floatpoint.kyt.entity.dataobject.User;
import com.floatpoint.kyt.entity.params.add.UserAddPA;
import com.floatpoint.kyt.entity.params.query.UserQuery;
import com.floatpoint.kyt.entity.params.update.UserUpdatePA;
import com.floatpoint.kyt.entity.vo.UserMessageVO;
import com.floatpoint.kyt.entity.vo.UserVO;
import com.floatpoint.kyt.mapper.UserMapper;
import com.floatpoint.kyt.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import com.floatpoint.kyt.common.utils.kytUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 服务实现类
 *
 * @author cbj
 * @author yiming
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
//    @Autowired UserMapper userMapper;
    @Override
    public boolean add(UserAddPA addVO) {
        User user = new User();
        BeanUtils.copyProperties(addVO,user);
        user.setUserId(addVO.getUserId());
        user.setUserName1("Nameless");
        user.setRoleId(RoleEnum.USER);
        user.setIsDelete(0);
        return save(user);
    }//新增用户
    @Override
    public List <UserMessageVO> findByUserId(String id){
        List<UserMessageVO> userMessageVOS = new ArrayList<>();
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",id);
        List<User> users = baseMapper.selectList(wrapper);

        //封装个人信息
        UserMessageVO userMessageVO;
        int type;
        for(User user:users){
            userMessageVO=new UserMessageVO();
            BeanUtils.copyProperties(user,userMessageVO);
            userMessageVO.setUserName1(user.getUserName1());
            userMessageVOS.add(userMessageVO);
        }
        return userMessageVOS;
    }//获取用户资料
    @Override
    public List<UserVO> PageFindUser(long current, long limit, UserQuery userQuery){
        Page<User> userPage = new Page<>(current, limit);
        QueryWrapper<User> wrapper=new QueryWrapper<>();
        String name= userQuery.getUserName1();
        wrapper.like("user_name",name);
        this.page(userPage,wrapper); //调用方法时，底层封装，把分页所有数据都封装到userPage对象里面
        List<User> records=userPage.getRecords();//获取当前页记录
        List<UserVO> userVOS = new ArrayList<>();
        //封装个人信息
        UserVO userVO;
        int type;
        for(User record:records){
            userVO = new UserVO();
            BeanUtils.copyProperties(record,userVO);
            userVOS.add(userVO);
        }
        return userVOS;
    }//模糊查询
    @Override
    public boolean update(UserUpdatePA userUpdatePA){
        User user= getById(kytUtil.getUserId());
        BeanUtils.copyProperties(userUpdatePA,user);
        return updateById(user);
    }//修改个人资料



    @Override
    public String querySeniorById ( String id ){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //wrapper.eq("user_id" , id);
       //List<User> users = baseMapper.selectList(wrapper);
        User user = baseMapper.selectById(id);

        return user.getUserName1();
    }
    @Override
    public List<User> queryUserById(String id){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id" , id);
        List<User> users = baseMapper.selectList(wrapper);
        return users;
    }

    @Override
    public boolean setUserRoleId(String id){
        User user = getById(id);
        user.setRoleId(RoleEnum.SENIOR);
        return  updateById(user);
    }


}
