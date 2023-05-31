package com.floatpoint.kyt.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import com.floatpoint.kyt.common.utils.kytUtil;
import com.floatpoint.kyt.entity.dataobject.Punch;
import com.floatpoint.kyt.entity.params.add.PunchAddPA;
import com.floatpoint.kyt.entity.vo.PunchVO;
import com.floatpoint.kyt.mapper.PunchMapper;
import com.floatpoint.kyt.service.PunchService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class PunchServiceImpl extends ServiceImpl<PunchMapper, Punch> implements PunchService {
    /**
     * 新建打卡记录
     */
    @Override
    public boolean add(PunchAddPA punchAddPA) {
        Punch punch = new Punch();
        BeanUtils.copyProperties(punchAddPA, punch);
        punch.setUserId(kytUtil.getUserId())
                .setIsDelete(0);
        return save(punch);
    }
    /**
     * 查询所有的打卡记录
     */
    @Override
    public List<PunchVO> punchList() {
        List<PunchVO> punchVOS= new ArrayList<>();
        QueryWrapper<Punch> wrapper=new QueryWrapper<>();
        wrapper.eq("user_id", kytUtil.getUserId())
                .orderByDesc("create_date");
        Integer count= baseMapper.selectCount(wrapper);
        List<Punch> punches=baseMapper.selectList(wrapper);


        //封装打卡信息
        PunchVO punchVO;
        for (Punch punch: punches) {
            punchVO = new PunchVO();
            BeanUtils.copyProperties(punch, punchVO);
            punchVO.setTotalDay(count);
            punchVOS.add(punchVO);
        }
        return punchVOS;
    }
    /**
     * 查询当月的打卡记录
     */
    @Override
    public List<Integer> getLocalMonth(String year,String month) throws ParseException {
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date=new Date();
        String time=year+'-'+month+"-1 00:00:00";
        String endTime=null;
        Integer dayCount=0;
        switch (month){
            case "1":
            case "3":
            case "5":
            case "7":
            case "8":
            case "10":
            case "12": endTime = year+'-'+month+"-31 23:59:59";
                        dayCount=31;
                break;
            case "4":
            case "6":
            case "9":
            case "11": endTime = year+'-'+month+"-30 23:59:59";
                        dayCount=30;
                break;
            case "2":
                Integer yy = Integer.parseInt(year);
                if(yy%400 == 0 || yy%100 != 0&&yy%4==0){
                    endTime = year + '-' + month + "-28 23:59:59";
                    dayCount=28;
                }
                else {
                    endTime = year + '-' + month + "-29 23:59:59";
                    dayCount=29;
                }
        }
        Date beginDate=dateFormat.parse(time);
        Date endDate=dateFormat.parse(endTime);
        QueryWrapper<Punch> wrapper=new QueryWrapper<>();
        wrapper.apply(endDate!=null,
                        "DATE_FORMAT(create_date,'%Y-%m-%d %H:%i:%s') <= DATE_FORMAT('" + dateFormat.format(endDate)+ "','%Y-%m-%d %H:%i:%s')")
                .apply(beginDate!=null,
                        "DATE_FORMAT(create_date,'%Y-%m-%d %H:%i:%s') >= DATE_FORMAT('" + dateFormat.format(beginDate)+ "','%Y-%m-%d %H:%i:%s')")
                .orderByAsc("create_date")
                .eq("user_id", kytUtil.getUserId());
        List<Punch> punches=baseMapper.selectList(wrapper);
        List<Integer> arry=new ArrayList<Integer>(Collections.nCopies(dayCount, 0));
        for(Punch punch:punches){
            Date tempDate=punch.getCreateDate();
            Integer tempDay=tempDate.getDate();
            arry.set(tempDay,1);
        }
        return arry;
    }

}
