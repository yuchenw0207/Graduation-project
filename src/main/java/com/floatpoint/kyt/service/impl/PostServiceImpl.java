package com.floatpoint.kyt.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.floatpoint.kyt.entity.dataobject.User;
import com.floatpoint.kyt.service.UserService;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.floatpoint.kyt.common.utils.kytUtil;
import com.floatpoint.kyt.entity.dataobject.Post;
import com.floatpoint.kyt.entity.params.add.PostAddPA;
import com.floatpoint.kyt.entity.vo.PostVO;
import com.floatpoint.kyt.mapper.PostMapper;
import com.floatpoint.kyt.service.PostService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@Accessors(chain = true)
public class PostServiceImpl extends ServiceImpl<PostMapper, Post> implements PostService {

    @Autowired
    UserService userService;

    LocalDateTime today = LocalDateTime.now();


    /**添加一个帖子*/
    @Override
    public List<PostVO> add(PostAddPA postAddPA)
    {
        Post post = new Post();
        //将分别存入的URL合并，并插入识别用字符
        String[] images = new String[9];
        StringBuffer imageBuffer = new StringBuffer();
        for(int i=0; i< images.length;i++)
        {
            switch (i) {
                case 0:
                    images[i]=postAddPA.getImageContent1();
                    break;
                case 1:
                    images[i]=postAddPA.getImageContent2();
                    break;
                case 2:
                    images[i]=postAddPA.getImageContent3();
                    break;
                case 3:
                    images[i]=postAddPA.getImageContent4();
                    break;
                case 4:
                    images[i]=postAddPA.getImageContent5();
                    break;
                case 5:
                    images[i]=postAddPA.getImageContent6();
                    break;
                case 6:
                    images[i]=postAddPA.getImageContent7();
                    break;
                case 7:
                    images[i]=postAddPA.getImageContent8();
                    break;
                case 8:
                    images[i]=postAddPA.getImageContent9();
                    break;

            }
        }
        for(int i=0;i<9;i++)
        {

            imageBuffer.append(images[i]+"$");
        }
        BeanUtils.copyProperties(postAddPA,post);
        String  imageContent= new String(imageBuffer);
        post.setPosterId(kytUtil.getUserId())
                .setFavoriteCount(0)
                .setLikeCount(0)
                .setFavoriteCount(0)
                .setImageContent(imageContent.toString())
                .setPosterId(kytUtil.getUserId())
                .setIsDelete(0);
        save(post);
        PostVO addBefore = new PostVO();
        BeanUtils.copyProperties(post,addBefore);//返回刚加入数据库的那条记录的部分，主要是为了id
        List<PostVO> addBefores = new ArrayList<>();
        addBefores.add(addBefore);
        return addBefores;
    }


    /**
     * 分页查询个人帖子
     * @param current
     * @param limit
     * @return
     */
    @Override
    public List<PostVO> pageQueryMyPost(long current, long limit) {

        Page<Post> postPage = new Page<>(current, limit);
        QueryWrapper<Post> wrapper =new QueryWrapper<>();
        wrapper.eq("poster_id", kytUtil.getUserId())
                .orderByDesc("create_date");
        this.page(postPage, wrapper);
        List<Post> records =postPage.getRecords();
        List<PostVO> postVOS = new ArrayList<>();
        PostVO postVO;
        for(Post record : records)
        {
            postVO = new PostVO();
            BeanUtils.copyProperties(record, postVO);
            postVO.setPosterName((userService.getById(record.getPosterId()).getUserName1()));
            //分割图片的URL
            String[] images = record.getImageContent().split("\\$");
            for(int i=0; i< images.length;i++)
            {
                switch (i) {
                    case 0:
                        postVO.setImageContent1(images[i]);
                        break;
                    case 1:
                        postVO.setImageContent2(images[i]);
                        break;
                    case 2:
                        postVO.setImageContent3(images[i]);
                        break;
                    case 3:
                        postVO.setImageContent4(images[i]);
                        break;
                    case 4:
                        postVO.setImageContent5(images[i]);
                        break;
                    case 5:
                        postVO.setImageContent6(images[i]);
                        break;
                    case 6:
                        postVO.setImageContent7(images[i]);
                        break;
                    case 7:
                        postVO.setImageContent8(images[i]);
                        break;
                    case 8:
                        postVO.setImageContent9(images[i]);
                        break;

                }
            }
            postVOS.add(postVO);
        }
        return postVOS;
    }


