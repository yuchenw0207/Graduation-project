package com.floatpoint.kyt.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.floatpoint.kyt.entity.dataobject.Material;
import com.floatpoint.kyt.entity.dataobject.Materialbrowse;
import com.floatpoint.kyt.entity.dataobject.PostBrowse;
import com.floatpoint.kyt.entity.params.add.MaterialbrowseAddPA;
import com.floatpoint.kyt.mapper.MaterialbrowseMapper;
import com.floatpoint.kyt.service.MaterialbrowseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.floatpoint.kyt.common.utils.*;


import java.util.Date;
import java.util.List;


@Service
public class MaterialbrowseServiceImpl extends ServiceImpl<MaterialbrowseMapper, Materialbrowse> implements MaterialbrowseService {
    @Autowired
    MaterialbrowseMapper materialbrowseMapper;


    @Override
    public boolean add(MaterialbrowseAddPA materialbrowseAddPA)
    {
        Materialbrowse materialbrowse =new Materialbrowse();
        QueryWrapper<Materialbrowse> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",kytUtil.getUserId())
                .eq("material_id",materialbrowseAddPA.getMaterialId());
        List<Materialbrowse> materialbrowses = baseMapper.selectList(wrapper);
        if(materialbrowses.isEmpty()) {
            BeanUtils.copyProperties(materialbrowseAddPA, materialbrowse);
            materialbrowse.setUserId(kytUtil.getUserId())
                    .setIsDelete(0);
        }
        else{
            materialbrowse = materialbrowses.get(0);
            materialbrowse.setIsDelete(0);
        }
//        BeanUtils.copyProperties(materialbrowseAddPA,materialbrowse);
//        Date date=new Date();
//        materialbrowse.setUserId(kytUtil.getUserId()).setIsDelete(0).setCreateDate(date) .setModifiedDate(date);
        return saveOrUpdate(materialbrowse);
    }
    @Override
    public boolean deletebro(String userId,String materialId)
    {
        boolean  deletedebro = materialbrowseMapper.deletebro(userId,materialId);
        return deletedebro;
    }
    @Override
    public boolean deleteall(String userId)
    {
        boolean deleteall=materialbrowseMapper.deleteall(userId);
        return deleteall;
    }


}
