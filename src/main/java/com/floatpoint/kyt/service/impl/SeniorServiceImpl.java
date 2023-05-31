package com.floatpoint.kyt.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.floatpoint.kyt.entity.dataobject.Senior;
import com.floatpoint.kyt.entity.dataobject.User;
import com.floatpoint.kyt.entity.params.add.SeniorAddPA;
import com.floatpoint.kyt.entity.params.query.SeniorSelectPA;
import com.floatpoint.kyt.entity.params.update.SeniorPositiveUpdatePA;
import com.floatpoint.kyt.entity.params.update.SeniorUpdatePA;
import com.floatpoint.kyt.mapper.SeniorMapper;
import com.floatpoint.kyt.service.SeniorService;
import com.floatpoint.kyt.service.UserService;
import com.floatpoint.kyt.common.utils.kytUtil;
import com.floatpoint.kyt.entity.vo.SeniorInfoVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SeniorServiceImpl extends ServiceImpl<SeniorMapper, Senior> implements SeniorService {

    @Autowired
    UserService userService;

    //查询用户自身作为学长学姐的信息
    @Override
    public List<SeniorInfoVO> queryMySeniorInfo (){
        List<SeniorInfoVO> seniorInfoVOS = new ArrayList<>();
        QueryWrapper<Senior> wrapper =new QueryWrapper<>();
        wrapper.eq("user_id" , kytUtil.getUserId());
        List<Senior> seniors = baseMapper.selectList(wrapper);

        SeniorInfoVO seniorInfoVO;
        for (Senior senior : seniors){
            seniorInfoVO =new SeniorInfoVO();
            BeanUtils.copyProperties(senior , seniorInfoVO);
            seniorInfoVOS.add(seniorInfoVO);
        }
        Senior senior = getById(kytUtil.getUserId());
        return seniorInfoVOS;
    }

    @Override
    public SeniorInfoVO getMySeniorInfo (){
        Senior senior = getById(kytUtil.getUserId());
        SeniorInfoVO seniorInfoVO = new SeniorInfoVO();
        BeanUtils.copyProperties(senior, seniorInfoVO);
        return seniorInfoVO;
    }

    //查询所有学长学姐的信息
    @Override
    public List<SeniorInfoVO> getSeniorInfo (){
        List<SeniorInfoVO> seniorInfoVOS = new ArrayList<>();
        QueryWrapper<Senior> wrapper =new QueryWrapper<>();
        List<Senior> seniors = baseMapper.selectList(wrapper);
        SeniorInfoVO seniorInfoVO;
        if (seniors != null && seniors.size() > 0) {
            for (Senior senior : seniors){
                seniorInfoVO =new SeniorInfoVO();
                BeanUtils.copyProperties(senior , seniorInfoVO);
                seniorInfoVOS.add(seniorInfoVO);
            }

        } else {
        }
        return seniorInfoVOS;
    }

    //根据选项查找符合条件的学长学姐
    @Override
    public List<SeniorInfoVO> getSelectSeniorInfo (SeniorSelectPA seniorSelectPA){
        List<SeniorInfoVO> seniorInfoVOS = new ArrayList<>();
        QueryWrapper<Senior> seniorSelectWrapper = new QueryWrapper<>();
        if (seniorSelectPA.getCurrentSchool()!=null)
            seniorSelectWrapper.like("current_school" , seniorSelectPA.getCurrentSchool());
        if (seniorSelectPA.getCurrentSpeciality()!=null)
            seniorSelectWrapper.like("current_speciality" , seniorSelectPA.getCurrentSpeciality());
        List<Senior> seniors = baseMapper.selectList(seniorSelectWrapper);
        SeniorInfoVO seniorInfoVO;
        for (Senior senior : seniors){
            seniorInfoVO = new SeniorInfoVO();
            BeanUtils.copyProperties(senior,seniorInfoVO);
            seniorInfoVOS.add(seniorInfoVO);
        }
        return seniorInfoVOS;
    }

    //提交自身的学长学姐信息修改
    @Override
    public boolean setMySeniorInfo ( SeniorUpdatePA seniorUpdatePA ){
        QueryWrapper<Senior> seniorQueryWrapper = new QueryWrapper<>();
        seniorQueryWrapper.eq("user_id" , kytUtil.getUserId());
        Senior senior = new Senior();
        BeanUtils.copyProperties(seniorQueryWrapper,senior);
        BeanUtils.copyProperties(seniorUpdatePA,senior);
        return update(senior,seniorQueryWrapper);
    }

    //当前用户评价某个学长学姐，对该学长学姐的评价值进行更新
    public boolean updatePositiveRating (SeniorPositiveUpdatePA seniorPositiveUpdatePA){
        QueryWrapper<Senior> seniorQueryWrapper = new QueryWrapper<>();
        seniorQueryWrapper.eq("user_id" , seniorPositiveUpdatePA.getAppraisedId());
        Senior senior = baseMapper.selectById(seniorPositiveUpdatePA.getAppraisedId());
        float PositiveRating = (1 * seniorPositiveUpdatePA.getEvaluate() + senior.getEvaluateCount() * senior.getPositiveRating())/(senior.getEvaluateCount()+1);
        senior.setEvaluateCount(senior.getEvaluateCount()+1);
        senior.setPositiveRating(PositiveRating);
        return update(senior , seniorQueryWrapper);
    }

    @Override
    public boolean addSenior (SeniorAddPA seniorAddPA){
        Senior senior = new Senior();
        BeanUtils.copyProperties(seniorAddPA, senior);
        senior.setUserId(kytUtil.getUserId());
        senior.setPositiveRating(0);
        senior.setEvaluateCount(0);
        senior.setIsDelete(0);
        userService.setUserRoleId(kytUtil.getUserId());
        return save(senior);
    }

    @Override
    public List<Senior> getSeniorName (String userid){
        QueryWrapper<Senior> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id" , userid);
        List<Senior> seniors = baseMapper.selectList(wrapper);
        return seniors;
    }

}
