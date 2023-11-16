package com.example.tallking.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.tallking.entity.Category;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;


import java.util.List;


@Repository
public interface CategoryMapper extends BaseMapper<Category> {


//  select * from type_info where 1=1
//    <if test="name !=null and name !='all' and name !=''">
//      and `name`=#{name}
//    </if>


    @Select("<script>" +
            " select * from category where 1=1" +
            "<where>" +
            " <if test=' name != null and name != \"all\" and name != \" \" ' > " +
            "and name = #{name}" +
            "</if>"+
            "</where >"+
            "</script> ")
    List<Category> findByName(@Param("name") String name);

    @Select("select count(*) from category where 1=1")
    int findByNameall();



    @Select(" select * from category where 1=1")
    List<Category>  findallData();


    @Select(" select * from category limit #{pageSize} offset #{offset}")
    List<Category> findByNamep(@Param("offset") Integer offset,@Param("pageSize") Integer pageSize);


    @Select("select * from category where id=#{id}")
    Category selectById(@Param("id") Integer id);
}