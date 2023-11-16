package com.example.tallking.service;


import com.example.tallking.entity.Favorite;
import com.example.tallking.entity.UserLike;
import com.example.tallking.mapper.FavoriteMapper;
import com.example.tallking.mapper.UserLikeMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserLikeService {

    @Resource
    UserLikeMapper userLikeMapper;

    public void insertfavorite(UserLike userLike){

        userLikeMapper.insert(userLike);
    }

    public Integer findfavoriteByid(Integer userid,Integer postid){

        return userLikeMapper.selectByUserId(userid,postid);
    }


    public Integer findstatusByid(Integer userid,Integer postid){

        return userLikeMapper.selectstatusByUserId(userid,postid);
    }

    public Integer selectAllByPostId(Integer postid){

        return userLikeMapper.selectAllByPostId(postid);
    }

    public void deletefavorite(Integer id){
        userLikeMapper.deleteById(id);
    }

    public void updatafavorite(Integer id,Integer status){
        userLikeMapper.updatestatusById(id,status);
    }




}
