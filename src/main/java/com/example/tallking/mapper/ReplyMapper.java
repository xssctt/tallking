package com.example.tallking.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.tallking.entity.Reply;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
public interface ReplyMapper extends BaseMapper<Reply> {


    @Select("select * from reply where postid=#{postid}")
    List<Reply> selectByPostId(@Param("postid") Integer postid);

    @Select("select count(*) from reply where postid=#{postid}")
    Integer selectcountByPostId(@Param("postid") Integer postid);

    @Select("select * from reply where postid=#{postid} limit #{offset},#{limit}")
    List<Reply> selectByPostlist(@Param("postid") Integer postid,@Param("limit") Integer limit,@Param("offset") Integer offset);
}
