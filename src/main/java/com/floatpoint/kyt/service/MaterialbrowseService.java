package com.floatpoint.kyt.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.floatpoint.kyt.entity.dataobject.Material;
import com.floatpoint.kyt.entity.dataobject.Materialbrowse;
import com.floatpoint.kyt.entity.params.add.MaterialbrowseAddPA;
import org.apache.ibatis.annotations.Param;


import java.util.List;

public interface MaterialbrowseService extends IService<Materialbrowse>{

    boolean deletebro( String userId,String materialId);
    boolean deleteall( String userId);
    boolean add(MaterialbrowseAddPA materialbrowseAddPA);
}
