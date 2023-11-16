package com.example.tallking.service;

import cn.hutool.core.collection.CollectionUtil;
import com.example.tallking.common.Result;
import com.example.tallking.entity.Category;
import com.example.tallking.entity.Post;
import com.example.tallking.entity.Tag;
import com.example.tallking.mapper.PostMapper;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.List;

@Service
public class PostService {


    @Resource
    PostMapper postMapper;


    public Post createPost(Post post){



        //postMapper.createPost(post);
        postMapper.insert(post);

        return post;
    }


    public void deletePost(Integer id){

        postMapper.deleteById(id);
    }

    public Result addfavorite(Integer id){

        postMapper.addfavorite(id);

        return Result.success();
    }

    public Post findPostById(Integer id){

        return postMapper.selectById(id);
    }

    public void updatafavoriteCount(Integer favoriteCount,Integer id){

        postMapper.updatafavoriteCount(favoriteCount,id);

    }
    public void updatalikeCount(Integer likeCount,Integer id){

        postMapper.updatalikeCount(likeCount,id);

    }

    //获取各分类 帖子
    public List<Post> findpostlist(Integer categoryId,Integer limit, Integer page){

        int totalCount=postMapper.findBycategoryidall(categoryId);
        Integer offset;



        Integer totalPages = totalCount;
        if (totalCount % limit != 0){
            totalPages += 1;
        }
        //   # 获取当前页数据
        offset = (page - 1) * limit;

        System.out.println(categoryId+"--"+limit+"--"+offset);
        return postMapper.findpostlist(limit,offset,categoryId);
    }


    //获取评论  《新表》
    public List<Post> findpostReplylist(Integer parentid,Integer limit, Integer page){

        int totalCount=postMapper.findreplyByfuidall(parentid);
        Integer offset;



        Integer totalPages = totalCount;
        if (totalCount % limit != 0){
            totalPages += 1;
        }
        //   # 获取当前页数据
        offset = (page - 1) * limit;

        System.out.println(parentid+"--"+limit+"--"+offset);
        return postMapper.findreplypostlist(limit,offset,parentid);
    }


    public List<Post> findUseridlist(Integer userid,Integer limit, Integer page){

        int totalCount=postMapper.finduserByidall(userid);
        Integer offset;



        Integer totalPages = totalCount;
        if (totalCount % limit != 0){
            totalPages += 1;
        }
        //   # 获取当前页数据
        offset = (page - 1) * limit;

        System.out.println(userid+"--"+limit+"--"+offset);
        return postMapper.findByiddall(limit,offset,userid);
    }


    public List<Post> selectAllPost() {
        return postMapper.selectAllPost();
    }

    public void addreply() {
    }

    public void updatereply(Integer id) {
        Integer replycount=postMapper.findreplyByfuidall(id);
        postMapper.updatereply(replycount);
    }
}
