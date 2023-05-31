package com.floatpoint.kyt.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.floatpoint.kyt.entity.dataobject.Materialtag;
import com.floatpoint.kyt.entity.params.add.MaterialAddPA;
import com.floatpoint.kyt.entity.params.add.MaterialtagAddPA;


import java.util.List;

public interface MaterialtagService extends  IService<Materialtag>{

    List<Materialtag> history(String userId);
    boolean addtag(MaterialtagAddPA materialtagAddPA);
    boolean deletetag(String materialId,String tagName);
    boolean deletemt(String materialId);
}
