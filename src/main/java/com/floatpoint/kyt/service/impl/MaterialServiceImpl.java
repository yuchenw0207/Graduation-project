package com.floatpoint.kyt.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.floatpoint.kyt.entity.dataobject.Material;
import com.floatpoint.kyt.entity.dataobject.Materialbrowse;
import com.floatpoint.kyt.entity.params.add.MaterialAddPA;
import com.floatpoint.kyt.mapper.MaterialMapper;
import com.floatpoint.kyt.service.MaterialService;
import com.floatpoint.kyt.common.utils.kytUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MaterialServiceImpl extends ServiceImpl<MaterialMapper, Material> implements MaterialService {
    @Autowired
    MaterialMapper materialMapper;
    /* 通过搜索tag标签来搜索  提供tag_id 返回值 material_id title favorite_count*/
    @Override
    public List<Material> search(String tag) {
        List<Material> search = materialMapper.search(tag);
        return search;
    }
    /* 打开资料详情 提供material_id 返回值m1.title,m1.text_content,m1.image_content,m1.poster_id,m1.create_date*/
    @Override
    public List<Material> detail(String materialId) {
        List<Material> detail = materialMapper.detail(materialId);
        return detail;
    }

    /*删除资料 提供posterId,material_id 返回boolean值*/
    @Override
    public boolean deletede(String posterId,String materialId) {
        boolean  deletede = materialMapper.deletede(posterId,materialId);
        return deletede;
    }


    /* */
    @Override
    public boolean add(MaterialAddPA materialAddPA)
    {
        Material material =new Material();
        BeanUtils.copyProperties(materialAddPA,material);


        Date date = new Date();
        material.setCreateDate(date);
        material.setModifiedDate(date);
        material.setLikeCount(0).setFavoriteCount(0).setIsDelete(0).setPosterId(kytUtil.getUserId());
        return save(material);
    }
    /*主页的内容推荐 无需提供参数 返回title material_id favorite_count*/
    @Override
    public List<Material> recommend() {
        List<Material> recommend = materialMapper.recommend();
        return recommend;
    }
    @Override
    public List<Material> selectlast() {
        List<Material> selectlast = materialMapper.selectlast();
        return selectlast;
    }


    @Override
    public List<Material> searchbro(String userId)
    {
        List<Material> searchbro=materialMapper.searchbro(userId);
        return searchbro;
    }
    @Override
    public List<Material> searchfav(String userId)
    {
        List<Material> searchbro=materialMapper.searchfav(userId);
        return searchbro;
    }





//    @Override
//    public List<MaterialVO> homere(long current, long limit) {
//
//        Page<material> materialPage = new Page<>(current, limit);
//        QueryWrapper<material> wrapper = new QueryWrapper<>();
//        wrapper.orderByAsc("like_count");
//        this.page(materialPage, wrapper);
//        List<material> records = materialPage.getRecords(); // 当前页记录
//        // 封装已完成待办信息
//        List<MaterialVO> materialVOS = new ArrayList<>();
//
//
//        int type;
//        MaterialVO materialVO;
//
//        for (material record : records) {
//            System.out.println(record);
//            materialVO = new MaterialVO();
//            BeanUtils.copyProperties(record, materialVO);
//            materialVOS.add(materialVO);
//        }
//        return materialVOS;
//    }




//   @Override
//    public List<MaterialdetailVO> getde(long current, long limit, String id) {
//
//        Page<material> materialPage = new Page<>(current, limit);
//        QueryWrapper<material> wrapper = new QueryWrapper<>();
//        wrapper.eq("material_id",id);
//        this.page(materialPage, wrapper);
//        List<material> records = materialPage.getRecords(); // 当前页记录
//        // 封装已完成待办信息
//        List<MaterialdetailVO> materialdetailVOS = new ArrayList<>();
//
//
//        int type;
//        MaterialdetailVO materialdetailVO;
//
//        for (material record : records) {
//            System.out.println(record);
//            materialdetailVO = new MaterialdetailVO();
//            BeanUtils.copyProperties(record, materialdetailVO);
//            materialdetailVOS.add(materialdetailVO);
//        }
//        return materialdetailVOS;
//
//
//    }


}


