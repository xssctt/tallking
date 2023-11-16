package com.example.tallking.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.tallking.entity.Tokendto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;



@Repository
public interface TokendtoMapper extends BaseMapper<Tokendto> {




    @Insert("insert into tokendto(token,userid) values (#{ctoken.token},#{ctoken.userid})")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    void InserToken(@Param("ctoken") Tokendto tokenuser);



    @Select("select * from tokendto where token = #{token} ")
    Tokendto getUserIdByToken(@Param("token") String token);

    @Select("select token from tokendto where userid = #{userid} ")
    String getTokenIdByuserid(@Param("userid") int userid);



}