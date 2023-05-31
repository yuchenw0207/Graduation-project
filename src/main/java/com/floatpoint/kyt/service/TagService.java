package com.floatpoint.kyt.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.floatpoint.kyt.entity.dataobject.SchoolTag;
import com.floatpoint.kyt.entity.dataobject.Tag;
import com.floatpoint.kyt.entity.params.add.TagAddPA;
import com.floatpoint.kyt.entity.vo.TagVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TagService extends IService<Tag> {
    /**
     *新建tag标签
     */
    boolean add(TagAddPA tagAddPA);

    /**
     * 删除tag标签
     */
    //int delete(String id);

    /**
     * 查看所有tag标签
     */

    //List<Tag> getAll();
    List<TagVO> TagList();

    /**
     * 更新帖子查询次数
     * @return
     */
    boolean change(String id);

    TagVO gettag(SchoolTag school_tag);

    /**
     * 根据标签名字查询id
     */
    Tag tagNameFindSchool(String name);

    Tag tagNameFindPost(String name);
}
