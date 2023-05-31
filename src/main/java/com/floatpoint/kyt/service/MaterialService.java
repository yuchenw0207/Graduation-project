package com.floatpoint.kyt.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.floatpoint.kyt.entity.dataobject.Material;

import com.floatpoint.kyt.entity.params.add.MaterialAddPA;

import java.util.List;

public interface MaterialService extends IService<Material>{
    List<Material> recommend();
    List<Material> search(String tag);
    List<Material> detail(String materialId);
    boolean  deletede(String posterId,String materialId);
    boolean add(MaterialAddPA materialAddPA);
    List<Material> searchbro(String userId);
    List<Material> searchfav(String userId);
    List<Material> selectlast();


    //List<MaterialdetailVO> getde(long current, long limit, String id);
    //List<MaterialVO> homere(long current, long limit);
}
