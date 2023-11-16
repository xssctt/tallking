package com.example.tallking.service;


import com.example.tallking.entity.Favorite;
import com.example.tallking.mapper.FavoriteMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class FavoriteService {

    @Resource
    FavoriteMapper favoriteMapper;

    public void insertfavorite(Favorite favorite){

        favoriteMapper.insert(favorite);
    }

    public Integer findfavoriteByid(Integer userid,Integer postid){

        return favoriteMapper.selectByUserId(userid,postid);
    }


    public Integer findstatusByid(Integer userid,Integer postid){

        return favoriteMapper.selectstatusByUserId(userid,postid);
    }

    public Integer selectAllByPostId(Integer postid){

        return favoriteMapper.selectAllByPostId(postid);
    }

    public void deletefavorite(Integer id){
        favoriteMapper.deleteById(id);
    }

    public void updatafavorite(Integer id,Integer status){
        favoriteMapper.updatestatusById(id,status);
    }




}
