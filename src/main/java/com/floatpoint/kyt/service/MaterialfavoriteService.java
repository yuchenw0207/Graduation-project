package com.floatpoint.kyt.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.floatpoint.kyt.entity.dataobject.Materialfavorite;
import com.floatpoint.kyt.entity.params.add.MaterialfavoriteAddPA;


import java.util.List;

public interface MaterialfavoriteService extends  IService<Materialfavorite>{
    boolean favoritecount(String materialId);
    boolean decfavoritecount(String materialId);

    boolean add(MaterialfavoriteAddPA materialfavoriteAddPA);
    List<Materialfavorite> jude(String userId, String materialId);
    boolean deletefav(String userId,String materialId);
}
