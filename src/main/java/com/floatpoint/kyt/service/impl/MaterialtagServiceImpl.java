package com.floatpoint.kyt.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.floatpoint.kyt.common.utils.kytUtil;
import com.floatpoint.kyt.entity.dataobject.Material;
import com.floatpoint.kyt.entity.dataobject.Materialtag;
import com.floatpoint.kyt.entity.params.add.MaterialAddPA;
import com.floatpoint.kyt.entity.params.add.MaterialtagAddPA;
import com.floatpoint.kyt.mapper.MaterialtagMapper;
import com.floatpoint.kyt.service.MaterialtagService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MaterialtagServiceImpl extends ServiceImpl <MaterialtagMapper, Materialtag>implements MaterialtagService {
    @Autowired
    MaterialtagMapper materialtagMapper;
    @Override
    public List<Materialtag> history(String userId) {
        List<Materialtag> materialtags= materialtagMapper.history(userId);
        return materialtags;
    }

    @Override
    public boolean addtag(MaterialtagAddPA materialtagAddPA)
    {
        Materialtag materialtag =new Materialtag();
        BeanUtils.copyProperties(materialtagAddPA,materialtag);
        Date date = new Date();
        materialtag.setCreateDate(date);
        materialtag.setModifiedDate(date);
        materialtag.setIsDelete(0);
        return save(materialtag);
    }

    @Override
    public boolean deletetag(String materialId,String tagName)
    {
        boolean deletetag=materialtagMapper.deletetag(materialId,tagName);
        return deletetag;
    }

    @Override
    public boolean deletemt(String materialId)
    {
        boolean deletemt=materialtagMapper.deletemt(materialId);
        return deletemt;
    }
}