    /**
     * 根据id返回某个帖子的PostVO
     * @param postId
     * @return
     */
    @Override
    public PostVO queryById (String postId){
        QueryWrapper<Post> wrapper = new QueryWrapper<>();
        wrapper.eq("post_id",postId)
                .orderByDesc("create_date");
        List<Post> postList = baseMapper.selectList(wrapper);
        PostVO postVO = new PostVO();
        for(Post postResult : postList) {
            BeanUtils.copyProperties(postResult, postVO);
            postVO.setPosterName((userService.getById(postResult.getPosterId()).getUserName1()));
            //分割图片的URL
            String[] images = postResult.getImageContent().split("\\$");
            for (int i = 0; i < images.length; i++) {
                switch (i) {
                    case 0:
                        postVO.setImageContent1(images[i]);
                        break;
                    case 1:
                        postVO.setImageContent2(images[i]);
                        break;
                    case 2:
                        postVO.setImageContent3(images[i]);
                        break;
                    case 3:
                        postVO.setImageContent4(images[i]);
                        break;
                    case 4:
                        postVO.setImageContent5(images[i]);
                        break;
                    case 5:
                        postVO.setImageContent6(images[i]);
                        break;
                    case 6:
                        postVO.setImageContent7(images[i]);
                        break;
                    case 7:
                        postVO.setImageContent8(images[i]);
                        break;
                    case 8:
                        postVO.setImageContent9(images[i]);
                        break;
                }
            }
        }
        return postVO;
    }


    @Override
    public List<PostVO> QueryByName (String title) {

        QueryWrapper<Post> wrapper = new QueryWrapper<>();
        wrapper.like("title",title)
                .orderByDesc("create_date");
        List<Post> records = baseMapper.selectList(wrapper);
        List<PostVO> postVOS = new ArrayList<>();
        PostVO postVO;
        for (Post record : records) {
            postVO = new PostVO();
            BeanUtils.copyProperties(record, postVO);
            postVO.setPosterName((userService.getById(record.getPosterId()).getUserName1()));
            //分割图片的URL
            String[] images = record.getImageContent().split("\\$");
            for(int i=0; i< images.length;i++)
            {
                switch (i) {
                    case 0:
                        postVO.setImageContent1(images[i]);
                        break;
                    case 1:
                        postVO.setImageContent2(images[i]);
                        break;
                    case 2:
                        postVO.setImageContent3(images[i]);
                        break;
                    case 3:
                        postVO.setImageContent4(images[i]);
                        break;
                    case 4:
                        postVO.setImageContent5(images[i]);
                        break;
                    case 5:
                        postVO.setImageContent6(images[i]);
                        break;
                    case 6:
                        postVO.setImageContent7(images[i]);
                        break;
                    case 7:
                        postVO.setImageContent8(images[i]);
                        break;
                    case 8:
                        postVO.setImageContent9(images[i]);
                        break;
                }
            }
            postVOS.add(postVO);
        }
        return postVOS;
    }

    @Override
    public boolean delete(String postId)
    {
        List<Post> posts = new ArrayList<>();
        QueryWrapper<Post> wrapper = new QueryWrapper<>();
        wrapper.eq("post_id",postId);
        posts = baseMapper.selectList(wrapper);
        for(Post record: posts){
            if(!record.getPosterId().equals(kytUtil.getUserId()))
            {
                return false;
            }
        }
        return remove(wrapper);
    }


    @Override
    public List<PostVO> commend(long current, long limit)
    {
        DateTimeFormatter df1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        today = LocalDateTime.now();
        LocalDateTime former = today;
        former=former.minusMonths(1);
        Page<Post> postPage = new Page<>(current, limit);
        QueryWrapper<Post> wrapper = new QueryWrapper<>();
        wrapper.apply(today!=null,
                        "DATE_FORMAT(create_date,'%Y-%m-%d %H:%i:%s') <= DATE_FORMAT('" + df1.format(today)+ "','%Y-%m-%d %H:%i:%s')")
                .apply(former!=null,
                        "DATE_FORMAT(create_date,'%Y-%m-%d %H:%i:%s') >= DATE_FORMAT('" + df1.format(former)+ "','%Y-%m-%d %H:%i:%s')")
                .orderByDesc("like_count")
                .orderByDesc("favorite_count")
                .orderByDesc("modified_date");
        this.page(postPage, wrapper);
        List<Post> records = postPage.getRecords();
        List<PostVO> postVOS = new ArrayList<>();
        PostVO postVO;
        for (Post record : records) {
            postVO = new PostVO();
            BeanUtils.copyProperties(record, postVO);
            postVO.setPosterName((userService.getById(record.getPosterId()).getUserName1()));
            //分割图片的URL
            String[] images = record.getImageContent().split("\\$");
            for(int i=0; i< images.length;i++)
            {
                switch (i) {
                    case 0:
                        postVO.setImageContent1(images[i]);
                        break;
                    case 1:
                        postVO.setImageContent2(images[i]);
                        break;
                    case 2:
                        postVO.setImageContent3(images[i]);
                        break;
                    case 3:
                        postVO.setImageContent4(images[i]);
                        break;
                    case 4:
                        postVO.setImageContent5(images[i]);
                        break;
                    case 5:
                        postVO.setImageContent6(images[i]);
                        break;
                    case 6:
                        postVO.setImageContent7(images[i]);
                        break;
                    case 7:
                        postVO.setImageContent8(images[i]);
                        break;
                    case 8:
                        postVO.setImageContent9(images[i]);
                        break;
                }
            }
            postVOS.add(postVO);
        }
        return postVOS;
    }





}
