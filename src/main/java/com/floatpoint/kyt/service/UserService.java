package com.floatpoint.kyt.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.floatpoint.kyt.common.enums.RoleEnum;
import com.floatpoint.kyt.entity.dataobject.User;
import com.floatpoint.kyt.entity.params.add.UserAddPA;
import com.floatpoint.kyt.entity.params.query.UserQuery;
import com.floatpoint.kyt.entity.params.update.UserUpdatePA;
import com.floatpoint.kyt.entity.vo.UserMessageVO;
import com.floatpoint.kyt.entity.vo.UserVO;

import java.util.List;


public interface UserService extends IService<User> {
    /**
     * 增加一个用户
     * @param addVO
     * @return
     */
     boolean add(UserAddPA addVO);
    /**
     * 获取用户信息
     */
     List<UserMessageVO> findByUserId(String id);
    /**
     * 模糊查询用户
     */
     List<UserVO> PageFindUser(long current, long limit, UserQuery userQuery);
    /**
     * 修改个人信息
     */
    boolean update(UserUpdatePA userUpdatePA);

     String querySeniorById (String id);//通过用户Id查询学长学姐信息

     List<User> queryUserById(String id);//通过id查询用户信息

     boolean setUserRoleId(String id);
}
