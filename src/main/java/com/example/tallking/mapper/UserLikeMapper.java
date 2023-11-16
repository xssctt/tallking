package com.example.tallking.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.tallking.entity.Favorite;
import com.example.tallking.entity.UserLike;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;


@Repository
public interface UserLikeMapper extends BaseMapper<UserLike> {


    @Select("select id from userlike where userid=#{userid} and postid=#{postid}")
    Integer selectByUserId(@Param("userid") Integer userid,@Param("postid") Integer postid);

    @Select("select status from userlike where userid=#{userid} and postid=#{postid}")
    Integer selectstatusByUserId(@Param("userid") Integer userid,@Param("postid") Integer postid);

    @Update("update userlike set status=#{status} where id=#{id}")
    void updatestatusById(@Param("id") Integer id,@Param("status") Integer status);

    @Select("select count(*) from userlike where postid=#{postid} and status=1")
    Integer selectAllByPostId(@Param("postid") Integer postid);
}
