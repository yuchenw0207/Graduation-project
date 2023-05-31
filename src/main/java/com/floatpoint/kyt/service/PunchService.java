package com.floatpoint.kyt.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.floatpoint.kyt.entity.dataobject.Punch;
import com.floatpoint.kyt.entity.params.add.PunchAddPA;
import com.floatpoint.kyt.entity.vo.PunchVO;

import java.text.ParseException;
import java.util.List;

public interface PunchService extends IService<Punch> {
    /**
     * 新建打卡记录
     */
    boolean add(PunchAddPA punchAddPA);

    /**
     *查看所有的打卡记录
     */
    List<PunchVO> punchList();
    /**
     * 根据年月份获取当月打卡记录
     *
     */
    List<Integer> getLocalMonth(String year,String month) throws ParseException;
}
