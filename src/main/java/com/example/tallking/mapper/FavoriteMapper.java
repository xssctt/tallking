package com.example.tallking.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.tallking.entity.Favorite;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;


@Repository
public interface FavoriteMapper extends BaseMapper<Favorite> {


    @Select("select id from favorite where userid=#{userid} and postid=#{postid}")
    Integer selectByUserId(@Param("userid") Integer userid,@Param("postid") Integer postid);

    @Select("select status from favorite where userid=#{userid} and postid=#{postid}")
    Integer selectstatusByUserId(@Param("userid") Integer userid,@Param("postid") Integer postid);

    @Update("update favorite set status=#{status} where id=#{id}")
    void updatestatusById(@Param("id") Integer id,@Param("status") Integer status);

    @Select("select count(*) from favorite where postid=#{postid} and status=1")
    Integer selectAllByPostId(@Param("postid") Integer postid);
}
