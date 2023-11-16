package com.example.tallking.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.tallking.entity.UserDto;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;


@Repository
public interface UserdtoMapper extends BaseMapper<UserDto> {


    @Select("select * from userdto where id =#{id}")
    UserDto getuser(@Param("id") int id);


    @Select("select * from userdto where mobile = #{mobile} ")
    UserDto getUserByiphone(@Param("mobile") String mobile);


    @Select("select * from userdto where username = #{name} ")
    UserDto getIdByname(@Param("name") String name);


    @Insert("insert into userdto(username,password,nickname) values ( #{user.username},#{user.password},#{user.nickname} )")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    void InserUser(@Param("user") UserDto user);

    @Update("update userdto set password=#{password} where id=#{id}")
    void updatePassword(@Param("password") String password,@Param("id") int id);



    @Update("update userdto set avatar=#{avatar} where id=#{id}")
    void updatAevatar(@Param("avatar") String avatar,@Param("id") int id);

    @Update("update userdto set nickname=#{nickname} where id=#{id}")
    void updatnickname(@Param("nickname") String nickname, @Param("id") Integer id);
}