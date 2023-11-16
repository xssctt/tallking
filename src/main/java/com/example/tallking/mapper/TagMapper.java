package com.example.tallking.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.tallking.entity.Tag;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TagMapper extends BaseMapper<Tag> {


    @Select("select * from tag where name=#{tagname}")
    Tag findTagByname(@Param("tagname") String tagname);

    @Select("select * from tag where 1=1 limit #{hotnum} ")
    List<Tag> findTagHot(@Param("hotnum") Integer hotnum);

}