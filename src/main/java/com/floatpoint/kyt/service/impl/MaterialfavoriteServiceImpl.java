package com.floatpoint.kyt.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.floatpoint.kyt.entity.dataobject.Materialfavorite;
import com.floatpoint.kyt.entity.params.add.MaterialfavoriteAddPA;
import com.floatpoint.kyt.mapper.MaterialfavoriteMapper;
import com.floatpoint.kyt.service.MaterialfavoriteService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.floatpoint.kyt.common.utils.*;

import java.util.Date;
import java.util.List;

@Service
public class MaterialfavoriteServiceImpl extends ServiceImpl<MaterialfavoriteMapper, Materialfavorite> implements MaterialfavoriteService {
    @Autowired
    MaterialfavoriteMapper materialfavoriteMapper;

    @Override
    public boolean favoritecount(String materialId){


        boolean favoritecount=materialfavoriteMapper.favoritecount(materialId);
        return favoritecount;
    }
    @Override
    public boolean decfavoritecount(String materialId){

        //userId=TodoListUtil.getUserId();
        boolean favoritecount=materialfavoriteMapper.decfavoritecount(materialId);
        return favoritecount;
    }


    @Override
    public boolean add(MaterialfavoriteAddPA materialfavoriteAddPA)
    {
        Materialfavorite materialfavorite =new Materialfavorite();
        BeanUtils.copyProperties(materialfavoriteAddPA,materialfavorite);
        Date date=new Date();
        materialfavorite.setUserId(kytUtil.getUserId()).setIsDelete(0).setModifiedDate(date);
        return save(materialfavorite);
    }

    @Override
    public List<Materialfavorite> jude(String userId, String materialId)
    {
        List<Materialfavorite> jude = materialfavoriteMapper.jude(userId,materialId);

        return jude;
    }



    @Override
    public boolean deletefav(String userId,String materialId)
    {
        boolean deletefav=materialfavoriteMapper.deletefav(userId,materialId);
        return deletefav;
    }
}
