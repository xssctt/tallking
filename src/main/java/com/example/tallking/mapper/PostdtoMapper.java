package com.example.tallking.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.tallking.entity.Post;
import com.example.tallking.entity.Postdto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PostdtoMapper extends BaseMapper<Postdto> {



    @Select("select id from postdto where userid=#{userid} and postid=#{postid}")
    Integer selectByUserId(@Param("userid") Integer userid, @Param("postid") Integer postid);

    @Select("select `like` from postdto where userid=#{userid} and postid=#{postid}")
    String selectlikeByUserId(@Param("userid") Integer userid,@Param("postid") Integer postid);

    @Select("select favorite from postdto where userid=#{userid} and postid=#{postid}")
    String selectFavoriteByUserId(@Param("userid") Integer userid,@Param("postid") Integer postid);


    @Select("select * from postdto where userid=#{userid} and postid=#{postid}")
    Postdto selectPostdtoByUserId(@Param("userid") Integer userid,@Param("postid") Integer postid);



    @Update("update postdto set `like`=#{like} where id=#{id}")
    void updatelikeById(@Param("id") Integer id,@Param("like") String like);

    @Update("update postdto set favorite=#{favorite} where id=#{id}")
    void updatefavoriteById(@Param("id") Integer id,@Param("favorite") String favorite);



    @Select("select count(*) from postdto where postid=#{postid} and `like` ='true' ")
    Integer selectAlllikeByPostId(@Param("postid") Integer postid);

    @Select("select count(*) from postdto where postid=#{postid} and favorite='true' ")
    Integer selectAllfavoriteByPostId(@Param("postid") Integer postid);


    @Insert("insert into postdto(`like`,nickname,avatar,userid,username,postid) values (#{postdto.like},#{postdto.nickname},#{postdto.avatar},#{postdto.userid},#{postdto.username},#{postdto.postid})")
    void insertlikePost(@Param("postdto") Postdto postdto);

    @Insert("insert into postdto(favorite,nickname,avatar,userid,username,postid) values (#{postdto.favorite},#{postdto.nickname},#{postdto.avatar},#{postdto.userid},#{postdto.username},#{postdto.postid})")
    void insertfavoritePost(@Param("postdto") Postdto postdto);






    //查询 用户like
    @Select(" select * from postdto where userid=#{userid} and `like`='true' limit #{offset},#{pageSize} ")
    List<Postdto> findUseridlikelist(@Param("pageSize") Integer pageSize,@Param("offset") Integer offset,@Param("userid") Integer userid);


    @Select(" select count(*) from postdto where userid=#{userid} and `like`='true' ")
    Integer findByuserilikedall(@Param("userid") Integer userid);





    //查询 用户发的favorite

    @Select(" select * from postdto where userid=#{userid} and favorite='true' limit #{offset},#{pageSize} ")
    List<Postdto> findUseridfavoritelist(@Param("pageSize") Integer pageSize,@Param("offset") Integer offset,@Param("userid") Integer userid);


    @Select(" select count(*) from postdto where userid=#{userid} and favorite='true' ")
    Integer findByuseridfavoriteall(@Param("userid") Integer userid);


    @Select(" select * from postdto where 1=1 ")
    List<Postdto> selectAllPostdto();
}