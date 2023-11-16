package com.example.tallking.service;


import com.example.tallking.entity.Favorite;
import com.example.tallking.entity.Postdto;
import com.example.tallking.mapper.FavoriteMapper;
import com.example.tallking.mapper.PostdtoMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PostDtoService {

    @Resource
    PostdtoMapper postdtoMapper;

    public void insertlikepost(Postdto postdto){

        postdtoMapper.insertlikePost(postdto);
    }
    public void insertFavoritepost(Postdto postdto){

        postdtoMapper.insertfavoritePost(postdto);
    }

    public Integer findByid(Integer userid,Integer postid){

        return postdtoMapper.selectByUserId(userid,postid);
    }

    public Postdto selectPostdtoByUserId(Integer userid,Integer postid){
        return postdtoMapper.selectPostdtoByUserId(userid,postid);
    }



    public Boolean selectlikeByUserId(Integer userid,Integer postid){

        return Boolean.valueOf(postdtoMapper.selectlikeByUserId(userid,postid));
    }

    public Boolean selectFavoriteByUserId(Integer userid,Integer postid){

        return Boolean.valueOf(postdtoMapper.selectFavoriteByUserId(userid,postid));
    }



    public Integer selectAllfavoriteByPostId(Integer postid){
        return postdtoMapper.selectAllfavoriteByPostId(postid);
    }

    public Integer selectAlllikeByPostId(Integer postid){
        return postdtoMapper.selectAlllikeByPostId(postid);
    }


    public void deletepostdto(Integer id){
        postdtoMapper.deleteById(id);
    }


    public void updatafavorite(Integer id,String status){
        postdtoMapper.updatefavoriteById(id,status);
    }
    public void updatalike(Integer id,String status){
        postdtoMapper.updatelikeById(id,status);
    }


    //获取我的收藏/点赞
    public List<Postdto> findUseridfavoritelist(Integer userid, Integer limit, Integer page){

        int totalCount=postdtoMapper.findByuseridfavoriteall(userid);
        Integer offset;



        Integer totalPages = totalCount;
        if (totalCount % limit != 0){
            totalPages += 1;
        }
        //   # 获取当前页数据
        offset = (page - 1) * limit;

        System.out.println(userid+"--"+limit+"--"+offset);
        return postdtoMapper.findUseridfavoritelist(limit,offset,userid);
    }

    public List<Postdto> findUseridlikelist(Integer userid, Integer limit, Integer page){

        int totalCount=postdtoMapper.findByuserilikedall(userid);
        Integer offset;



        Integer totalPages = totalCount;
        if (totalCount % limit != 0){
            totalPages += 1;
        }
        //   # 获取当前页数据
        offset = (page - 1) * limit;

        System.out.println(userid+"--"+limit+"--"+offset);
        return postdtoMapper.findUseridlikelist(limit,offset,userid);
    }

    public List<Postdto> selectAllPostdto() {
        return postdtoMapper.selectAllPostdto();
    }
}
